
/**
 * Write a description of class InterCollec here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.time.LocalDate;

public class InterCollec extends JDialog
{   
    static long nif;
    JLabel teste;
    JLabel t;
    JMenuBar barraF;
    JMenu fatura,cliente,faturado;
    JMenuItem novaFatura,verFatura,valFatura,oddFatura,clientBydata,clientSort,getFaturado;
    
    public InterCollec(JFrame frame, long n)
    {          
        super(frame,"Empresas");
        setLayout(new FlowLayout());
        nif = n;
        
        BaseDados bd = new BaseDados();
        HashMap<Long,C_Colectivo> col = bd.getColectivo();
        
        C_Colectivo x = col.get(n);
        
        barraF = new JMenuBar();
        setJMenuBar(barraF);
        
        fatura = new JMenu("Fatura");
        barraF.add(fatura);
        
        cliente = new JMenu("Clientes");
        barraF.add(cliente);
        
        faturado = new JMenu("Total Faturado");
        barraF.add(faturado);
        
        novaFatura = new JMenuItem("Criar Fatura");
        fatura.add(novaFatura);
        
        verFatura = new JMenuItem("Verificar Faturas");
        fatura.add(verFatura);
        
        valFatura = new JMenuItem("Faturas por validar");
        fatura.add(valFatura);
        
        oddFatura = new JMenuItem("Faturas emitidas");
        fatura.add(oddFatura);
        
        clientBydata = new JMenuItem("Faturas dos Clientes por data");
        cliente.add(clientBydata);
        
        clientSort = new JMenuItem("Faturas dos Clientes ordenadas por preço");
        cliente.add(clientSort);
        
        getFaturado = new JMenuItem("Obter Faturado");
        faturado.add(getFaturado);
        
        t = new JLabel("Bem vindo ao seu programa de faturação -- " + x.getNome());
        add(t);
        
        event e = new event();
        novaFatura.addActionListener(e);
        verFatura.addActionListener(e);
        valFatura.addActionListener(e); 
        oddFatura.addActionListener(e);
        clientBydata.addActionListener(e);
        clientSort.addActionListener(e);
        getFaturado.addActionListener(e);
    }
    
    public class event implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {                
            String tipoA = e.getActionCommand();
                
            if(tipoA.equals("Criar Fatura"))
            {
                CriaFat cf = new CriaFat(InterCollec.this,nif);
                cf.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                cf.setSize(1000,100);
                cf.setLocation(0,0);
                cf.setVisible(true);
            }
            if(tipoA.equals("Verificar Faturas"))
            {
                BaseDados bd = new BaseDados();
                HashMap<Long,Faturas> ft = bd.getFatura();
                HashMap<Long,C_Colectivo> cl = bd.getColectivo();
                    
                if(ft.containsKey(nif))
                {
                    JFrame warn = new JFrame("As Suas Faturas");
                    warn.setLayout(new FlowLayout());
                    warn.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                    warn.setSize(700,300);
                    warn.setLocation(700,100);
                    warn.setVisible(true);
                    Iterator<Fatura> it = ft.get(nif).getFaturas().iterator();
                    int index = 0;
                    while(it.hasNext())
                    {
                        Fatura f = (Fatura) it.next();
                        int sup = index;
                        JPanel fatu = new JPanel(new FlowLayout());
                        StringBuilder but = new StringBuilder();
                        but.append("Emissor: " + f.getNomeE());
                        but.append(" || Descriçao:" + f.getDesc());
                        but.append(" || Data:" + f.getData().toString());
                        String kk = but.toString();
                        JLabel click = new JLabel(kk);
                        click.addMouseListener(new MouseListener()
                        {
                            public void mouseReleased(MouseEvent e){}
                            public void mousePressed(MouseEvent e){}
                            public void mouseExited(MouseEvent e){}    
                            public void mouseEntered(MouseEvent e){}
                            public void mouseClicked(MouseEvent e){
                                JOptionPane.showMessageDialog(null,f.toString());
                            }});
                        fatu.add(click);
                        if(f.getVal() == true) {
                            JLabel upd = new JLabel(" || atualizar");
                            upd.setForeground(Color.RED);
                            upd.addMouseListener(new MouseListener(){
                                public void mouseReleased(MouseEvent e){}
                                public void mousePressed(MouseEvent e){}
                                public void mouseExited(MouseEvent e){}    
                                public void mouseEntered(MouseEvent e){}
                                public void mouseClicked(MouseEvent e){
                                    JFrame edit = new JFrame("Fatura:");
                                    edit.setLayout(new FlowLayout(5));
                                    edit.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                                    edit.setSize(700,200);
                                    edit.setLocation(100,100);
                                    edit.setVisible(true);
                                    C_Colectivo fo = cl.get(f.getNifE());
                                    JLabel cc = new JLabel("Qual o serviço correspondente a esta fatura?");
                                    JTextField bb = new JTextField(10);
                                    JLabel foDes = new JLabel("<html><br>Despesas participantes por parte do Fornecedor: <br><br>"+fo.getDSP().empString()+"<br><html>");
                                    foDes.setForeground(Color.RED);
                                    JButton update = new JButton("Atualiza Fatura");
                                    JLabel sucess = new JLabel("");
                                    edit.add(cc);
                                    edit.add(bb);
                                    edit.add(foDes);
                                    edit.add(update);
                                    edit.add(sucess);
                                    update.addActionListener(new ActionListener(){
                                        public void actionPerformed(ActionEvent e){
                                            int n;
                                            try
                                            {
                                                n = Integer.parseInt(bb.getText());
                                            }catch(NumberFormatException x) {
                                                JOptionPane.showMessageDialog(null,"Not a Number");
                                                return;
                                            }                                         
                                            if(fo.getDSP().notBelong(n))
                                                JOptionPane.showMessageDialog(null,"Natureza escrita nao pertence aos serviços \n prestados pelo Fornecedor");
                                            else
                                            {
                                                HashMap<Long,C_Individual> id = bd.getIndividual();
                                                C_Individual remetente = id.get(nif);
                                                DedAcu dx = new DedAcu();
                                                dx = remetente.getDAc();
                                                dx.remValor(f.getCost(),f.getDSP());
                                                
                                                f.setDSP(n);
                                                Faturas fts = ft.get(nif);
                                                ArrayList<Fatura> fat = fts.getFaturas();
                                                
                                                dx.add(n,(f.getDSPD() * f.getCost()));
                                                bd.addIndividual(remetente);
                                                
                                                fat.set(sup,f);
                                                fts.setFaturas(fat);
                                                ft.replace(nif,fts);
                                                bd.setFatura(ft);
                                                sucess.setText("Fatura atualizada com sucesso");
                                                sucess.setForeground(Color.BLUE);
                                            }
                                        }});
                                    }});
                                fatu.add(upd);
                        }
                        warn.add(fatu);
                        index++;
                    }
                }
                else
                {
                    JFrame warn = new JFrame("As Suas Faturas");
                    warn.setLayout(new FlowLayout());
                    warn.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                    warn.setSize(400,50);
                    warn.setLocation(700,300);
                    warn.setVisible(true);
                    JLabel not = new JLabel("Nao possui faturas em seu nome");
                    warn.add(not);
                }
            }
            
            if(tipoA.equals("Faturas por validar"))
            {
                BaseDados bd = new BaseDados();
                HashMap<Long,Faturas> ft = bd.getFatura();
                HashMap<Long,C_Colectivo> cl = bd.getColectivo();
                
                if(ft.containsKey(nif))
                {
                    JFrame warn = new JFrame("As Suas Faturas por Validar:");
                    warn.setLayout(new FlowLayout());
                    warn.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                    warn.setSize(700,300);
                    warn.setLocation(700,300);
                    warn.setVisible(true);
                    Iterator<Fatura> it = ft.get(nif).getFaturas().iterator();
                    int index = 0;
                    while(it.hasNext())
                    {
                        int sup = index;                     
                        Fatura f = (Fatura) it.next();
                        if(f.getVal() == false){
                            JPanel fatu = new JPanel(new FlowLayout());
                            StringBuilder but = new StringBuilder();
                            but.append("Emissor: " + f.getNomeE());
                            but.append(" || Descriçao:" + f.getDesc());
                            but.append(" || Data:" + f.getData().toString());
                            String kk = but.toString();
                            JLabel click = new JLabel(kk);
                            click.addMouseListener(new MouseListener()
                            {
                                public void mouseReleased(MouseEvent e){}
                                public void mousePressed(MouseEvent e){}
                                public void mouseExited(MouseEvent e){}    
                                public void mouseEntered(MouseEvent e){}
                                public void mouseClicked(MouseEvent e){
                                    JFrame edit = new JFrame("Fatura:");
                                    edit.setLayout(new FlowLayout(5));
                                    edit.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                                    edit.setSize(900,400);
                                    edit.setLocation(100,100);
                                    edit.setVisible(true);
                                    StringBuilder sb = new StringBuilder();        
                                    sb.append("<html>Fornecedor: ");
                                    sb.append(f.getNomeE() + "<br>");
                                    sb.append("NIF Fornecedor: ");
                                    sb.append(f.getNifE() + "<br>");
                                    sb.append("NIF Cliente: ");
                                    sb.append(f.getNifC() + "<br>");
                                    sb.append("Natureza da despesa efectuada: ");
                                    sb.append(f.getDSP() + "<br>");
                                    sb.append("Descriçao da Despesa: ");
                                    sb.append(f.getDesc() + "<br>");
                                    sb.append("Desconto associado a despesa: ");
                                    sb.append(f.getDSPD() + "<br>");
                                    sb.append("Preço total: ");
                                    sb.append(f.getCost() + "<br>");
                                    sb.append("Data: ");
                                    sb.append(f.getData().toString() + "<br>");
                                    sb.append("Validada: ");
                                    sb.append(f.getVal() + "<br><html>");
                                    JLabel dt = new JLabel(sb.toString());
                                    edit.add(dt);
                                
                                    C_Colectivo fo = cl.get(f.getNifE());
                                    JLabel foDes = new JLabel("<html><br>Despesas participantes por parte do Fornecedor: <br><br>"+fo.getDSP().empString()+"<br><html>");
                                    foDes.setForeground(Color.RED);
                                    JTextField newNat = new JTextField(10);
                                    JLabel newDes = new JLabel("A qual tipo de despesa esta fatura corresponde?");
                                    JButton update = new JButton("Atualiza Fatura");
                                    JLabel sucess = new JLabel("");
                                    edit.add(newDes);
                                    edit.add(newNat);
                                    edit.add(foDes);
                                    edit.add(update);
                                    edit.add(sucess);
                                    update.addActionListener(new ActionListener()
                                    {
                                        public void actionPerformed(ActionEvent e){
                                            int n;
                                            try
                                            {
                                                n = Integer.parseInt(newNat.getText());
                                            }catch(NumberFormatException x) {
                                                JOptionPane.showMessageDialog(null,"Not a Number");
                                                return;
                                            }
                                        
                                            if(fo.getDSP().notBelong(n))
                                                JOptionPane.showMessageDialog(null,"Natureza escrita nao pertence aos serviços \n prestados pelo Fornecedor");
                                            else{
                                                f.setDSP(n);
                                                f.setVal(true);
                                                
                                                C_Colectivo remetente = cl.get(nif);
                                                DedAcu dx = new DedAcu();
                                                dx = remetente.getDAc();
                                                dx.add(n,(f.getDSPD() * f.getCost()));
                                                bd.addColectivo(remetente);
                                                
                                                Faturas fts = ft.get(nif);
                                                ArrayList<Fatura> fat = fts.getFaturas();
                                                fat.set(sup,f);
                                                fts.setFaturas(fat);
                                                ft.replace(nif,fts);
                                                bd.setFatura(ft);
                                                sucess.setText("Fatura atualizada com sucesso");
                                                sucess.setForeground(Color.BLUE);
                                            }
                                        }
                                    });                     
                                }
                            });
                            fatu.add(click);
                            warn.add(fatu);
                        }
                        index++;
                    }
                }
                else
                {
                    JFrame warn = new JFrame("As Suas Faturas");
                    warn.setLayout(new FlowLayout());
                    warn.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                    warn.setSize(600,100);
                    warn.setLocation(700,100);
                    warn.setVisible(true);
                    JLabel not = new JLabel("Nao possui faturas por validar");
                    warn.add(not);
                }
            }
            if(tipoA.equals("Faturas emitidas"))
            {
                BaseDados bd = new BaseDados();
                ArrayList<Fatura> data = bd.getFaturaByNif(nif,1);
                ArrayList<Fatura> valor = bd.getFaturaByNif(nif,2);
                if(data.size() > 0)
                {
                    JFrame warn = new JFrame("As Suas Faturas");
                    warn.setLayout(new FlowLayout());
                    warn.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                    warn.setSize(400,50);
                    warn.setLocation(700,100);
                    warn.setVisible(true);
                    JButton date = new JButton("Ordenar por data");
                    JButton value = new JButton("Ordenar por valor");
                    warn.add(date);
                    warn.add(value);
                    date.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){
                            Iterator<Fatura> it = data.iterator();
                            JFrame war = new JFrame("As Suas Faturas");
                            war.setLayout(new FlowLayout());
                            war.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                            war.setSize(600,100);
                            war.setLocation(700,300);
                            war.setVisible(true);
                            while(it.hasNext()){
                                Fatura f = (Fatura) it.next();
                                JPanel fatu = new JPanel(new FlowLayout());
                                StringBuilder but = new StringBuilder();
                                but.append("Cliente: " + f.getNifC());
                                but.append(" || Descriçao:" + f.getDesc());
                                but.append(" || Data:" + f.getData().toString());
                                String kk = but.toString();
                                JLabel click = new JLabel(kk);
                                click.addMouseListener(new MouseListener(){
                                    public void mouseReleased(MouseEvent e){}
                                    public void mousePressed(MouseEvent e){}
                                    public void mouseExited(MouseEvent e){}    
                                    public void mouseEntered(MouseEvent e){}
                                    public void mouseClicked(MouseEvent e){
                                        JOptionPane.showMessageDialog(null,f.toString());
                                    }
                                });
                                fatu.add(click);
                                war.add(fatu);
                            }
                        }
                    });
                    value.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){
                            Iterator<Fatura> it = valor.iterator();
                            JFrame war = new JFrame("As Suas Faturas");
                            war.setLayout(new FlowLayout());
                            war.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                            war.setSize(600,100);
                            war.setLocation(700,300);
                            war.setVisible(true);
                            while(it.hasNext()){
                                Fatura f = (Fatura) it.next();
                                JPanel fatu = new JPanel(new FlowLayout());
                                StringBuilder but = new StringBuilder();
                                but.append("Emissor: " + f.getNomeE());
                                but.append(" || Descriçao:" + f.getDesc());
                                but.append(" || Data:" + f.getData().toString());
                                String kk = but.toString();
                                JLabel click = new JLabel(kk);
                                click.addMouseListener(new MouseListener(){
                                    public void mouseReleased(MouseEvent e){}
                                    public void mousePressed(MouseEvent e){}
                                    public void mouseExited(MouseEvent e){}    
                                    public void mouseEntered(MouseEvent e){}
                                    public void mouseClicked(MouseEvent e){
                                        JOptionPane.showMessageDialog(null,f.toString());
                                    }
                                });
                                fatu.add(click);
                                war.add(fatu);
                            }
                        }
                    });
                }
                else
                {
                    JFrame warn = new JFrame("As Suas Faturas");
                    warn.setLayout(new FlowLayout());
                    warn.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                    warn.setSize(600,100);
                    warn.setLocation(700,100);
                    warn.setVisible(true);
                    JLabel not = new JLabel("Nao possui faturas emitidas");
                    warn.add(not);
                }
            }            
            if(tipoA.equals("Faturas dos Clientes por data"))
            {
                Querys xx = new Querys();                
                ArrayList<Long> clientes = xx.getContribuintesEmp(nif);
                
                if(clientes.size() > 0)
                {
                    JFrame warn = new JFrame("Ver faturas");
                    warn.setLayout(new FlowLayout());
                    warn.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                    warn.setSize(400,150);
                    warn.setLocation(700,100);
                    warn.setVisible(true);                   
                    JLabel procurar = new JLabel("Entre que datas quer procurar:");
                    JLabel dt1 = new JLabel("Intervalo de tempo: I(Dia-Mes-Ano)/F(Dia-Mes-Ano)");
                    JTextField tf1 = new JTextField(17);                    
                    warn.add(procurar);
                    warn.add(dt1);
                    warn.add(tf1);
                    JButton getEm = new JButton("Obter Faturas");
                    warn.add(getEm);
                    getEm.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){
                            String time;
                            
                            time = tf1.getText();
                            LocalDate begin = xx.getTime(time,1);
                            LocalDate end = xx.getTime(time,2);
                            
                            JFrame novo = new JFrame("Contribuintes");
                            novo.setLayout(new FlowLayout());
                            novo.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                            novo.setSize(400,300);
                            novo.setLocation(700,100);
                            novo.setVisible(true);   
                            
                            Iterator it = clientes.iterator();
                            while(it.hasNext())
                            {                                
                                Long nf = (Long) it.next();
                                JLabel nife = new JLabel("|| " + nf.toString() + " ||");
                                novo.add(nife);
                                nife.addMouseListener(new MouseListener(){
                                    public void mouseReleased(MouseEvent e){}
                                    public void mousePressed(MouseEvent e){}
                                    public void mouseExited(MouseEvent e){}    
                                    public void mouseEntered(MouseEvent e){}
                                    public void mouseClicked(MouseEvent e){
                                        ArrayList<Fatura> ft = xx.getFatContribuinte(nif,nf,begin,end);
                                        if(ft.size() > 0)
                                        {
                                             JFrame hek = new JFrame("Faturas");
                                             hek.setLayout(new FlowLayout());
                                             hek.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                                             hek.setSize(400,300);
                                             hek.setLocation(700,100);
                                             hek.setVisible(true);
                                            
                                             Iterator ite = ft.iterator();
                                            while(ite.hasNext())
                                            {
                                                Fatura fatura = (Fatura) ite.next();   
                                                StringBuilder but = new StringBuilder();
                                                but.append("Emissor: " + fatura.getNomeE());
                                                but.append(" || Descriçao:" + fatura.getDesc());
                                                but.append(" || Data:" + fatura.getData().toString());
                                                String kk = but.toString();
                                                JLabel add = new JLabel(kk);
                                                hek.add(add);
                                                add.addMouseListener(new MouseListener(){
                                                    public void mouseReleased(MouseEvent e){}
                                                    public void mousePressed(MouseEvent e){}
                                                    public void mouseExited(MouseEvent e){}    
                                                    public void mouseEntered(MouseEvent e){}
                                                    public void mouseClicked(MouseEvent e){
                                                        JOptionPane.showMessageDialog(null,fatura.toString());
                                                    }
                                                });
                                            }
                                        }
                                        else
                                            JOptionPane.showMessageDialog(null,"Cliente não tem faturas feitas dentro do \n intervalo de tempo fornecido");                                       
                                    }
                                });
                            }
                        }
                    });
                }
            }
            if(tipoA.equals("Faturas dos Clientes ordenadas por preço"))
            {
                Querys xx = new Querys();                
                ArrayList<Long> clientes = xx.getContribuintesEmp(nif);
                
                if(clientes.size() > 0)
                {
                    JFrame novo = new JFrame("Contribuintes");
                    novo.setLayout(new FlowLayout());
                    novo.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                    novo.setSize(400,300);
                    novo.setLocation(700,100);
                    novo.setVisible(true);   
                    
                    Iterator it = clientes.iterator();
                    while(it.hasNext())
                    {                       
                        Long nf = (Long) it.next();
                        JLabel nife = new JLabel("|| " + nf.toString() + " ||");
                        novo.add(nife);
                        nife.addMouseListener(new MouseListener(){
                            public void mouseReleased(MouseEvent e){}
                            public void mousePressed(MouseEvent e){}
                            public void mouseExited(MouseEvent e){}    
                            public void mouseEntered(MouseEvent e){}
                            public void mouseClicked(MouseEvent e){
                            ArrayList<Fatura> ft = xx.getFatSorted(nif,nf);
                            if(ft.size() > 0)
                            {
                                JFrame hek = new JFrame("Faturas");
                                hek.setLayout(new FlowLayout());
                                hek.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                                hek.setSize(400,300);
                                hek.setLocation(700,100);
                                hek.setVisible(true); 
                                
                                Iterator ite = ft.iterator();
                                while(ite.hasNext())
                                {
                                    Fatura fatura = (Fatura) ite.next();                                                  
                                    StringBuilder but = new StringBuilder();
                                    but.append("Emissor: " + fatura.getNomeE());
                                    but.append(" || Descriçao:" + fatura.getDesc());
                                    but.append(" || Data:" + fatura.getData().toString());
                                    String kk = but.toString();
                                    JLabel add = new JLabel(kk);
                                    hek.add(add);
                                    add.addMouseListener(new MouseListener(){
                                        public void mouseReleased(MouseEvent e){}
                                        public void mousePressed(MouseEvent e){}
                                        public void mouseExited(MouseEvent e){}    
                                        public void mouseEntered(MouseEvent e){}
                                        public void mouseClicked(MouseEvent e){
                                            JOptionPane.showMessageDialog(null,fatura.toString());
                                        }
                                    });
                                }
                            }
                            else
                                JOptionPane.showMessageDialog(null,"Cliente não tem faturas");                                       
                            }
                        });
                    }
                }
            }
            if(tipoA.equals("Obter Faturado"))
            {
                Querys xx = new Querys();
                
                JFrame warn = new JFrame("Faturado");
                warn.setLayout(new FlowLayout());
                warn.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                warn.setSize(400,150);
                warn.setLocation(700,100);
                warn.setVisible(true);                   
                JLabel procurar = new JLabel("Entre que datas quer procurar:");
                JLabel dt1 = new JLabel("Intervalo de tempo: I(Dia-Mes-Ano)/F(Dia-Mes-Ano)");
                JTextField tf1 = new JTextField(17);                    
                warn.add(procurar);
                warn.add(dt1);
                warn.add(tf1);
                JButton getEm = new JButton("Total");
                warn.add(getEm);
                getEm.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        String time;
                        LocalDate begin = LocalDate.now();
                        LocalDate end = LocalDate.now();
                        try{    
                            time = tf1.getText();
                            begin = xx.getTime(time,1);
                            end = xx.getTime(time,2);
                        }catch(NumberFormatException x){
                            JOptionPane.showMessageDialog(null,"Formato errado");
                            return;
                        }
                            
                        JFrame novo = new JFrame("Contribuintes");
                        novo.setLayout(new FlowLayout());
                        novo.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                        novo.setSize(200,100);
                        novo.setLocation(700,100);
                        novo.setVisible(true); 
                        JLabel in = new JLabel("Total Recebido: " + xx.getTotalFaturadoIn(nif,begin,end));
                        JLabel out = new JLabel("Total Gasto: " + xx.getTotalFaturadoOut(nif,begin,end));
                        novo.add(in);
                        novo.add(out);
                     }
                });
            }
        }
    }
}

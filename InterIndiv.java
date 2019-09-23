
/**
 * Write a description of class InterIndiv here.
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

public class InterIndiv extends JDialog
{
    private static long nif;
    
    JLabel t;
    JMenuBar barra;
    JMenu fatura;
    JMenu agregado;
    JMenuItem novaFatura;
    JMenuItem nonValid;
    JMenuItem acc;
    
    public InterIndiv(JFrame frame,long n)
    {
        super(frame,"Contribuite Individual");
        setLayout(new FlowLayout());
        nif = n;
        
        BaseDados bd = new BaseDados();
        HashMap<Long,C_Individual> ind = bd.getIndividual();
        
        C_Individual x = ind.get(nif);
         
        barra = new JMenuBar();
        setJMenuBar(barra);
        
        fatura = new JMenu("Fatura");
        barra.add(fatura);
        agregado = new JMenu("Agregado");
        barra.add(agregado);
        
        novaFatura = new JMenuItem("Verificar Faturas");
        fatura.add(novaFatura);
        nonValid = new JMenuItem("Faturas por validar");
        fatura.add(nonValid);
        acc = new JMenuItem("Deduçoes Acumuladas");
        agregado.add(acc);
        
        t = new JLabel("Bem vindo ao seu programa de gestao de faturas -- " + x.getNome());
        add(t);
        
        event e = new event();
        novaFatura.addActionListener(e);
        nonValid.addActionListener(e);
        acc.addActionListener(e);
    }
    
    public class event implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {                
            String tipoA = e.getActionCommand();
                
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
                                    update.addActionListener(new ActionListener()
                                    {
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
                                            else{
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
                                        }
                                    });
                                }
                            });
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
                    warn.setSize(400,100);
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
                                                
                                                HashMap<Long,C_Individual> id = bd.getIndividual();
                                                C_Individual remetente = id.get(nif);
                                                DedAcu dx = new DedAcu();
                                                dx = remetente.getDAc();
                                                dx.add(n,(f.getDSPD() * f.getCost()));
                                                bd.addIndividual(remetente);
                                                
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
                    JLabel not = new JLabel("Nao possui faturas ainda por validar");
                    warn.add(not);
                }
            }
            if(tipoA.equals("Deduçoes Acumuladas"))
            {
               BaseDados bd = new BaseDados(); 
               HashMap<Long,C_Individual> ind = bd.getIndividual();
               C_Individual utilizador = ind.get(nif);
               JFrame warn = new JFrame("Deduçoes do Agregado");
               warn.setLayout(new FlowLayout());
               warn.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
               warn.setSize(500,200);
               warn.setLocation(700,100);
               warn.setVisible(true);
               JLabel uti = new JLabel("As Suas Deduçoes           ");            
               uti.addMouseListener(new MouseListener(){
                   public void mouseReleased(MouseEvent e){}
                   public void mousePressed(MouseEvent e){}
                   public void mouseExited(MouseEvent e){}    
                   public void mouseEntered(MouseEvent e){}
                   public void mouseClicked(MouseEvent e){
                       JOptionPane.showMessageDialog(null,utilizador.getDAc().toString());
                    }
               });
               warn.add(uti);
               DedAcu xx = utilizador.getDAc();
               double total = xx.getDTotal();
               ArrayList<Long> agregado = utilizador.getNifAgreg();
               if(agregado.size() > 0)
               {
                   Iterator<Long> it = agregado.iterator();
                   while(it.hasNext())
                   {
                       long nf = (long) it.next();
                       C_Individual membro = ind.get(nf);
                       StringBuilder but = new StringBuilder();
                       but.append("Nif: " + membro.getNif());
                       but.append(" || Nome:" + membro.getNome());
                       but.append(" || Email:" + membro.getEmail());
                       String kk = but.toString();
                       JLabel click = new JLabel(kk);
                       DedAcu yy = membro.getDAc();
                       double sum = yy.getDTotal();
                       total += sum;
                       click.addMouseListener(new MouseListener()
                       {
                           public void mouseReleased(MouseEvent e){}
                           public void mousePressed(MouseEvent e){}
                           public void mouseExited(MouseEvent e){}    
                           public void mouseEntered(MouseEvent e){}
                           public void mouseClicked(MouseEvent e){
                               JOptionPane.showMessageDialog(null,membro.getDAc().toString());
                           }
                       });
                       warn.add(click);
                   }
               }
               JLabel tot = new JLabel("Total deduzido entre Agregado: " + total + "€");
               tot.setForeground(Color.RED);
               warn.add(tot);
            }
        }
    }
}
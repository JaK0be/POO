/**
 * Classe que define a Janela relativa ao Administrador na Interface Gráfica
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.NavigableMap;
import java.util.Map;
import java.util.HashMap;

public class Administrador extends JDialog
{
    JLabel teste;
    JLabel t;
    JMenuBar barra;
    JMenu contribuinte,queries;
    JMenuItem individual;
    JMenuItem coletivo;
    JMenuItem addFamilia;
    JMenuItem addServiço;
    JMenuItem top10;
    JMenuItem topX;
    
    /**
     * Construtor Gráfico da Janela pertencente ao Administrador
     * 
     * @param (frame) Janela onde será possível criar uma Janela do Administrador
     */
    public Administrador(JFrame frame)
    {
        super(frame, "Administrador");
        setLayout(new FlowLayout());
        
        barra = new JMenuBar();
        setJMenuBar(barra);
        
        contribuinte = new JMenu("Contribuintes");
        barra.add(contribuinte);
        
        queries = new JMenu("Queries");
        barra.add(queries);
        
        individual = new JMenuItem("Novo Contribuinte Individual");
        contribuinte.add(individual);
        
        coletivo = new JMenuItem("Novo Contribuinte Coletivo");
        contribuinte.add(coletivo);
        
        addFamilia = new JMenuItem("Agregado Familiar");
        contribuinte.add(addFamilia);
        
        addServiço = new JMenuItem("Serviços Prestados");
        contribuinte.add(addServiço);
        
        top10 = new JMenuItem("Top 10 Contribuintes");
        queries.add(top10);
        
        topX = new JMenuItem("Top X Empresas");
        queries.add(topX);
        
        teste = new JLabel("Bem-Vindo, Sr(a) Administrador(a).");
        add(teste);
        
        event e = new event();
        individual.addActionListener(e);
        coletivo.addActionListener(e);
        addFamilia.addActionListener(e);
        addServiço.addActionListener(e);
        top10.addActionListener(e);
        topX.addActionListener(e);
    }   
    
    /**
     * Sub-Classe que implementa o que acontece quando se carrega num sub menu do Adminstrador
     */
    public class event implements ActionListener
    {
        /**
         * Função que implementa o que acontece quando se carrega num sub menu do Adminstrador
         */
        public void actionPerformed(ActionEvent e)
        {
            String tipo = e.getActionCommand();
            
            if(tipo.equals("Novo Contribuinte Individual"))
            {
                CriaInd cI = new CriaInd(Administrador.this);
                cI.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                cI.setSize(1200,120);
                cI.setLocation(300,300);
                cI.setVisible(true);
            }
            else if(tipo.equals("Novo Contribuinte Coletivo"))
            {
                CriaCol cC = new CriaCol(Administrador.this);
                cC.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                cC.setSize(1200,120);
                cC.setLocation(300,300);
                cC.setVisible(true);                
            }else if(tipo.equals("Agregado Familiar"))
            {
               AddMembro aM = new AddMembro(Administrador.this);
               aM.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
               aM.setSize(800,60);
               aM.setLocation(300,300);
               aM.setVisible(true); 
            }else if(tipo.equals("Serviços Prestados"))
            {
               AddServiço aS = new AddServiço(Administrador.this);
               aS.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
               aS.setSize(800,260);
               aS.setLocation(300,300);
               aS.setVisible(true); 
            }else if(tipo.equals("Top 10 Contribuintes"))
            {
                Querys xx = new Querys();
                NavigableMap<Double,Long> t10 = xx.getTop10().descendingMap();
                int i = 0;
                StringBuilder sb = new StringBuilder();
                for(Map.Entry<Double,Long> mp : t10.entrySet())
                {
                    int x = i+1;
                    if(i == 10)
                        break;
                    else
                        sb.append(x + "º=> Nif:" + mp.getValue() + " || Total:" + mp.getKey() + ".\n");
                    i++;
                }
                JOptionPane.showMessageDialog(null,sb.toString());
            }else if(tipo.equals("Top X Empresas"))
            {
                JFrame ask = new JFrame("Top X");
                ask.setLayout(new FlowLayout());
                ask.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                ask.setSize(260,100);
                ask.setLocation(300,300);
                ask.setVisible(true); 
                JLabel k = new JLabel("Qual o numero de Empresas pretendido?");
                JTextField tf = new JTextField(5);
                JButton click = new JButton("Obter Top X");
                ask.add(k);ask.add(tf);ask.add(click);
                click.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                         int n;
                         try
                         {
                             n = Integer.parseInt(tf.getText());
                         }catch(NumberFormatException x) {
                             JOptionPane.showMessageDialog(null,"Not a Number");
                             return;
                         }
                         Querys xx = new Querys();
                         NavigableMap<Integer,Long> t10 = xx.getXEmpresas().descendingMap();
                         int i = 0;
                
                         JFrame result = new JFrame("Top X");
                         result.setLayout(new FlowLayout());
                         result.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                         result.setSize(300,400);
                         result.setLocation(300,300);
                         result.setVisible(true); 
                
                         for(Map.Entry<Integer,Long> mp : t10.entrySet())
                         {
                             BaseDados bd = new BaseDados();
                             HashMap<Long,C_Colectivo> cc = bd.getColectivo();
                             StringBuilder sb = new StringBuilder();
                             int x = i+1;
                             if(i == n)
                                break;
                             else
                                sb.append(x + "º=> Nif:" + mp.getValue() + " || Total Faturas:" + mp.getKey() + " || Total Deduções:" + cc.get(mp.getValue()).getDAc().getDTotal() + ".\n");
                             i++;
                    
                             JLabel insert = new JLabel(sb.toString());
                             result.add(insert);
                         }                            
                    }
                });
            }
        }
    }
}

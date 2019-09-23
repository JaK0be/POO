/**
 * Classe que define a janela de Login da Interface Gráfica
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.HashMap;

public class Interface extends JFrame
{
    JButton ind, col, admin;
    JTextField nif, pass;
    JLabel lNif, lPass, error;
    
    /**
     * Construtor Gráfico do Login
     */
    public Interface()
    {
        setLayout(new FlowLayout());
        
        lNif = new JLabel("NIF: ");
        add(lNif);
        
        nif = new JTextField(10);
        add(nif);
        
        lPass = new JLabel("Password: ");
        add(lPass);
        
        pass = new JTextField(10);
        add(pass);
        
        ind = new JButton("Individual");
        add(ind);
        
        col = new JButton("Coletivo");
        add(col);
        
        admin = new JButton("Admistrador");
        add(admin);
        
        error = new JLabel("");;
        add(error);
        
        event a = new event();
        ind.addActionListener(a);
        col.addActionListener(a);
        admin.addActionListener(a);
    }
    
    /**
     * Sub-Classe que define o que acontece quando fazemos login no sistema
     */
    public class event implements ActionListener {
        /**
         * Função que define o que acontece quando fazemos login no sistema
         */
        public void actionPerformed(ActionEvent a)
        {
            long n;
            String p;
            BaseDados bd = new BaseDados();
            HashMap<Long,C_Individual> ind = bd.getIndividual();
            HashMap<Long,C_Colectivo> col = bd.getColectivo();
            
            try
            {
               n = Long.parseLong(nif.getText());
            }catch(NumberFormatException e) {
                error.setText("Não é um número");
                error.setForeground(Color.RED);
                return;
            }
            
            p = pass.getText();
            String tipo = a.getActionCommand();
            
            if(tipo.equals("Individual"))
            {
                if(ind.containsKey(n))
                {
                    C_Individual cont = ind.get(n);
                    if(n == cont.getNif() && p.equals(cont.getPass()))
                    {
                        InterIndiv ad = new InterIndiv(Interface.this,n);
                        ad.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                        ad.setSize(500,100);
                        ad.setLocation(600,300);
                        ad.setVisible(true);
                        error.setText("Loged In -> Individual");
                        error.setForeground(Color.BLUE);
                    }      
                }else 
                {
                    error.setText("Not able to log in");
                    error.setForeground(Color.RED);                    
                }
            }else if(tipo.equals("Coletivo"))
                  {
                    if(col.containsKey(n)){
                        C_Colectivo cCol = col.get(n);
                        if(n == cCol.getNif() && p.equals(cCol.getPass()))
                        {
                            InterCollec ad = new InterCollec(Interface.this,n);
                            ad.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                            ad.setSize(500,100);
                            ad.setLocation(600,300);
                            ad.setVisible(true);
                            error.setText("Loged In -> Coletivo");
                            error.setForeground(Color.BLUE);
                        }
                    }else 
                    {
                        error.setText("Not able to log in");
                        error.setForeground(Color.RED);                    
                    }  
                  }else if(tipo.equals("Admistrador"))
                  {
                    if(n==333333 && p.equals("Password"))
                    {
                        Administrador ad = new Administrador(Interface.this);
                        ad.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                        ad.setSize(300,80);
                        ad.setLocation(600,300);
                        ad.setVisible(true);
                        error.setText("Loged In -> Admin");
                        error.setForeground(Color.BLUE);
                    }else 
                    {
                        error.setText("Not able to log in");
                        error.setForeground(Color.RED);                    
                    }  
                  }
        }
    }
    
    /**
     * Função que executa a Interface Gráfica
     */
    public void begin(){
        Interface f = new Interface();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        f.setSize(950,100);
        f.setLocation(300,300);
        f.setTitle("JavaFactura");
    }
}

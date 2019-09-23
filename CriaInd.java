import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.HashMap;
/**
 * Classe que define a Janela Relativa ao Menu de Adição de um Contribuinte Individual
 */
public class CriaInd extends JDialog
{
    JLabel lnif, lpassword, lemail, lnome, lcPostal, lnPorta, lmorada, lregiao, error;
    JTextField nif, password, email, nome, cPostal, nPorta, morada, regiao;
    JButton criar;
    /**
     * Construtor Gráfico do Menu de Adição de um Contribuinte Individual
     */
    public CriaInd(JDialog frame)
    {
        super(frame,"Novo Contribuinte Individual");
        setLayout(new FlowLayout());
        
        lnif = new JLabel("NIF");
        add(lnif);
        
        nif = new JTextField(10);
        add(nif);
        
        lpassword = new JLabel("Password");
        add(lpassword);
        
        password = new JTextField(15);
        add(password);
        
        lemail = new JLabel("E-mail");
        add(lemail);
        
        email = new JTextField(25);
        add(email);
        
        lnome = new JLabel("Nome");
        add(lnome);
        
        nome = new JTextField(15);
        add(nome);
        
        lcPostal = new JLabel("Código Postal");
        add(lcPostal);
        
        cPostal = new JTextField(10);
        add(cPostal);
        
        lnPorta = new JLabel("Número de Porta");
        add(lnPorta);
        
        nPorta = new JTextField(8);
        add(nPorta);
        
        lmorada = new JLabel("Morada");
        add(lmorada);
        
        morada = new JTextField(23);
        add(morada);
        
        lregiao = new JLabel("Região");
        add(lregiao);
        
        regiao = new JTextField(10);
        add(regiao);
        
        criar = new JButton("Criar");
        add(criar);
        
        error = new JLabel("");
        add(error);
        
        event a = new event();
        criar.addActionListener(a);
    }  
    
    /**
     * Sub-Classe que cria um novo Contribuinte Individual
     */
    public class event implements ActionListener{
        /**
         * Função que cria um novo Contribuinte Individual
         */
        public void actionPerformed(ActionEvent a)
        {
            long niF=-1;
            String mail;
            String nom, morad, regia, pass;
            int cP=-1, nP=-1;
            
            BaseDados bd = new BaseDados(); 
            HashMap<Long,C_Individual> ind = bd.getIndividual();
            HashMap<Long,C_Colectivo> col = bd.getColectivo();
            
            try
            {
                niF = Long.parseLong(nif.getText());
            }catch(NumberFormatException e)
            {                
               error.setText("Insira um número");
               error.setForeground(Color.RED);
            }
            
            mail = email.getText();
            nom = nome.getText();
            
            try
            {
                cP = Integer.parseInt(cPostal.getText());
            }catch(NumberFormatException e)
            {                
               error.setText("Insira um número");
               error.setForeground(Color.RED);
            }
            
            try
            {
               nP = Integer.parseInt(nPorta.getText());
            }catch(NumberFormatException e)
            {                
               error.setText("Insira um número");
               error.setForeground(Color.RED);
            }
            
            morad = morada.getText();
            regia = regiao.getText();
            pass = password.getText();
            
            if(niF != -1 && (col.containsKey(niF) || ind.containsKey(niF)))
            {
                error.setText("NIF já existe");
                error.setForeground(Color.RED);
            }else if(regia.equals("Litoral")==false && regia.equals("Interior")==false && regia.equals("Ilhas")==false)
                {
                    error.setText("Região inválida. Escolha entre Litoral, Interior ou Ilhas");
                    error.setForeground(Color.RED);
                }else
                    {
                        C_Individual ci= new C_Individual(niF, mail, nom, cP, nP, morad, regia, pass);
                        bd.addIndividual(ci);
                        error.setText("Contribuinte adicionado");
                        error.setForeground(Color.BLUE);                        
                    }
        }
    }
}

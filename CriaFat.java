import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.HashMap;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Classe que define a Janela Relativa ao Menu de criação de uma Nova Fatura
 */
public class CriaFat extends JDialog
{   JLabel ldescDespesa, lpreco, lnifC, error;
    JTextField descDespesa, preco, nifC;
    JButton cria;
    private static long nif;
    /**
     * COnstrutor Gráfico da Janela Relativa ao Menu de criação de uma Nova Fatura
     */
    public CriaFat(JDialog frame, long n)
    {
        super(frame,"Nova Fatura");
        setLayout(new FlowLayout());
        
        nif = n;
        
        lnifC = new JLabel("NIF Cliente");
        add(lnifC);
        
        nifC = new JTextField(10);
        add(nifC);
        
        ldescDespesa = new JLabel("Descrição Despesa");
        add(ldescDespesa);
        
        descDespesa = new JTextField(25);
        add(descDespesa);
        
        lpreco = new JLabel("Valor Fatura");
        add(lpreco);
        
        preco = new JTextField(10);
        add(preco);
        
        cria = new JButton("Cria Fatura");
        add(cria);
        
        error = new JLabel("");
        add(error);
        
        event a = new event();
        cria.addActionListener(a);
    }
/**
 * Sub-Classe que cria uma Nova Fatura
 */
public class event implements ActionListener{
    /**
     * Função que cria uma Nova Fatura
     */
    public void actionPerformed(ActionEvent a)
        {
           String cliente, descricao;
           double prec = -1;
           long nC = -1;
           LocalDate datA;
           int tipo; //tipo de contribuinte. 1 se for individual. 2 se for coletivo
           tipo = 0;
           
           BaseDados bd = new BaseDados(); 
           HashMap<Long,C_Individual> ind = bd.getIndividual();
           HashMap<Long,C_Colectivo> col = bd.getColectivo();
           HashMap<Long,Faturas> fat = bd.getFatura();
           
           try
           {
              prec = Double.parseDouble(preco.getText());
           }catch(NumberFormatException e)
           {
               error.setText("Não é um preço válido");
               error.setForeground(Color.RED);
           }
           
           try
           {
               nC = Long.parseLong(nifC.getText());
           }catch(NumberFormatException e)
           {
               error.setText("Não é um NIF válido");
               error.setForeground(Color.RED);
           }
           
           descricao = descDespesa.getText();
           
           if(nC !=-1 && ind.containsKey(nC)){
               tipo = 1;
               C_Individual remetente = ind.get(nC);
               cliente = remetente.getNome();
            }
           else if(nC !=-1 && col.containsKey(nC)){
               tipo = 2;
               C_Colectivo remetente = col.get(nC);
               cliente = remetente.getNome();
            }
           
           C_Colectivo emissor = col.get(nif);
           String nomeE = emissor.getNome();
           Despesa servicos = emissor.getDSP();
           int nServicos = servicos.getnumDesp();
           int despesa = 2000;
           ArrayList<Integer> desp = servicos.getDesp();
           Fatura fatura;
           Faturas x;
           DedAcu dx = new DedAcu();
           
           if(nif != nC)
           {
               if(tipo == 1){
                   if(nServicos == 1)
                   {
                       despesa = desp.get(0);//Tira código de serviço se a empresa apenas fornece um tipo de serviços
                       fatura = new Fatura(nif, nomeE, LocalDate.now() , nC, descricao, prec , despesa, true);
                       C_Individual remetente = ind.get(nC);
                       dx = remetente.getDAc();
                       dx.add(despesa, (prec * fatura.getDSPD())); 
                       
                       bd.addIndividual(remetente);
                   }else{
                       fatura = new Fatura(nif, nomeE, LocalDate.now() , nC, descricao, prec , despesa, false);
                    }
                   
                   if(fat.containsKey(nC))
                   {
                       x = fat.get(nC);
                       x.addFatura(fatura);
                   }else{
                       x = new Faturas(nC);
                       x.addFatura(fatura);
                    }
                   
                   bd.addFatura(x);
                   error.setText("Fatura adicionada");
                   error.setForeground(Color.BLUE);
                   }else if(tipo==2)
                            {
                               if(nServicos == 1)
                               {
                                   despesa = desp.get(0);//Tira código de serviço se a empresa apenas fornece um tipo de serviços
                                   fatura = new Fatura(nif, nomeE, LocalDate.now() , nC, descricao, prec , despesa, true);
                                   C_Colectivo remetente = col.get(nC);
                                   dx = remetente.getDAc();
                                   dx.add(despesa, (prec * fatura.getDSPD()));
                                   
                                   bd.addColectivo(remetente);
                               }else{
                                   fatura = new Fatura(nif, nomeE, LocalDate.now() , nC, descricao, prec , despesa, false);
                                }
                               
                               if(fat.containsKey(nC))
                               {
                                   x = fat.get(nC);
                                   x.addFatura(fatura);
                               }else{
                                   x = new Faturas(nC);
                                   x.addFatura(fatura);
                                }
                               
                               bd.addFatura(x);
                               error.setText("Fatura adicionada");
                               error.setForeground(Color.BLUE);                            
                            }
                         else
                           {    
                               error.setText("Contribuinte não existe, fatura não adicionada");
                               error.setForeground(Color.RED);
                            }
           }else
           {
               error.setText("Contribuinte iguais, fatura não adicionada");
               error.setForeground(Color.RED);
           }
        }
    }
}

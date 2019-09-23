import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
/**
 * Classe que define a Janela Relativa ao Menu de Adição de Contribuinte ao Agregado Familiar
 */
public class AddMembro extends JDialog
{
    JLabel lnif1, lnif2, error;
    JTextField nif1, nif2;
    JButton adicionar;
    
    /**
     * Construtor Gráfico de Menu de Adição de Contribuinte ao Agregado Familiar
     */
    public AddMembro(JDialog frame)
    {
        super(frame,"Adicionar Membro ao Agregado Familiar");
        setLayout(new FlowLayout());
        
        lnif1= new JLabel("NIF 1");
        add(lnif1);
        
        nif1 = new JTextField(10);
        add(nif1);
        
        lnif2= new JLabel("NIF 2");
        add(lnif2);
        
        nif2 = new JTextField(10);
        add(nif2);
        
        adicionar = new JButton("Adicionar");
        add(adicionar);
        
        error = new JLabel("");
        add(error);
        
        event e = new event();
        adicionar.addActionListener(e);
    }
    
    /**
     * Subclasse que adiciona um Contribuinte ao Agregado Familiar
     */
    public class event implements ActionListener {
        public void actionPerformed(ActionEvent a)
        {
            BaseDados bd = new BaseDados(); 
            HashMap<Long,C_Individual> ind = bd.getIndividual();
            
            long niF1 = -1, niF2 = -1;
            
            try
            {
                niF1 = Long.parseLong(nif1.getText());
            }catch(NumberFormatException e){
                error.setText("NIF 1 não é um número");
                error.setForeground(Color.RED);
            }
            
            try
            {
                niF2 = Long.parseLong(nif2.getText());
            }catch(NumberFormatException e){
                error.setText("NIF 2 não é um número");
                error.setForeground(Color.RED);
            }
            
            if(niF1 != niF2)
            {   
                if(niF1!=-1 && ind.containsKey(niF1) && niF2!=-1 && ind.containsKey(niF2))
                {
                    C_Individual cI1 = ind.get(niF1);
                    C_Individual cI2 = ind.get(niF2);
                    ArrayList<Long> b = new ArrayList<>();
                    b = cI1.getNifAgreg();
                    
                    boolean belongs = b.contains(niF2);
                    if(!belongs)
                    { 
                        boolean b1 = cI1.adiciona(niF2);
                        boolean b2 = cI2.adiciona(niF1);
                                        
                        if(b1 == true && b2 == true)
                        {
                            ArrayList<Long> x1 = new ArrayList<>();
                            ArrayList<Long> x2 = new ArrayList<>();
                            x1 = cI1.getNifAgreg();
                            x2 = cI2.getNifAgreg();
                            double c1=1,c2=1;
                            
                            if(x1.size()>6) {c1 = 2;}
                            if(x2.size()>6) {c2 = 2;}
                            
                            cI1.setCoef(c1);
                            cI2.setCoef(c2);
                            
                            bd.addIndividual(cI1);
                            bd.addIndividual(cI2);
                            error.setText("Atualização Concluída");
                            error.setForeground(Color.BLUE);
                        }
                    }else
                    {
                        error.setText("Contribuintes já pertencem ao mesmo agregado.");
                        error.setForeground(Color.RED);
                    }
                }else if(niF1!=-1 && niF2!=-2 && ind.containsKey(niF1))
                        {
                            error.setText("NIF 2 Inexistente");
                            error.setForeground(Color.BLUE);
                        }else if(niF1!=-1 && niF2!=-2 && ind.containsKey(niF2))
                                {
                                    error.setText("NIF 1 Inexistente");
                                    error.setForeground(Color.BLUE);
                                }
            }else
            {
                error.setText("São o mesmo contribuinte, Erro!");
                error.setForeground(Color.RED);
            }
        }
    }
}

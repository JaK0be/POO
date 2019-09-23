import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.HashMap;
/**
 * Classe que define a Janela Relativa ao Menu de Adição de um Serviço a uma dada Empresa
 */
public class AddServiço extends JDialog
{
    JLabel lnif, lcode, codigos, error;
    JTextField nif, code;
    JButton adiciona;
    /**
     * Construtor Gráfico do Menu de Adição de um Serviço a uma dada Empresa
     */
    public AddServiço(JDialog frame)
    {
        super(frame,"Adicionar Serviço a Empresa");
        setLayout(new FlowLayout());
        
        lnif = new JLabel("NIF Empresa");
        add(lnif);
        
        nif = new JTextField(10);
        add(nif);
        
        lcode = new JLabel("Código Serviço a Adicionar");
        add(lcode);
        
        code = new JTextField(10);
        add(code);
        
        adiciona = new JButton("Adiciona Serviço");
        add(adiciona);
        
        StringBuilder sb = new StringBuilder();
        sb.append("<html>Códigos: <br>");
        sb.append("Despesas Gerais Familiares => 0 <br>");
        sb.append("Educação => 10 <br>");
        sb.append("Habitação => 11 <br>");
        sb.append("Lares => 100 <br>");
        sb.append("Reparação de Automoveis => 101 <br>");
        sb.append("Reparação de Motociclos => 110 <br>");
        sb.append("Restauração e Alojamento => 111 <br>");
        sb.append("Cabeleireiros => 1000 <br>");
        sb.append("Veterinários => 1001 <br>");
        sb.append("Passes Mensais => 1010 <br>");
        
        codigos = new JLabel("" + sb);
        add(codigos);
        
        error = new JLabel("");
        add(error);
        
        event e = new event();
        adiciona.addActionListener(e);
    }
    
    /**
     * Subclasse que adicona um serviço a uma Empresa
     */
    public class event implements ActionListener {
        /**
         * Função que adiciona um serviço a uma Empresa
         */
        public void actionPerformed(ActionEvent a)
        {
            long niF = -1;
            int cdigo = -1;
            
            try
            {
                niF = Long.parseLong(nif.getText());
            }catch(NumberFormatException e){
                error.setText("NIF inserido não é um número");
                error.setForeground(Color.RED);
            }
            
            try
            {
                cdigo = Integer.parseInt(code.getText());
            }catch(NumberFormatException e){
                error.setText("Código inserido não é um número");
                error.setForeground(Color.RED);
            }
            
            BaseDados bd = new BaseDados(); 
            HashMap<Long,C_Colectivo> col = bd.getColectivo();
            
            if(niF != -1 && col.containsKey(niF) && (cdigo==0 || cdigo==10 || cdigo==11 || cdigo==100 || cdigo==101 || cdigo==110 || cdigo==111 || cdigo==1000 || cdigo==1000 || cdigo==1001 || cdigo==1010))
            {
                C_Colectivo x = col.get(niF);
                Despesa dx = new Despesa();
                dx = x.getDSP();
                
                dx.addDsp(cdigo);
                x.setDSP(dx);
                bd.addColectivo(x);
                error.setText("Serviço Adicionado");
                error.setForeground(Color.BLUE);
            }else if(!col.containsKey(niF) || niF==-1)
            {
                error.setText("NIF não existente");
                error.setForeground(Color.RED);
            }else
            {
                error.setText("Código Inválido");
                error.setForeground(Color.RED);
            }
        }
    }
}

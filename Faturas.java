/**
 * Classe que define como são armazenadas Faturas de um Contribuinte no Sistema
 */

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

public class Faturas implements java.io.Serializable
{
    private long nifContribuinte;
    private ArrayList<Fatura> faturas;
    
    /**
     * Construtor parametrizado
     * 
     * @param (nif) Nif do Contribuinte
     */
    public Faturas(long nif)
    {
        nifContribuinte = nif;
        faturas = new ArrayList<>();
    }
    
    /**
     * Retorna o nif do contribuinte a que pertencem essas Faturas
     */
    public long getNif()
    {
        return this.nifContribuinte;
    }
    
    /**
     * Altera o Contribuinte a que certas Faturas pertencem
     * 
     * @param (nif) Novo proprietário Faturas
     */
    public void setNif(long nif)
    {
        this.nifContribuinte = nif;
    }
    
    /**
     * Retorna um ArrayList com todas as Faturas de um dado Contribuinte
     */
    public ArrayList<Fatura> getFaturas()
    {
        return this.faturas;
    }
    
    /**
     * Adiciona um conjunto de Faturas
     * 
     * @param (faturas) Conjunto a ser adicionado
     */
    public void setFaturas(ArrayList<Fatura> faturas)
    {
        this.faturas = faturas;
    }
    
    /**
     * Adiciona Fatura a um dado Contribuinte
     * 
     * @param (ftr) Fatura a adicionar
     */
    public void addFatura(Fatura ftr)
    {
        this.faturas.add(ftr);
    }
    
    /**
     * Função toString
     */
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        
        sb.append("NIF Contribuinte: ");
        sb.append(this.nifContribuinte + "\n");
        sb.append("Faturas do Contribuinte: \n");
        sb.append(faturas.toString());
        
        return sb.toString();
    }
    
    /**
     * Método que vai devolver todas as faturas ainda não validadas de um certo contribuinte
     */
    public ArrayList<Fatura> getNonValid()
    {
        ArrayList<Fatura> ret = new ArrayList<>();
        
        Iterator<Fatura> it = this.faturas.iterator();
        while(it.hasNext())
        {
            Fatura ft = (Fatura) it.next();
            if(ft.getVal() == false)
            {
                ret.add(ft);
            }
        }
        return ret;
    }
    
}

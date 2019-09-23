/**
 * Classe que define que serviços uma Empresa presta
 */

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Despesa implements java.io.Serializable
{
    private ArrayList<Integer> despesas;
    
    /**
     * Construtor Vazio
     */
    public Despesa()
    {
        this.despesas = new ArrayList<>();
    }
    
    /**
     * Construtor Cópia
     * 
     * @param (c) Despesa a ser copiado
     */
    public Despesa(Despesa c)
    {
        this.despesas = c.getDesp();
    }
   
    /**
     * Retorna o número de serviços que uma empresa presta
     */
    public int getnumDesp()
    {
        return (this.despesas.size());
    }
    
    /**
     * Retorna os serviços que uma empresa  presta
     */
    public ArrayList<Integer> getDesp()
    {
        return (ArrayList) this.despesas.clone();
    }
    
    /**
     * Adiciona Serviçps a uma dada empresa
     * 
     * @param (dsp) Serviços a serem adicionados
     */
    public void setDesp(ArrayList<Integer> dsp)
    {
        this.despesas = dsp;
    }
    
    /**
     * Retorna a percentagem possível de reduzir de um dado Serviço 
     * 
     * @param (dsp) Serviço
     */
    public double getDesconto(int dsp)
    {
        if((dsp == 0) || (dsp == 1)||(dsp == 101)||(dsp == 110)||(dsp == 111)||(dsp == 1000)||(dsp == 1001))
            return 0.15;
        if(dsp == 100)
            return 0.25;
        if(dsp == 10)
            return 0.3;
        if(dsp == 11)
            return 0.35;;
        if(dsp == 1000)
            return 1;
            
        return 0;
    }
    
    /**
     * Função que verifica se um dado serviço não pertence aos serviços prestados por uma dada Empresa
     * 
     * @param (dsp) Serviço
     */
    public boolean notBelong(int dsp)
    {
        if(this.despesas.size() == 0)
            return true;
        
        if(this.despesas.contains(dsp))
            return false;
        
        return true;
    }
    
    /**
     * Adiciona um serviço a uma dada empresa
     * 
     * @param (dsp) Serviço a ser adicionado
     */
    public void addDsp(int dsp)
    {
        if((notBelong(dsp)) && this.getnumDesp() <= 11)
        {
            this.despesas.add(dsp);
            Collections.sort(this.despesas);
        }
    }
    
    /**
     * Remove um serviço a uma dada Empresa (Isto se essa Empresa prestar o serviço que pretendemos remover)
     * 
     * @param (dsp) Serviço a remover
     */
    public void remDsp(int dsp)
    {     
        if(!notBelong(dsp))
        {
            this.despesas.remove(dsp);
            Collections.sort(this.despesas);
        }
    }
    
    /**
     * Função toString
     */
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        int c = 0;
        
        sb.append("Numero de despesas participantes: ");
        sb.append(this.getnumDesp() + "\n");
        sb.append("Codigos das respetivas despesas: ");
        sb.append(this.despesas.toString());
        sb.append("\n"); 
        
        return sb.toString();
    }
    
    /**
     * Função Equals
     */
    public boolean equals(Object o)
    {
        if(o==this) return true;
        if(o==null || o.getClass() != this.getClass() ) return false;
        Despesa c = (Despesa) o;
        boolean ret = c.getnumDesp() == this.getnumDesp() && this.despesas.equals(c.despesas);
        return ret;
    }
    
    /**
     * Função clone
     */
    public Despesa clone()
    {
        return new Despesa(this);
    }
    
    /**
     * Função que imprime noo ecrã os códigos correspondentes a cada serviço
     */
    public String dspString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Códigos: \n");
        sb.append("Despesas Gerais Familiares => 0 \n");
        sb.append("Saude => 1 \n");
        sb.append("Educação => 10 \n");
        sb.append("Habitação => 11 \n");
        sb.append("Lares => 100 \n");
        sb.append("Reparação de Automoveis => 101 \n");
        sb.append("Reparação de Motociclos => 110 \n");
        sb.append("Restauração e Alojamento => 111 \n");
        sb.append("Cabeleireiros => 1000 \n");
        sb.append("Veterinários => 1001 \n");
        sb.append("Passes Mensais => 1010 \n");
        sb.append("\n"); 
        
        return sb.toString();
    }
    
    /**
     * Função que imprime no ecrã os serviços prestados por uma dada Empresa
     */
    public String empString()
    {
        StringBuilder sb = new StringBuilder();
        
        Iterator<Integer> it = this.despesas.iterator();
        while(it.hasNext())
        {
            int codigo = (int) it.next();
            if(codigo == 0)
                sb.append("|| Despesas Gerais Familiares => 0 ||");
            if(codigo == 1)
                sb.append("|| Saude => 1 ||");
            if(codigo == 10)
                sb.append("|| Educaçao => 10 ||");
            if(codigo == 11)
                sb.append("|| Habitaçao => 11 ||");    
            if(codigo == 100)
                sb.append("|| Lares => 100 ||");
            if(codigo == 101)
                sb.append("|| Reparação de Automoveis => 101 ||");
            if(codigo == 110)
                sb.append("|| Reparação de Motociclos => 110 ||");
            if(codigo == 111)
                sb.append("|| Restauração e Alojamento => 111 ||");
            if(codigo == 1000)
                sb.append("|| Cabeleireiros => 1000 ||");
            if(codigo == 1001)
                sb.append("|| Veterinários => 1001 ||");
            if(codigo == 1010)
                sb.append("|| Passes Mensais => 1010 ||");
        }
        return sb.toString();
    }
}















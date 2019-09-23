
/**
 * Write a description of class Guardar_dados here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Iterator;
import java.time.LocalDate;
import java.util.Collections;


public class BaseDados implements java.io.Serializable
{
    private HashMap<Long,C_Individual> hashIndividuais;
    private HashMap<Long,C_Colectivo> hashColectivos;
    private HashMap<Long,Faturas> hashFaturas;
    
    public class sortFatura implements Comparable<sortFatura>
    {
        private Fatura f;
        private int sort;
        
        public sortFatura(Fatura f,int sort)
        {
            this.f = f;
            this.sort = sort;
        }
        
        public Fatura getFat(){return this.f;}
        
        public int compareTo(sortFatura sf) 
        {
            int ret = 0;
            Fatura ft = sf.getFat();
            if(sort == 1)
            {
                LocalDate compareData = (LocalDate) ((Fatura) ft).getData(); 
        
                ret = compareData.compareTo(this.f.getData()); 
            }
            if(sort == 2)
            {
                int compareValue = (int) ((Fatura) ft).getCost(); 
        
                ret = (compareValue - (int) this.f.getCost()); 
            }         
            return ret;
        }
    }
    
    /**
     * Construtor vazio
     */
    public BaseDados()
    {
        hashIndividuais = new HashMap<>();
        hashColectivos = new HashMap<>();
        hashFaturas = new HashMap<>();
    }
    
    /**
     * Função que vai escrever atraves de serialização ambas as HashTables num ficheiro 
     */
    private void WriteData()
    {
        try {
            FileOutputStream fileOut = new FileOutputStream("ficheiros/contribuintes.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this.hashIndividuais);
            out.writeObject(this.hashColectivos);
            out.writeObject(this.hashFaturas);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }    
    }
    
    /**
     * Método que vai decifrar o ficheiro com a informação das hash's
     */
    private void LoadData()
    {
        File dir = new File("ficheiros/contribuintes.ser");
        if(dir.exists())
        {
            try {
                FileInputStream fileIn = new FileInputStream("ficheiros/contribuintes.ser");
                ObjectInputStream in = new ObjectInputStream(fileIn);
                this.hashIndividuais = (HashMap) in.readObject();
                this.hashColectivos = (HashMap) in.readObject();
                this.hashFaturas = (HashMap) in.readObject();
                in.close();
                fileIn.close();
                
            }catch (IOException i) {
                i.printStackTrace();
                return;
            }catch (ClassNotFoundException c) {
                System.out.println("Class not found exception");
                c.printStackTrace();
                return;
            }
        }
    }
    
    /**
     * Método usada para eliminar todos os conteudos do ficheiro
     */
    public void FileWipe()
    {
        HashMap<Long,C_Individual> hashInd = new HashMap<>();
        HashMap<Long,C_Colectivo> hashColec = new HashMap<>();
        HashMap<Long,Faturas> hashFatu = new HashMap<>();
        try {
            FileOutputStream fileOut = new FileOutputStream("ficheiros/contribuintes.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(hashInd);
            out.writeObject(hashColec);
            out.writeObject(hashFatu);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }   
    }
    
    /**
     * Método que permite adicionar um novo contribuinte individual á sua HashMap correspondente
     * @params insert o contribuinte que se pretende adicionar
     */
    public void addIndividual(C_Individual insert)
    {
        this.LoadData();
        long nif = insert.getNif();
        this.hashIndividuais.put(nif,insert);
        this.WriteData();
    }
    
    /**
     * Método que permite adicionar um novo contribuinte colectivo á sua HashMap correspondente
     * @params insert o contribuinte que se pretende adicionar
     */
    public void addColectivo(C_Colectivo insert)
    {
        this.LoadData();
        long nif = insert.getNif();
        this.hashColectivos.put(nif,insert);
        this.WriteData();
    }
    
    /**
     * Método que permite adicionar um novo conjunto de Faturas á sua HashMap correspondente
     * @params insert o conjunto que se pretende adicionar
     */
    public void addFatura(Faturas insert)
    {
        this.LoadData();
        long nif = insert.getNif();
        this.hashFaturas.put(nif,insert);
        this.WriteData();
    }
    
    /**
     * Método usado para se obter os contribuintes individuais
     * @returns Um  HashMap<Long,C_Individual> com todos os contribuintes individuais
     */
    public HashMap<Long,C_Individual> getIndividual()
    {
        this.LoadData();
        return this.hashIndividuais;
    }
    
    /**
     * Método usado para se obter os contribuintes colectivos
     * @returns Um  HashMap<Long,C_Colectivo> com todos os contribuintes colectivos
     */
    public HashMap<Long,C_Colectivo> getColectivo()
    {
        this.LoadData();
        return this.hashColectivos;
    }
    
    /**
     * Método usado para se obter as faturas
     * @returns Um  HashMap<Long,Faturas> com todas as faturas emitidas
     */
    public HashMap<Long,Faturas> getFatura()
    {
        this.LoadData();
        return this.hashFaturas;
    }
    
    public ArrayList<Fatura> getFaturaByNif(long nif,int sort)//sort = 1 => Organizar decrescente data || sort = 2 => Organizar decrescente valor
    {
        ArrayList<sortFatura> ret = new ArrayList<>();
        ArrayList<Fatura> ret2 = new ArrayList<>();
        this.LoadData();
        Iterator it = this.hashFaturas.entrySet().iterator();
        while(it.hasNext())
        {
            Map.Entry pair = (Map.Entry)it.next();
            Faturas ft = (Faturas) pair.getValue();
            Iterator<Fatura> ite = ft.getFaturas().iterator();
            while(ite.hasNext())
            {
                Fatura f = (Fatura) ite.next();
                sortFatura insert = new sortFatura(f,sort);
                if(f.getNifE() == nif)
                {
                    ret.add(insert);
                    Collections.sort(ret);
                }
            }
        }
        
        Iterator<sortFatura> its = ret.iterator();
        while(its.hasNext())
        {
            sortFatura ssf = (sortFatura) its.next();
            Fatura f = ssf.getFat();
            ret2.add(f);
        }
        
        return ret2;
    }
    
    /**
     * Método usado para se atualizar as faturas
     */
    public void setFatura(HashMap<Long,Faturas> ft)
    {
        this.hashFaturas = ft;
        this.WriteData();
    }
    
    /**
     * Função que vai verificar se um certo nif pertence á base de dados
     * @params nif O Nif do contribuinte que esta a tentar fazer login
     * @params password A password do contribuinte que esta a tentar fazer login
     * @params UserType É 1 se for contribuinte individual e 2 se for contribuinte colectivo
     * @returns True ou False dependendo se existe ou não
     */
    public boolean checkCredenciais(long nif,String password,int UserType)
    {
        this.LoadData();
        
        if(UserType == 1)
        {
            if(this.hashIndividuais.containsKey(nif))
            {
                C_Individual contribuinte = this.hashIndividuais.get(nif);
                if(contribuinte.getPass().compareTo(password) == 0)
                {
                    return true;
                }   
            }
        }
        else
        {
            if(this.hashColectivos.containsKey(nif))
            {
                C_Colectivo contribuinte = this.hashColectivos.get(nif);
                if(contribuinte.getPass().compareTo(password) == 0)
                {
                    return true;
                }
            }
        }
        return false;
    }
    
    public void checkContent()
    {
        this.LoadData();
        System.out.println(this.hashIndividuais.toString());
        System.out.println(this.hashColectivos.toString());
        System.out.println(this.hashFaturas.toString());
    }
}

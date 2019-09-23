import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Set;
import java.util.HashSet;
import java.util.TreeMap;

public class Querys
{
    /**
     * Metodo que vai obter todos os contribuintes que foram faturados por uma empresa
     * 
     * @param (nifE) O nif da empresa da qual se quer saber os contribuintes
     * 
     * @returns Uma ArrayList com os nif's dos contribuintes
     */
    public ArrayList<Long> getContribuintesEmp(long nifE)
    {
        ArrayList<Long> ret = new ArrayList<>();
        BaseDados bd = new BaseDados();
        HashMap<Long,Faturas> ff = bd.getFatura();
        
        Iterator it = ff.entrySet().iterator();
        while(it.hasNext())
        {
            Map.Entry pair = (Map.Entry) it.next();
            Faturas fs = (Faturas) pair.getValue();
            ArrayList<Fatura> fa = fs.getFaturas();
            Iterator ite = fa.iterator();
            while(ite.hasNext())
            {
                Fatura fat = (Fatura) ite.next();
                if(fat.getNifE() == nifE)
                {
                    long insert = fat.getNifC();
                    ret.add(insert);
                }
            }
        }
        
        Set<Long> hs = new HashSet<>();
        hs.addAll(ret);
        ret.clear();
        ret.addAll(hs);
        
        return ret;
    }
    
    /**
     * Metodo que vai obter as faturas emitidas por uma empresa a um contribuinte num certo intervalo de tempo
     * 
     * @param (nifE) O nif da empresa 
     * @param (nifC) O nif do contribuinte
     * @param (begin) LocalDate que corresponde ao inicio do intervalo de tempo
     * @param (end) LocalDate que corresponde ao fim do intervalo de tempo
     * 
     * @return Um ArrayList com as Faturas desse contribuinte no intervalo de tempo
     */
    public ArrayList<Fatura> getFatContribuinte(long nifE,long nifC,LocalDate begin,LocalDate end)
    {
        ArrayList<Fatura> ret = new ArrayList<>();
        BaseDados bd = new BaseDados();
        HashMap<Long,Faturas> ff = bd.getFatura();
        Faturas fs = ff.get(nifC);
        ArrayList<Fatura> fa = fs.getFaturas();
        Iterator ite = fa.iterator();
        while(ite.hasNext())
        {
            Fatura fat = (Fatura) ite.next();
            LocalDate check = fat.getData();
            if(check.isAfter(begin) == true && check.isBefore(end) == true && fat.getNifE() == nifE)
                ret.add(fat);
          
         }         
        
        return ret;
    }
    
    /**
     * Metodo que vai obter as faturas emitidas por uma Empresa a um certo contribuinte ordenadas pelo seu preço total
     *
     * @param (nifE) O nif da empresa 
     * @param (nifC) O nif do contribuinte
     *
     * @return Um ArrayList com as faturas ordenadas pelo preço total de forma decrescente
     */
    public ArrayList<Fatura> getFatSorted(long nifE,long nifC)
    {
        ArrayList<Fatura> ret = new ArrayList<>();
        BaseDados bd = new BaseDados();
        HashMap<Long,Faturas> ff = bd.getFatura();
        Faturas fs = ff.get(nifC);
        Iterator it = fs.getFaturas().iterator();
        while(it.hasNext())
        {
            Fatura f = (Fatura) it.next();
            if(f.getNifE() == nifE)
                ret.add(f);
        }
        Collections.sort(ret);
        
        return ret;
    }
    
    /**
     * Metodo que vai transformar as datas lidas nos textfields em LocalDate
     *
     * @param (time) A String lida com as datas
     * @param (type) A data que se quer obter a partir da String // 1 = Inicio // 2 = Fim
     *
     * @return Uma LocalDate
     */
    public LocalDate getTime(String time,int type)
    {
        int ano1,ano2,mes1,mes2,dia1,dia2;
        String delet = "\\p{Punct}|\\p{Blank}|\\p{Alpha}";
        
        String[] obj = time.split(delet);
        String[] ret = new String[6];
        
        int t = 0;
        for(String s : obj)
        {
            if(!s.equals(""))
            {
                ret[t] = s;
                t++;
            }
        }
        
        if(type == 1)
            return (LocalDate.of(Integer.parseInt(ret[2]),Integer.parseInt(ret[1]),Integer.parseInt(ret[0])));
        else
            return(LocalDate.of(Integer.parseInt(ret[5]),Integer.parseInt(ret[4]),Integer.parseInt(ret[3])));
    }
    
    /**
     * Metodo que obter o quanto foi faturado por uma empresa nas suas compras dentro de um intervalo de tempo
     *
     * @param (nif) O nif da Empresa
     * @param (begin) LocalDate que corresponde ao inicio do intervalo de tempo
     * @param (end) LocalDate que corresponde ao fim do intervalo de tempo da String // 1 = Inicio // 2 = Fim
     *
     * @return Um double com o total faturado
     */
    public double getTotalFaturadoOut(long nif,LocalDate begin,LocalDate end)
    {
        double ret=0;
        
        BaseDados bd = new BaseDados();
        HashMap<Long,Faturas> ft = bd.getFatura();
        
        if(ft.containsKey(nif))
        {
            Iterator it = ft.get(nif).getFaturas().iterator();
            while(it.hasNext())
            {
                Fatura fat = (Fatura) it.next();
                LocalDate check = fat.getData();
                if(check.isAfter(begin) == true && check.isBefore(end) == true)
                    ret += fat.getCost();
            }
        }
        return ret;
    }

    /**
     * Metodo que obter o quanto foi faturado por uma empresa nas suas vendas dentro de um intervalo de tempo
     *
     * @param (nif) O nif da Empresa
     * @param (begin) LocalDate que corresponde ao inicio do intervalo de tempo
     * @param (end) LocalDate que corresponde ao fim do intervalo de tempo da String // 1 = Inicio // 2 = Fim
     *
     * @return Um double com o total faturado
     */
    public double getTotalFaturadoIn(long nif,LocalDate begin,LocalDate end)
    {
        double ret = 0;
        
        BaseDados bd = new BaseDados();
        HashMap<Long,Faturas> ff = bd.getFatura();
        
        Iterator it = ff.entrySet().iterator();
        while(it.hasNext())
        {
            Map.Entry pair = (Map.Entry) it.next();
            Faturas fs = (Faturas) pair.getValue();
            ArrayList<Fatura> fa = fs.getFaturas();
            Iterator ite = fa.iterator();
            while(ite.hasNext())
            {
                Fatura fat = (Fatura) ite.next();
                LocalDate check = fat.getData();
                if(fat.getNifE() == nif && check.isAfter(begin) == true && check.isBefore(end) == true)
                {
                    ret += fat.getCost();
                }
            }
        }
        
        return ret;
    }
    
    /**
     * Metodo que vai obter os top contribuintes que mais gastaram
     *
     * @return Um TreeMap com os contribuintes que mais gastaram ordenados
     */
    public TreeMap<Double,Long> getTop10()
    {
        TreeMap<Double,Long> ret = new TreeMap<>();
        
        BaseDados bd = new BaseDados();
        HashMap<Long,Faturas> ff = bd.getFatura();
       
        Iterator it = ff.entrySet().iterator();
        while(it.hasNext())
        {
            Map.Entry pair = (Map.Entry) it.next();
            long nif = (long) pair.getKey();
            double total = 0;
            
            Iterator ite = ((Faturas)pair.getValue()).getFaturas().iterator();            
            while(ite.hasNext())
                total += ((Fatura)ite.next()).getCost();
            
            ret.put(total,nif);    
        }
        return ret;
    }
    
     /**
     * Metodo que vai obter as Top Empresas com mais faturas
     *
     * @return Um TreeMap com as Empresas que mais faturas tem ordenadas
     */
    public TreeMap<Integer,Long> getXEmpresas()
    {
        TreeMap<Integer,Long> ret = new TreeMap<>();
        HashMap<Long,Integer> temp = new HashMap<>();
        
        BaseDados bd = new BaseDados();
        HashMap<Long,Faturas> ff = bd.getFatura();
        HashMap<Long,C_Colectivo> cc = bd.getColectivo();
        
        Iterator it = cc.entrySet().iterator();
        while(it.hasNext())
        {
            int total = 0;
            Map.Entry pair = (Map.Entry) it.next();
            long key = (long)pair.getKey();
            
            if(ff.containsKey(key))
                total += ff.get(key).getFaturas().size();
            
            Iterator ite = ff.entrySet().iterator();
            while(ite.hasNext())
            {
                Map.Entry pt = (Map.Entry) ite.next();
                Faturas ft = (Faturas) pt.getValue();
                if(ft.getNif() != key)
                {
                    Iterator its = ft.getFaturas().iterator();
                    while(its.hasNext())
                    {
                        if(((Fatura)its.next()).getNifE() == key)
                            total++;
                    }
                }
            }
            temp.put(key,total);
        }
        
        it = temp.entrySet().iterator();
        while(it.hasNext())
        {
            Map.Entry pair = (Map.Entry) it.next();
            long key = (long) pair.getKey();
            int value = (int) pair.getValue();
            ret.put(value,key);
        }
        
        System.out.println(ret.toString());
        System.out.println(temp.toString());
        
        return ret;
    }
}

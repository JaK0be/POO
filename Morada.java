/**
 * Classe que define como é representada uma Morada no Sistema
 */
public class Morada implements java.io.Serializable
{
    private int cPostal;
    private int nPorta;
    private String morada;
    private String regiao;
    
    /**
     * Construtor vazio
     */
    public Morada(){
        this.cPostal=0;
        this.nPorta=0;
        this.morada="-/-";
        this.regiao="-/-";
    }
    
    /**
     * Constructor parametrizado
     * 
     * @param (cP) Código Postal
     * @param (nP) Número de Porta
     * @param (m) Morada
     * @param (r) Região
     * 
     */
    public Morada(int cP, int nP,String m, String r){
        this.cPostal=cP;
        this.nPorta=nP;
        this.morada=m;
        this.regiao=r;
    }
    
    /**
     * Constructor cópia
     * 
     * @param (c) Morada
     */
    public Morada(Morada c){
        this.cPostal=c.getCp();
        this.nPorta=c.getNp();
        this.morada=c.getM(); 
        this.regiao=c.getR();
    }
    
    /**
     * retorna o código postal
     */
    public int getCp(){
        return this.cPostal;
    }
    
    /**
     * retorna o número de porta
     */
    public int getNp(){
        return this.nPorta;
    }
    
    /**
     * retorna a morada
     */
    public String getM(){
        return this.morada;
    }
    
    /**
     * retorna a região
     */
    public String getR()
    {
        return this.regiao;
    }
    
    /**
     * muda o código postal
     * 
     * @param (x) Novo Código Postal
     */
    public void setCp(int x){
        this.cPostal=x;
    }
    
    /**
     * muda o número de porta
     * 
     * @param (x) Novo Número de Porta
     */
    public void setNp(int x){
        this.nPorta=x;
    }
    
    /**
     * muda a morada
     * 
     * @param (x) Nova morada
     */
    public void setM(String x){
        this.morada=x;
    }
    
    /**
     * muda a região
     * 
     * @param (r) Nova Região
     */
    public void setR(String r){
        this.regiao=r;
    }
    
    /**
     * Função clone
     */
    public Morada clone(){
        return new Morada(this);
    }
    
    /**
     * Função equals
     */
    public boolean equals(Object o){
        if(o==this) return true;
        if(o==null || o.getClass() != this.getClass() ) return false;
        Morada c = (Morada) o; //cast
        boolean ret = this.morada.equals(c.morada) && c.getCp()==this.cPostal && c.getNp()==this.nPorta && c.getR()==this.regiao;
        return ret;
    }
    
    /**
     * Função toString
     */
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        
        sb.append("Codigo Postal: ");
        sb.append(getCp() + ",");
        sb.append("Numero de Porta: ");
        sb.append(getNp() + ",");
        sb.append("Rua: ");
        sb.append(getM() + ",");
        sb.append("Região: ");
        sb.append(getR());
        
        return sb.toString();
    }
}














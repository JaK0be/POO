import java.util.List;
import java.util.ArrayList;
/**
 * Classe que define um Contribuinte Individual
 */

public class C_Individual extends Contribuinte implements java.io.Serializable
{
    private ArrayList<Long> nifAgregado;
    private double coefFiscal; 
    private DedAcu dAc;
    
    
    /**
     * Construtor Vazio
     */
    public C_Individual()
    {
        super();
        this.nifAgregado = new ArrayList<>();
        this.coefFiscal = 0;
        this.dAc = new DedAcu(1);
    }
    
    /**
     * Construtor parametrizado
     * 
     * @param (n) Nif
     * @param (e) Email
     * @param (nome) Nome
     * @param (cP) Código Postal
     * @param (nP) Número de Porta
     * @param (morada) Morada
     * @param (regiao) Região
     * @param (p) Password
     * 
     */
    public C_Individual(long nif,String email,String nome, int cP,int nP, String morada, String regiao, String password)
    {
        super(nif, email, nome, cP, nP, morada, regiao, password);
        this.nifAgregado = new ArrayList<>();
        this.coefFiscal = cFiscal(this.nifAgregado);
        this.dAc = new DedAcu(1);
    }
    
    /**
     * Construtor cópia
     * 
     * @param (c) C_Individual a ser copiado
     */
    public C_Individual(C_Individual c)
    {
        super(c);
        this.coefFiscal = c.getCoef();
        this.nifAgregado = c.getNifAgreg();
        this.dAc = c.getDAc();
    }
    
    /**
     * Retorna o tamanho do agregrado familiar de um dado C_Individual
     */
    public int getAgreg()
    {
        return this.nifAgregado.size();
    }
    
    /**
     * Retorna o Agregado Familiar de um dado C_Individual
     */
    public ArrayList<Long> getNifAgreg()
    {
        return (ArrayList<Long>)this.nifAgregado;
    }
    
    /**
     * Retorna o coeficiente fiscal de um dado C_Individual
     */
    public double getCoef()
    {
        return this.coefFiscal;
    }
    
    /**
     * Retorna as deduções acumuladas de um dado C_Individual
     */
    public DedAcu getDAc()
    {
        return this.dAc;
    }
    
    /**
     * Altera o Agregadado de um dado C_Individual
     * 
     * @param (nifAgregado) Novo Agregado
     */
    public void setNifAgreg(ArrayList<Long> nifAgregado)
    {
        this.nifAgregado = nifAgregado;
    }
    
    /**
     * Altera o Coeficiente Fiscal de um dado C_Individual
     * 
     * @param (coefFiscal) Novo Coeficiente Fiscal
     * 
     */
    public void setCoef(double coefFiscal)
    {
        this.coefFiscal = coefFiscal;
    }
    
    /**
     * Altera as Deduções Acumuladas de um dado C_Individual
     * 
     * @param (dAc) Novas Deduções Acumuladas
     */
    public void setDAc(DedAcu dAc)
    {
        this.dAc = dAc.clone();
    }
    
    /**
     * Retorna o Coeficiente Fiscal
     * 
     * @param (c) Agregado Familiar de um C_Individual
     */
    public double cFiscal(ArrayList<Long> x)
    {
        if(x.size()>6) return 2;
        return 1;
    }
    
    /**
     * Adiciona membro ao Agregado Familiar de um dado C_Individual
     * 
     * @param (nif) Nif a ser adicionado ao Agregado
     */
    public boolean adiciona(long nif)
    {
        ArrayList<Long> x = new ArrayList<>();
        x = this.getNifAgreg();
        x.add(nif);
        return true;
    }
    
    /**
     * Função toSring
     */
    public String toString() {
        
        StringBuilder sb = new StringBuilder();
        
        sb.append(super.toString());
        sb.append("Coeficiente Fiscal: ");
        sb.append(this.coefFiscal + "\n");
        sb.append("Nif's do Agregado: ");
        sb.append(this.nifAgregado.toString());
        sb.append("\n");
        sb.append(this.dAc.toString() + "\n");

        return sb.toString();
    }
    
    /**
     * Função Equals
     */
    public boolean equals(Object o)
    {
        if(o==this) return true;
        if(o==null || o.getClass() != this.getClass() ) return false;
        C_Individual c = (C_Individual) o;
        
        return super.getNif() == c.getNif()
               && super.getEmail().equals(c.getEmail())
               && super.getNome().equals(c.getNome())
               && super.getPass().equals(c.getPass())
               && super.getResidencia().equals(c.getResidencia())
               && this.getAgreg() == c.getAgreg()
               && this.coefFiscal == c.getCoef()
               && this.getDAc().equals(c.getDAc())
               && this.nifAgregado.equals(c.getNifAgreg());
    }
    
    /**
     * Função Clone
     */
    public C_Individual clone()
    {
        return new C_Individual(this);
    }
    
}

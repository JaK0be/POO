/**
 * Classe que define um Contribuinte Colectivo
 */
public class C_Colectivo extends Contribuinte implements java.io.Serializable
{
    private int dedFiscal;
    private Despesa dsp;
    private DedAcu dAc;
    
    /**
     * Construtor Vazio
     */
    public C_Colectivo()
    {
        super();
        this.dedFiscal = 0;
        this.dsp = new Despesa();
        this.dAc = new DedAcu();
    }
    
    /**
     * Construtor Cópia
     * 
     * @param (c) Contribuinte Coletivo a ser copiado
     */
    public C_Colectivo(C_Colectivo c)
    {
        super(c);
        this.dedFiscal = c.getDedFiscal();
        this.dsp = c.getDSP();
        this.dAc = c.getDAc();
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
    public C_Colectivo(long nif,String email,String nome,int cP,int nP,String morada,String regiao,String password)
    {
       super(nif, email, nome, cP, nP, morada, regiao, password);
       this.dedFiscal = dFiscal(regiao);
       this.dsp = new Despesa();
       this.dAc = new DedAcu(dFiscal(regiao));
    }
    
    /**
     * Função que retorna a Dedução Fiscal de uma Empresa consoante a sua região
     * 
     * @param (r) Região
     */
    public int dFiscal(String r)
    {
        if(r.equals("Interior"))
            {
                return 3;
            }
        else if(r.equals("Ilhas"))
                {
                    return 4;
                }  
        return 2;      
    }
    
    /**
     * Retorna a dedução Fiscal de um dado C_Colectivo
     */
    public int getDedFiscal()
    {
        return this.dedFiscal;
    }
    
    /**
     * Retorna a Despesa de um dado C_Colectivo
     */
    public Despesa getDSP()
    {
        return this.dsp;
    }
    
    /**
     * Retorna a Dedução Acumulada de um dado C_Colectivo
     */
    public DedAcu getDAc()
    {
        return this.dAc;
    }
    
    /**
     * Retorna a Dedução Fiscal de um dado C_Colectivo
     * 
     * @param (dedFiscal) Nova Dedução Fiscal
     */
    public void setDedFiscal(int dedFiscal)
    {
        this.dedFiscal = dedFiscal;
    }
    
    /**
     * Altera a Despesa de um dado C_Colectivo
     * 
     * @param (dsp) Nova Despesa
     */
    public void setDSP(Despesa dsp)
    {
        this.dsp = dsp.clone();
    }
    
    /**
     * Altera a Dedução Acumulada de um dado C_Colectivo
     * 
     * @param (dAc) Nova Dedução Acumulada
     */
    public void setDAc(DedAcu dAc)
    {
        this.dAc = dAc.clone();
    }
        
    /**
     * Função toString
     */
    public String toString() {
        
        StringBuilder sb = new StringBuilder();
        
        sb.append(super.toString() + "\n");
        sb.append(this.dsp.toString() + "\n");
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
        C_Colectivo c = (C_Colectivo) o;
        return super.getNif() == c.getNif()
               && super.getEmail().equals(c.getEmail())
               && super.getNome().equals(c.getNome())
               && super.getPass().equals(c.getPass())
               && super.getResidencia().equals(c.getResidencia())
               && this.dedFiscal == c.dedFiscal
               && this.dsp.equals(c.dsp)
               && this.getDAc().equals(c.getDAc());
    }
    
    /**
     * Função Clone
     */
    public C_Colectivo clone()
    {
        return new C_Colectivo(this);
    }
}

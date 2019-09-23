 /**
 * Classe Contribuinte que contém a base para todos os contribuintes do sistema(Individuais e Colectivos)
 * 
 */

public abstract class Contribuinte implements java.io.Serializable
{
    private long nif;
    private String email;
    private String nome;
    private Morada residencia;
    private String password;

    /**
     * Construtor vazio Classe Contribuinte
     */
    public Contribuinte() 
    {
        this.nif=0;
        this.email = "n/e";
        this.nome = "n/e";
        this.residencia = new Morada();
        this.password = "00";
    }
    
    /**
     * Construtor parametrizado Classe Contribuinte
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
    public Contribuinte(long n, String e, String nome, int cP, int nP, String morada, String regiao, String p)
    {
        this.nif = n;
        this.email = e;
        this.nome = nome;
        this.residencia = new Morada(cP,nP,morada,regiao);
        this.password = p;
    }

    /**
     * Construtor cópia Classe Contribuinte
     * 
     * @param (c) Contribuinte a ser copiado
     * 
     */
    public Contribuinte (Contribuinte c)
    {
        this.nif = c.getNif();
        this.email = c.getEmail();
        this.nome = c.getNome();
        this.residencia = c.getResidencia(); 
        this.password = c.getPass();
    }
    
    /**
     * Função que retorna o Nif de um contribuinte
     */
    public long getNif()
    {
        return this.nif;
    }
    
    /**
     * Função que retorna o Email de um contribuinte
     */
    public String getEmail()
    {
        return this.email;
    }
    
    /**
     * Função que retorna o Nome de um contribuinte
     */
    public String getNome()
    {
        return this.nome;
    }
    
    /**
     * Função que retorna a Morada de um contribuinte
     */
    public Morada getResidencia()
    {
        return this.residencia;
    }
    
    /**
     * Função que retorna a Password de um contribuinte
     */
    public String getPass()
    {
        return this.password;
    }
    
    /**
     * Função que altera o Nif de um contribuinte
     * 
     * @param (nif) Novo Nif
     */
    public void setNif(long nif)
    {
        this.nif = nif;
    }
    
    /**
     * Função que altera o Email de um contribuinte
     * 
     * @param (email) Novo Email
     */
    public void setEmail(String email)
    {
        this.email = email;
    }
    
    /**
     * Função que altera o Nome de um contribuinte 
     * 
     * @param (nome) Novo nome
     */
    public void setNome(String nome)
    {
        this.nome = nome;
    }
    
    /**
     * Função que altera a Morada de um contribuinte
     * 
     * @param (residencia) Nova Morada
     */
    public void setResidencia(Morada residencia)
    {
        this.residencia = residencia.clone();
    }
    
    /**
     * Função que altera a Password de um contribuinte
     * 
     * @param (password) Nova Password
     */
    public void setPass(String password)
    {
        this.password = password;
    }
    
    /**
     * Função ToString
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append("\nNome: ");
        sb.append(this.nome + "\n");
        sb.append("Pass: ");
        sb.append(this.password + "\n");
        sb.append("NIF: ");
        sb.append(this.nif + "\n");
        sb.append("Email: ");
        sb.append(this.email + "\n");
        sb.append("Morada: ");
        sb.append(this.residencia.toString() + "\n");

        return sb.toString();
    }
    
    /**
     * Função equals
     */
    public boolean equals(Object o)
    {
        if(o==this) return true;
        if(o==null || o.getClass() != this.getClass() ) return false;
        Contribuinte c = (Contribuinte) o;
        boolean ret = c.nif == this.nif && this.nome.equals(c.nome) && this.password.equals(c.password) && this.residencia.equals(c.residencia);
        return ret;
    }
}


/**
 * Classe que guardará informação sobre as deduções acumuladas pelos contrbuintres
 * 
 */
public class DedAcu implements java.io.Serializable
{
    private int tipo;//tipo de contribuinte 
    private double dGerais;//armazena valor deduzido em faturas de despesas gerais
    private double dSaude;//armazena valor deduzido em faturas de despesas de saúde
    private double dEduc;//armazena valor deduzido em faturas de despesas em educação
    private double dHabi;//armazena valor deduzido em faturas de despesas em habitações
    private double dLares;////armazena valor deduzido em faturas de despesas em lares
    private double dRepA;//armazena valor deduzido em faturas de despesas em reparação automóvel
    private double dRepM;//armazena valor deduzido em faturas de despesas em repacão de motociclos
    private double dRest;//armazena valor deduzido em faturas de despesas em restuaração e alojamento
    private double dCab;//armazena valor deduzido em faturas de despesas em cabeleireiros
    private double dVet;//armazena valor deduzido em faturas de despesas em veterinários
    private double dPass;//armazena valor deduzido em faturas de despesas em passes mensais
    private double dTotal;//armazena valor total deduzido 
    /**
     * Construtor para objetos da classe DedAcu
     */
    public DedAcu()
    {
        this.tipo = 0;
        this.dGerais = 0;
        this.dSaude = 0;
        this.dEduc = 0;
        this.dHabi = 0;
        this.dLares = 0;
        this.dRepA = 0;
        this.dRepM = 0;
        this.dRest = 0;
        this.dCab = 0;
        this.dVet = 0;
        this.dPass = 0;
        this.dTotal = 0;        
    }
    
    /**
     * Construtor parametrizado para objetos da classe DedAcu
     */
    public DedAcu(int tipo,double dGerais,double dSaude,double dEduc,double dHabi,double dLares,double dRepA,double dRepM,double dRest,double dCab,double dVet,double dPass)
    {
        this.tipo = tipo;
        this.dGerais = dGerais;
        this.dSaude = dSaude;
        this.dEduc = dEduc;
        this.dHabi = dHabi;
        this.dLares = dLares;
        this.dRepA = dRepA;
        this.dRepM = dRepM;
        this.dRest = dRest;
        this.dCab = dCab;
        this.dVet = dVet;
        this.dPass = dPass;
        this.dTotal = dGerais + dEduc + dHabi + dLares + dRepA + dRepM + dRest + dCab + dVet + dPass;  
    }
    
    /**
     * Construtor usado para a interface gráfica
     * @param tipo recebe o tipo de contribuinte(Individual(1), Colectivo Litoral(2), Colectivo Interior(3), Colectivo Ilhas(4) )
     */
    public DedAcu(int tipo)
    {
        this.tipo = tipo;
        this.dGerais = 0;
        this.dEduc = 0;
        this.dSaude = 0;
        this.dHabi = 0;
        this.dLares = 0;
        this.dRepA = 0;
        this.dRepM = 0;
        this.dRest = 0;
        this.dCab = 0;
        this.dVet = 0;
        this.dPass = 0;
        this.dTotal = 0;  
    }
    
    /**
     * Construtor cópia
     */
    public DedAcu(DedAcu x)
    {
        this.tipo = x.getTipo();
        this.dGerais = x.getDGerais();
        this.dSaude = x.getDSaude();
        this.dEduc = x.getDEduc();
        this.dHabi = x.getDHabi();
        this.dLares = x.getDLares();
        this.dRepA = x.getDRepA();
        this.dRepM = x.getDRepM();
        this.dRest = x.getDRest();
        this.dCab = x.getDCab();
        this.dVet = x.getDVet();
        this.dPass = x.getDPass();
        this.dTotal = x.getDTotal();  
    }
    
    /**
     * Retorna o tipo de um objeto DedAcu
     */
    public int getTipo()
    {
        return this.tipo;
    }
    
    /**
     * Retorna o dGerais de um objeto DedAcu
     */
    public double getDGerais()
    {
        return this.dGerais;
    }
    
    /**
     * Retorna o dSaude de um objeto DedAcu
     */
    public double getDSaude()
    {
        return this.dSaude;
    }
    
    /**
     * Retorna o dEduc de um objeto DedAcu
     */
    public double getDEduc()
    {
        return this.dEduc;
    }
    
    /**
     * Retorna o dHabi de um objeto DedAcu
     */
    public double getDHabi()
    {
        return this.dHabi;
    }
    
    /**
     * Retorna o dLares de um objeto DedAcu
     */
    public double getDLares()
    {
        return this.dLares;
    }
    
    /**
     * Retorna o dRepA de um objeto DedAcu
     */
    public double getDRepA()
    {
        return this.dRepA;
    }
    
    /**
     * Retorna o dRepM de um objeto DedAcu
     */
    public double getDRepM()
    {
        return this.dRepM;
    }
    
    /**
     * Retorna o dRest de um objeto DedAcu
     */
    public double getDRest()
    {
        return this.dRest;
    }
    
    /**
     * Retorna o dCab de um objeto DedAcu
     */
    public double getDCab()
    {
        return this.dCab;
    }
    
    /**
     * Retorna o dVet de um objeto DedAcu
     */
    public double getDVet()
    {
        return this.dVet;
    }
    
    /**
     * Retorna o dPass de um objeto DedAcu
     */
    public double getDPass()
    {
        return this.dPass;
    }
    
    /**
     * Retorna o dTotal de um objeto DedAcu
     */
    public double getDTotal()
    {
        return this.dTotal;
    }
    
    /**
     * Altera o dTotal de um objeto DedAcu
     */
    public void setDTotal(double x)
    {
        this.dTotal = x;
    }
    
    /**
     * Consoante o tipo de despesa faz o set necessário para adicionar essa despesa
     */
    public void add(int codigo, double valor)
    {
        if(codigo==0){this.setDGerais(valor);}
        else if(codigo==1){this.setDSaude(valor);}
        else if(codigo==10){this.setDEduc(valor);}
        else if(codigo==11){this.setDHabi(valor);}
        else if(codigo==100){this.setDLares(valor);}
        else if(codigo==101){this.setDRepA(valor);}
        else if(codigo==110){this.setDRepM(valor);}
        else if(codigo==111){this.setDRest(valor);}
        else if(codigo==1000){this.setDCab(valor);}
        else if(codigo==1001){this.setDVet(valor);}
        else if(codigo==1010){this.setDPass(valor);}
    }
    
    /**
     * Adiciaona ao dTotal de um objeto DedAcu
     */
    public void addDTotal(double x)
    {
        this.dTotal += x;
        if(this.getTipo()==2)//Se for uma empresa do litoral
            {
                if(this.dTotal >= 15000){this.dTotal = 15000;}
            }else if(this.getTipo()==3)//Se for uma empresa do interior
                    {
                        if(this.dTotal >= 17500){this.dTotal = 17500;}
                    }
                    else if(this.getTipo()==4)//Se for uma empresa das ilhas
                           {
                               if(this.dTotal >= 20000){this.dTotal = 20000;}
                           } 
    }
    
    /**
     * Altera o dGerais de um objeto DedAcu
     */
    public void setDGerais(double x)
    {
        this.dGerais += x;
        this.addDTotal(x);
    }
    
    /**
     * Altera o dSaude de um objeto DedAcu
     */
    public void setDSaude(double x)
    {
        double resto=0;
        if(this.getTipo()==1)//Se for um contribuinte Individual
        {
            resto = this.dSaude;
            this.dSaude += x;
            if(this.dSaude > 1000){this.dSaude = 1000;this.addDTotal(1000-resto);}
            else{this.addDTotal(x);}
        }else {//Se for um contrribuinte coletivo
                this.dSaude += x;
                this.addDTotal(x);
              }
    }
    
    /**
     * Altera o dEduc de um objeto DedAcu
     */
    public void setDEduc(double x)
    {
        double resto=0;
        if(this.getTipo()==1)//Se for um contribuinte Individual
        {
            resto = this.dEduc;
            this.dEduc += x;
            if(this.dEduc > 800){this.dEduc = 800;this.addDTotal(800-resto);}
            else{this.addDTotal(x);}
        }else {//Se for um contrribuinte coletivo
                this.dEduc += x;
                this.addDTotal(x);
              }
    }
    
    /**
     * Altera o dHabi de um objeto DedAcu
     */
    public void setDHabi(double x)
    {
        double resto=0;
        if(this.getTipo()==1)//Se for um contribuinte Individual
        {
            resto = this.dEduc;
            this.dEduc += x;
            if(this.dHabi > 400){this.dHabi = 400;this.addDTotal(400-resto);}
            else{this.addDTotal(x);}
        }else {//Se for um contrribuinte coletivo
                this.dHabi += x;
                this.addDTotal(x);
              }
    }
    
    /**
     * Altera o dLares de um objeto DedAcu
     */
    public void setDLares(double x)
    {
        double resto=0;
        if(this.getTipo()==1)//Se for um contribuinte Individual
        {
            resto = this.dLares;
            this.dLares += x;
            if(this.dLares > 403.75){this.dLares = 403.75;this.addDTotal(403.75-resto);}
            else{this.addDTotal(x);}
        }else {//Se for um contrribuinte coletivo
                this.dLares += x;
                this.addDTotal(x);
              }
    }
    
    /**
     * Altera o dRepA de um objeto DedAcu
     */
    public void setDRepA(double x)
    {
        double resto=0;
        if(this.getTipo()==1)//Se for um contribuinte Individual
        {
            resto = this.dRepA;
            this.dRepA += x;
            if(this.dRepA > 300){this.dRepA = 300;this.addDTotal(300-resto);}
            else{this.addDTotal(x);}
        }else {//Se for um contrribuinte coletivo
                this.dRepA += x;
                this.addDTotal(x);
              }
    }
    
    /**
     * Altera o dRepM de um objeto DedAcu
     */
    public void setDRepM(double x)
    {
        double resto=0;
        if(this.getTipo()==1)//Se for um contribuinte Individual
        {
            resto = this.dRepM;
            this.dRepM += x;
            if(this.dRepM > 200){this.dRepM = 200;this.addDTotal(200-resto);}
            else{this.addDTotal(x);}
        }else {//Se for um contrribuinte coletivo
                this.dRepM += x;
                this.addDTotal(x);
              }
    }
    
    /**
     * Altera o dRest de um objeto DedAcu
     */
    public void setDRest(double x)
    {
        double resto=0;
        if(this.getTipo()==1)//Se for um contribuinte Individual
        {
            resto = this.dRest;
            this.dRest += x;
            if(this.dRest > 200){this.dRest = 200;this.addDTotal(200-resto);}
            else{this.addDTotal(x);}
        }else {//Se for um contrribuinte coletivo
                this.dRest += x;
                this.addDTotal(x);
              }
    }
    
    /**
     * Altera o dCab de um objeto DedAcu
     */
    public void setDCab(double x)
    {
        double resto=0;
        if(this.getTipo()==1)//Se for um contribuinte Individual
        {
            resto = this.dCab;
            this.dCab += x;
            if(this.dCab > 100){this.dCab = 100;this.addDTotal(100-resto);}
            else{this.addDTotal(x);}
        }else {//Se for um contrribuinte coletivo
                this.dCab += x;
                this.addDTotal(x);
              }
    }
    
    /**
     * Altera o dVet um objeto DedAcu
     */
    public void setDVet(double x)
    {
        double resto=0;
        if(this.getTipo()==1)//Se for um contribuinte Individual
        {
            resto = this.dVet;
            this.dVet += x;
            if(this.dVet > 150){this.dCab = 150;this.addDTotal(150-resto);}
            else{this.addDTotal(x);}
        }else {//Se for um contrribuinte coletivo
                this.dVet += x;
                this.addDTotal(x);
              }
    }
    
    /**
     * Altera o dPass um objeto DedAcu
     */
    public void setDPass(double x)
    {
        double resto=0;
        if(this.getTipo()==1)//Se for um contribuinte Individual
        {
            resto = this.dPass;
            this.dPass += x;
            if(this.dPass > 150){this.dPass = 150;this.addDTotal(150-resto);}
            else{this.addDTotal(x);}
        }else {//Se for um contribuinte coletivo
                this.dPass += x;
                this.addDTotal(x);
              }
    }
    
    public void remValor(double valor,int serviço)
    {
        Despesa xx = new Despesa();
        double desconto = xx.getDesconto(serviço);
        double remove = valor * desconto;
        double getValue,insereValue;
        if(serviço == 0){getValue = this.getDGerais();insereValue = getValue - remove;this.dGerais = insereValue;this.dTotal = this.dTotal - remove;}
        if(serviço == 1){getValue = this.getDSaude();insereValue = getValue - remove;this.dSaude = insereValue;this.dTotal = this.dTotal - remove;}
        if(serviço == 10){getValue = this.getDEduc();insereValue = getValue - remove;this.dEduc = insereValue;this.dTotal = this.dTotal - remove;}
        if(serviço == 11){getValue = this.getDHabi();insereValue = getValue - remove;this.dHabi = insereValue;this.dTotal = this.dTotal - remove;}
        if(serviço == 100){getValue = this.getDLares();insereValue = getValue - remove;this.dLares = insereValue;this.dTotal = this.dTotal - remove;}
        if(serviço == 101){getValue = this.getDRepA();insereValue = getValue - remove;this.dRepA = insereValue;this.dTotal = this.dTotal - remove;}
        if(serviço == 110){getValue = this.getDRepM();insereValue = getValue - remove;this.dRepM = insereValue;this.dTotal = this.dTotal - remove;}
        if(serviço == 111){getValue = this.getDRest();insereValue = getValue - remove;this.dRest = insereValue;this.dTotal = this.dTotal - remove;}
        if(serviço == 1000){getValue = this.getDCab();insereValue = getValue - remove;this.dCab = insereValue;this.dTotal = this.dTotal - remove;}
        if(serviço == 1001){getValue = this.getDVet();insereValue = getValue - remove;this.dVet = insereValue;this.dTotal = this.dTotal - remove;}
        if(serviço == 1010){getValue = this.getDPass();insereValue = getValue - remove;this.dPass = insereValue;this.dTotal = this.dTotal - remove;}
    }
    
    /**
     * Função equals
     */
    public boolean equals(Object o)
    {
        if(o==this) return true;
        if(o==null || o.getClass() != this.getClass() ) return false;
        DedAcu c = (DedAcu) o;
        return this.tipo == c.getTipo()
               && this.dGerais == c.getDGerais()
               && this.dSaude == c.getDSaude()
               && this.dEduc == c.getDEduc()
               && this.dHabi == c.getDHabi()
               && this.dLares == c.getDLares()
               && this.dRepA == c.getDRepA()
               && this.dRepM == c.getDRepM()
               && this.dRest == c.getDRest()
               && this.dCab == c.getDCab()
               && this.dVet == c.getDVet()
               && this.dPass == c.getDPass()
               && this.dTotal == c.getDTotal();
    }
    
    /**
     * Função ToString
     */
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        
        sb.append("Tipo: "+ this.tipo + "\n");
        sb.append("Despesas Gerais e Famaliares: " + this.dGerais + "\n");
        sb.append("Despesas Saúde: " + this.dSaude + "\n");
        sb.append("Despesas Educação: " + this.dEduc + "\n");
        sb.append("Despesas Habitação: " + this.dHabi + "\n");
        sb.append("Despesas Lares: " + this.dLares + "\n");
        sb.append("Despesas Reparação Auto: " + this.dRepA + "\n");
        sb.append("Despesas Reparação Moto: " + this.dRepM + "\n");
        sb.append("Despesas Restauração: " + this.dRest + "\n");
        sb.append("Despesas Cabeleireiros: " + this.dCab + "\n");
        sb.append("Despesas Veterinário: " + this.dVet + "\n");
        sb.append("Despesas Passes Mensais: " + this.dPass + "\n");
        sb.append("Despesas Totais: " + this.dTotal + "\n");
        
        return sb.toString();
    }
    
    /**
     * Função clone
     */
    public DedAcu clone()
    {
        return new DedAcu(this);
    }
}

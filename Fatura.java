/**
 * Classe que define como é representada uma Fatura no Sistema
 */

import java.time.LocalDate;

public class Fatura implements java.io.Serializable,Comparable<Fatura>
{
        private long nifEmissor;
        private String nomeEmissor;
        private LocalDate data;
        private long nifCliente;
        private String descDespesa;
        private double preco;
        private int despesa;
        private double dspDesc;
        private Boolean valido; 
        private LocalDate dataAtualizaçao;
        private int prevDespesa;
        
        /**
         * Construtor Vazio
         */
        public Fatura()
        {
            this.nifEmissor = 0;
            this.nomeEmissor = "n/e";
            this.data = LocalDate.now();
            this.nifCliente = 0;
            this.descDespesa = "n/e";
            this.preco = 0;
            this.despesa = 2000;
            this.dspDesc = 0;
            this.valido = false;
            this.dataAtualizaçao = LocalDate.now();
            this.prevDespesa = 2000;
        }
        
        
        /**
         * Construtor parametrizado
         * 
         * @param (nifE) Nif Emissor
         * @param (nomeE) Nome Emissor
         * @param (dt) Data emissão Fatura
         * @param (nifC) Nif Cliente
         * @param (desc) Descrição Fatura
         * @param (maney) Valor Monetário da Fatura
         * @param (despesa) Serviço prestado
         * @param (b) Validade Fatura
         * 
         * 
         */
        public Fatura(long nifE,String nomeE,LocalDate dt,long nifC,String desc,double maney,int despesa, Boolean b)
        {
            Despesa f = new Despesa(); 
       
            this.nifEmissor = nifE;
            this.nomeEmissor = nomeE;
            this.data = dt;
            this.nifCliente = nifC;
            this.descDespesa = desc;
            this.preco = maney;
            this.despesa = despesa;
            this.dspDesc = f.getDesconto(despesa);
            this.valido = b;
            this.dataAtualizaçao = LocalDate.now();
            this.prevDespesa = 2000;
        }

        /**
         * Construtor Cópia
         * 
         * @param (f) Fatura a ser copiada
         */
        public Fatura(Fatura f)
        {
            this.nifEmissor = f.getNifE();
            this.nomeEmissor = f.getNomeE();
            this.data = f.getData();
            this.nifCliente = f.getNifC();
            this.descDespesa = f.getDesc();
            this.preco = f.getCost();
            this.despesa = f.getDSP();
            this.dspDesc = f.getDSPD();
            this.valido = f.getVal();
            this.dataAtualizaçao = LocalDate.now();
            this.prevDespesa = 2000;
        }            
        
        /**
         * Função que vai dar Override a usada para comparar e dar sort a ArrayLists do Collections
         * 
         * @param (f) Fatura a ser comparada
         */
        
         public int compareTo(Fatura f) 
        {
            int comparePreco = (int) ((Fatura) f).getCost(); 
        
            return (comparePreco - (int) this.getCost()); 
        }
        
        /**
         * Retorna o Nif do Emissor de uma dada Fatura
         */
        public long getNifE()
        {
            return this.nifEmissor;
        }
    
        /**
         * Retorna o Nome do Emissor de uma dada Fatura
         */
        public String getNomeE()
        {
            return this.nomeEmissor;
        }
   
        /**
         * Retorna a Data de emissão de uma dada Fatura
         */
        public LocalDate getData()
        {
            return this.data;
        }
    
        /**
         * Retorna o Nif do Cliente de uma dada Fatura
         */
        public long getNifC()
        {
           return this.nifCliente;
        }
    
        /**
         * Retorna a Descrição de uma dada Fatura
         */
        public String getDesc()
        {
            return this.descDespesa;
        }
        
        /**
         * Retorna o Valor Monetário de uma dada Fatura
         */
        public double getCost()
        {
            return this.preco;
        }
        
        /**
         * Retorna o Serviço de uma dada Fatura
         */
        public int getDSP()
        {
            return this.despesa;
        }
   
        /**
         * Retorna a percentagem deduzível de uma dada Fatura
         */
        public double getDSPD()
        {
            return this.dspDesc;
        }
        
        /**
         * Retorna a Validade de uma dada Fatura
         */
        public Boolean getVal()
        {
            return this.valido;
        }
        
        /**
         * Retorna a Data de quando uma Fatura foi atualizada
         */
        public LocalDate getAtData()
        {
            return this.dataAtualizaçao;
        }
        
        /**
         * Retorna Serviço antes da Atualização de uma dada Fatura
         */
        public int getPrevDsp()
        {
            return this.prevDespesa;
        }
    
        /**
         * Altera o Nif do Emissor de uma dada Fatura
         * 
         * @param (NifE) Novo Nif Emissor
         */
        public void setNifE(long NifE)
        {
            this.nifEmissor = NifE;
        }
        
        /**
         * Altera o Nome do Emissor de uma dada Fatura
         * 
         * @param (nomeE) Novo Nome Emissor
         */
        public void setNomeE(String nomeE)
        {
            this.nomeEmissor = nomeE;
        }
    
        /**
         * Altera a Data de uma dada Fatura
         * 
         * @param(data) Nova Data
         */
        public void setData(LocalDate data)
        {
            this.data = data;
        }
        
        /**
         * Altera o Nif do Cliente de uma dada Fatura
         * 
         * @param(nifC) Novo Nif Cliente
         */
        public void setNifC(long nifC)
        {
            this.nifCliente = nifC;
        }
        
        /**
         * Altera a descrição de uma dada Fatura
         * 
         * @param (descD) Nova Descrição
         */
        public void setDesc(String descD)
        {
            this.descDespesa = descD;
        }
    
        /**
         * Altera o preço de uma dada Fatura
         * 
         * @param(preço) Novo Preço
         */
        public void setCost(double preco)
        {
            this.preco = preco;
        }
   
        /**
         * Função que atualiza uma Fatura
         */
        public void setDSP(int dsp)
        {
            Despesa f = new Despesa();
            
            setAtData();
            setPrevDsp();
            this.despesa = dsp;
            this.dspDesc = f.getDesconto(dsp);
        }
        
        /**
         * Altera a Validade de uma dada Fatura
         * 
         * @param(c) Nova Validade
         */
        public void setVal(Boolean c)
        {
            this.valido = c;
        }
        
        /**
         * Altera a Data de Atualização de uma dada Fatura
         */
        public void setAtData()
        {
            this.dataAtualizaçao = LocalDate.now();
        }
        
        /**
         * Altera ao Serviço de uma dada Fatura após uma atualização
         */
        public void setPrevDsp()
        {
            this.prevDespesa = this.despesa;
        }
        
        /**
         * Função Equals
         */
        public boolean equals(Object o)
        {
            if(o==this) return true;
            if(o==null || o.getClass() != this.getClass() ) return false;
            Fatura f = (Fatura) o;
            boolean ret = f.nifEmissor == this.nifEmissor && this.nomeEmissor.equals(f.nomeEmissor) && this.data.equals(f.data) &&
                                          this.nifCliente == f.nifCliente && this.descDespesa.equals(f.descDespesa) &&
                                          this.preco == f.preco && this.despesa == f.despesa && this.dspDesc == f.dspDesc &&
                                          this.valido == f.valido && this.dataAtualizaçao.equals(f.dataAtualizaçao) && this.prevDespesa == f.prevDespesa;
            return ret;
        }
   
        /**F
         * Função Clone
         */
        public Fatura clone()
        {
            return new Fatura(this);
        }
   
        /**
         * Função toString
         */
        public String toString() {
        
            StringBuilder sb = new StringBuilder();
        
            sb.append("Fornecedor: ");
            sb.append(this.nomeEmissor + "\n");
            sb.append("NIF Fornecedor: ");
            sb.append(this.nifEmissor + "\n");
            sb.append("NIF Cliente: ");
            sb.append(this.nifCliente + "\n");
            sb.append("Natureza da despesa efectuada: ");
            sb.append(this.despesa + "\n");
            sb.append("Descriçao da Despesa: ");
            sb.append(this.descDespesa + "\n");
            sb.append("Desconto associado a despesa: ");
            sb.append(this.dspDesc + "\n");
            sb.append("Preço total: ");
            sb.append(this.preco + "\n");
            sb.append("Data: ");
            sb.append(this.data.toString() + "\n");
            sb.append("Validada: ");
            sb.append(this.valido + "\n");
            sb.append("Ultima Atualizaçao: ");
            sb.append(this.dataAtualizaçao.toString() + "\n");
            sb.append("Natureza de despesa Anterior: ");
            sb.append(this.prevDespesa + "\n");

            return sb.toString();
        } 
}

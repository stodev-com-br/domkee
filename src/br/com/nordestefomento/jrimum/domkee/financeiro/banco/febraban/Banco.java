/*
 * Copyright 2008 JRimum Project
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by
 * applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
 * OF ANY KIND, either express or implied. See the License for the specific
 * language governing permissions and limitations under the License.
 * 
 * Created at: 30/03/2008 - 18:57:43
 * 
 * ================================================================================
 * 
 * Direitos autorais 2008 JRimum Project
 * 
 * Licenciado sob a Licença Apache, Versão 2.0 ("LICENÇA"); você não pode usar
 * esse arquivo exceto em conformidade com a esta LICENÇA. Você pode obter uma
 * cópia desta LICENÇA em http://www.apache.org/licenses/LICENSE-2.0 A menos que
 * haja exigência legal ou acordo por escrito, a distribuição de software sob
 * esta LICENÇA se dará “COMO ESTÁ”, SEM GARANTIAS OU CONDIÇÕES DE QUALQUER
 * TIPO, sejam expressas ou tácitas. Veja a LICENÇA para a redação específica a
 * reger permissões e limitações sob esta LICENÇA.
 * 
 * Criado em: 30/03/2008 - 18:57:43
 * 
 */

package br.com.nordestefomento.jrimum.domkee.financeiro.banco.febraban;

import static br.com.nordestefomento.jrimum.utilix.ObjectUtil.isNotNull;

import java.awt.Image;
import java.util.Collection;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;

import br.com.nordestefomento.jrimum.domkee.comum.NumeroDeTelefone;
import br.com.nordestefomento.jrimum.domkee.comum.endereco.Endereco;
import br.com.nordestefomento.jrimum.domkee.comum.pessoa.PessoaJuridica;
import br.com.nordestefomento.jrimum.domkee.comum.pessoa.id.cprf.AbstractCPRF;
import br.com.nordestefomento.jrimum.domkee.comum.pessoa.id.cprf.CNPJ;


/**
 * 
 * <p>
 * Um Banco (instituição financeira) supervisionada pelo <a href="http://www.bcb.gov.br/">BACEN</a>.
 * </p>
 * 
 * @author <a href="http://gilmatryx.googlepages.com/">Gilmar P.S.L.</a>
 * 
 * @since 0.2
 * 
 * @version 0.2
 */
	
public class Banco implements br.com.nordestefomento.jrimum.domkee.financeiro.banco.Banco {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6156550582890687779L;

	private CodigoDeCompensacaoBACEN codigoDeCompensacaoBACEN;

	private String segmento;

	private Image imgLogo;
	
	private PessoaJuridica pessoaJuridica;

	/**
	 * 
	 */
	public Banco() {
		super();
	}
	
	
	/**
	 * @param codigoDeCompensacaoBACEN
	 * @param instituicao
	 */
	public Banco(CodigoDeCompensacaoBACEN codigoDeCompensacaoBACEN, String instituicao) {
		super();
		
		this.codigoDeCompensacaoBACEN = codigoDeCompensacaoBACEN;

		pessoaJuridica = new PessoaJuridica();
		pessoaJuridica.setNome(instituicao);
		pessoaJuridica.setNomeFantasia(instituicao);
	}

	/**
	 * @param codigoDeCompensacaoBACEN
	 * @param instituicao
	 * @param cnpj
	 */
	public Banco(CodigoDeCompensacaoBACEN codigoDeCompensacaoBACEN, String instituicao, CNPJ cnpj) {
		super();
		
		this.codigoDeCompensacaoBACEN = codigoDeCompensacaoBACEN;
		
		pessoaJuridica = new PessoaJuridica();
		
		pessoaJuridica.setCPRF(cnpj);
		pessoaJuridica.setNome(instituicao);
		pessoaJuridica.setNomeFantasia(instituicao);
	}

	/**
	 * @param codigoDeCompensacaoBACEN
	 * @param instituicao
	 * @param cnpj
	 * @param segmento
	 */
	public Banco(CodigoDeCompensacaoBACEN codigoDeCompensacaoBACEN, String instituicao, CNPJ cnpj,
			String segmento) {
		super();
		
		this.codigoDeCompensacaoBACEN = codigoDeCompensacaoBACEN;
		this.segmento = segmento;
		
		pessoaJuridica = new PessoaJuridica();
		pessoaJuridica.setCPRF(cnpj);
		pessoaJuridica.setNome(instituicao);
		pessoaJuridica.setNomeFantasia(instituicao);
	}

	/**
	 * @param codigoDeCompensacaoBACEN
	 * @param instituicao
	 * @param cnpj
	 * @param segmento
	 * @param imgLogo
	 */
	public Banco(CodigoDeCompensacaoBACEN codigoDeCompensacaoBACEN, String instituicao, CNPJ cnpj,	String segmento, Image imgLogo) {
		super();
		
		this.codigoDeCompensacaoBACEN = codigoDeCompensacaoBACEN;
		this.segmento = segmento;
		this.imgLogo = imgLogo;
		
		pessoaJuridica = new PessoaJuridica();
		pessoaJuridica.setCPRF(cnpj);
	}

	/**
	 * 	 * <p>
	 * Verifica se o código passdo está ok em relação as regras:
	 * <ol>
	 * <li>Não nulo</li>
	 * <li>Numérico</li>
	 * <li>Com 3 digitos</li>
	 * </ol>
	 * </p>
	 * 
	 * @param codigo
	 * 
	 * @return se ok
	 * 
	 * @throws IllegalArgumentException
	 * 
	 * @since 0.2
	 * 
	 */		
	public static boolean isCodigoDeCompensacaoOK(String codigo) throws IllegalArgumentException{
		boolean ok = false;
		if(isNotNull(codigo, "codigo")){
			if(StringUtils.isNumeric(codigo))
				if(codigo.length() == 3)
					ok = true;
				else
					throw new IllegalArgumentException("O código é de apenas 3 digitos!");
			else
				throw new IllegalArgumentException("O código de compensação deve ser numérico!");
		}
		return ok;
	}


	/**
	 * @return the codigoDeCompensacaoBACEN
	 */
	public CodigoDeCompensacaoBACEN getCodigoDeCompensacaoBACEN() {
		return codigoDeCompensacaoBACEN;
	}


	/**
	 * @param codigoDeCompensacaoBACEN the codigoDeCompensacaoBACEN to set
	 */
	public void setCodigoDeCompensacaoBACEN(
			CodigoDeCompensacaoBACEN codigoDeCompensacaoBACEN) {
		this.codigoDeCompensacaoBACEN = codigoDeCompensacaoBACEN;
	}


	public CNPJ getCNPJ() {
		return (CNPJ)pessoaJuridica.getCPRF();
	}

	public void setCNPJ(CNPJ cnpj) {
		pessoaJuridica.setCPRF(cnpj);
	}

	public String getSegmento() {
		return segmento;
	}

	public void setSegmento(String segmento) {
		this.segmento = segmento;
	}

	public Image getImgLogo() {
		return imgLogo;
	}

	public void setImgLogo(Image imgLogo) {
		this.imgLogo = imgLogo;
	}

	/**
	 * @see br.com.nordestefomento.jrimum.domkee.comum.pessoa.IPessoa#addEndereco(br.com.nordestefomento.jrimum.domkee.comum.endereco.Endereco)
	 */
	
	public void addEndereco(Endereco endereco) {
		
		pessoaJuridica.addEndereco(endereco);
	}

	/**
	 * @see br.com.nordestefomento.jrimum.domkee.comum.pessoa.IPessoa#addTelefone(br.com.nordestefomento.jrimum.domkee.comum.NumeroDeTelefone)
	 */
	
	public void addTelefone(NumeroDeTelefone telefone) {
		
		pessoaJuridica.addTelefone(telefone);
	}

	/**
	 * @see br.com.nordestefomento.jrimum.domkee.comum.pessoa.IPessoa#getCPRF()
	 */
	
	public AbstractCPRF getCPRF() {
		
		return pessoaJuridica.getCPRF();
	}

	/**
	 * @see br.com.nordestefomento.jrimum.domkee.comum.pessoa.IPessoa#getEnderecos()
	 */
	public Collection<Endereco> getEnderecos() {
		
		return pessoaJuridica.getEnderecos();
	}

	/**
	 * @see br.com.nordestefomento.jrimum.domkee.comum.pessoa.IPessoa#getNome()
	 */
	
	public String getNome() {
		
		return pessoaJuridica.getNome();
	}

	/**
	 * @see br.com.nordestefomento.jrimum.domkee.comum.pessoa.IPessoa#getTelefones()
	 */
	
	public Collection<NumeroDeTelefone> getTelefones() {
		
		return pessoaJuridica.getTelefones();
	}

	/**
	 * @see br.com.nordestefomento.jrimum.domkee.comum.pessoa.IPessoa#isFisica()
	 */
	
	public boolean isFisica() {
		
		return pessoaJuridica.isFisica();
	}

	/**
	 * @see br.com.nordestefomento.jrimum.domkee.comum.pessoa.IPessoa#isJuridica()
	 */
	
	public boolean isJuridica() {
		
		return pessoaJuridica.isJuridica();
	}

	/**
	 * @see br.com.nordestefomento.jrimum.domkee.comum.pessoa.IPessoa#setCPRF(br.com.nordestefomento.jrimum.domkee.comum.pessoa.id.cprf.AbstractCPRF)
	 */
	
	public void setCPRF(AbstractCPRF abstractCPRF) {
		
		pessoaJuridica.setCPRF(abstractCPRF);
	}


	/**
	 * @see br.com.nordestefomento.jrimum.domkee.comum.pessoa.IPessoa#setEnderecos(java.util.Collection)
	 */
	public void setEnderecos(Collection<Endereco> enderecos) {
		
		pessoaJuridica.setEnderecos(enderecos);
	}

	/**
	 * @see br.com.nordestefomento.jrimum.domkee.comum.pessoa.IPessoa#setNome(java.lang.String)
	 */
	
	public void setNome(String nome) {
		
		pessoaJuridica.setNome(nome);
	}

	/**
	 * @see br.com.nordestefomento.jrimum.domkee.comum.pessoa.IPessoa#setTelefones(java.util.Collection)
	 */
	
	public void setTelefones(Collection<NumeroDeTelefone> telefones) {
		
		pessoaJuridica.setTelefones(telefones);
	}

	/**
	 * @see br.com.nordestefomento.jrimum.domkee.comum.pessoa.IPessoaJuridica#getInscricaoEstadual()
	 */
	
	public Long getInscricaoEstadual() {
		
		return pessoaJuridica.getInscricaoEstadual();
	}

	/**
	 * @see br.com.nordestefomento.jrimum.domkee.comum.pessoa.IPessoaJuridica#getInscricaoMunicipal()
	 */
	
	public Long getInscricaoMunicipal() {
		
		return pessoaJuridica.getInscricaoMunicipal();
	}

	/**
	 * @see br.com.nordestefomento.jrimum.domkee.comum.pessoa.IPessoaJuridica#getNomeFantasia()
	 */
	
	public String getNomeFantasia() {
		
		return pessoaJuridica.getNome();
	}

	/**
	 * @see br.com.nordestefomento.jrimum.domkee.comum.pessoa.IPessoaJuridica#setInscricaoEstadual(java.lang.Long)
	 */
	
	public void setInscricaoEstadual(Long inscricaoEstadual) {
		
		pessoaJuridica.setInscricaoEstadual(inscricaoEstadual);
	}

	/**
	 * @see br.com.nordestefomento.jrimum.domkee.comum.pessoa.IPessoaJuridica#setInscricaoMunicipal(java.lang.Long)
	 */
	
	public void setInscricaoMunicipal(Long inscricaoMunicipal) {
		
		pessoaJuridica.setInscricaoMunicipal(inscricaoMunicipal);
	}

	/**
	 * @see br.com.nordestefomento.jrimum.domkee.comum.pessoa.IPessoaJuridica#setNomeFantasia(java.lang.String)
	 */
	
	public void setNomeFantasia(String nomeFantasia) {
		
		pessoaJuridica.setNomeFantasia(nomeFantasia);
	}

	/**
	 * @see br.com.nordestefomento.jrimum.domkee.comum.pessoa.IPessoa#addContaBancaria(br.com.nordestefomento.jrimum.domkee.bank.febraban.ContaBancaria)
	 */
	
	public void addContaBancaria(ContaBancaria contaBancaria) {
		pessoaJuridica.addContaBancaria(contaBancaria);
		
	}


	/**
	 * @see br.com.nordestefomento.jrimum.domkee.comum.pessoa.IPessoa#getContasBancarias()
	 */
	
	public Collection<ContaBancaria> getContasBancarias() {
		
		return pessoaJuridica.getContasBancarias();
	}


	/**
	 * @see br.com.nordestefomento.jrimum.domkee.comum.pessoa.IPessoa#hasContaBancaria()
	 */
	
	public boolean hasContaBancaria() {
		
		return pessoaJuridica.hasContaBancaria();
	}


	/**
	 * @see br.com.nordestefomento.jrimum.domkee.comum.pessoa.IPessoa#setContasBancarias(java.util.Collection)
	 */
	
	public void setContasBancarias(Collection<ContaBancaria> contasBancarias) {
		
		pessoaJuridica.setContasBancarias(contasBancarias);
	}

	@Override
	public String toString() {
		
		ToStringBuilder tb = new ToStringBuilder(this);
		tb.append(codigoDeCompensacaoBACEN);
		tb.append(segmento);
		tb.append(pessoaJuridica);
		
		return tb.toString();
	}
}
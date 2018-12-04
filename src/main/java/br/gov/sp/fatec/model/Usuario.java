package br.gov.sp.fatec.model;

import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.gov.sp.fatec.view.View;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "USR_USUARIO")
public class Usuario implements UserDetails{

	private static final long serialVersionUID = 1507218635788384719L;

	@Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "USR_ID")
	@JsonView({View.UsuarioCompleto.class, View.UsuarioResumoAlternativo.class, View.Agenda.class})
	private Long id;
    
	@Column(name = "USR_NOME", unique=true, length = 20, nullable = false)
    @JsonView({View.UsuarioResumo.class, View.UsuarioResumoAlternativo.class, View.Agenda.class})
    private String nome;
	
	@Column(name = "USR_CPF", unique=true, length = 20, nullable = false)
    @JsonView({View.UsuarioResumo.class, View.UsuarioResumoAlternativo.class, View.Agenda.class})
    private String cpf;
	
	@Column(name = "USR_CELULAR", length = 20, nullable = false)
    @JsonView({View.UsuarioCompleto.class, View.UsuarioResumoAlternativo.class})
    private String celular;
	
	@Column(name = "USR_APARTAMENTO", length = 20, nullable = false)
    @JsonView({View.UsuarioResumo.class, View.UsuarioResumoAlternativo.class})
    private String apartamento;
	
	@Column(name = "USR_CARRO", length = 20)
    @JsonView({View.UsuarioResumo.class, View.UsuarioResumoAlternativo.class})
    private String carro;
	
	@Column(name = "USR_BLOCO", length = 10)
    @JsonView({View.UsuarioResumo.class})
    private String bloco;
	
	@Column(name = "USR_ANIMAL_ESTIMACAO")
    @JsonView({View.UsuarioResumoAlternativo.class})
    private boolean possui_animal_estimacao;
    
    @Column(name = "USR_SENHA", length = 50, nullable = false)
    @JsonView({View.UsuarioCompleto.class})
    private String senha;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "UAU_USUARIO_AUTORIZACAO", 
    	joinColumns = { @JoinColumn(name = "USR_ID") }, 
    	inverseJoinColumns = { @JoinColumn(name = "AUT_ID") })
    @JsonView({View.UsuarioResumo.class, View.UsuarioResumoAlternativo.class})
    @XmlElement(name = "autorizacao")
    private List<Autorizacao> autorizacoes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}
	
	public String getApartamento() {
		return apartamento;
	}

	public void setApartamento(String apartamento) {
		this.apartamento = apartamento;
	}
	
	public String getCarro() {
		return carro;
	}

	public void setCarro(String carro) {
		this.carro = carro;
	}
	
	public String getBloco() {
		return bloco;
	}

	public void setBloco(String bloco) {
		this.bloco = bloco;
	}

	public boolean getPossuiAnimal() {
		return possui_animal_estimacao;
	}

	public void setPossuiAnimal(boolean possui_animal_estimacao) {
		this.possui_animal_estimacao = possui_animal_estimacao;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Autorizacao> getAutorizacoes() {
		return autorizacoes;
	}

	public void setAutorizacoes(List<Autorizacao> autorizacoes) {
		this.autorizacoes = autorizacoes;
	}
	
	@Override
	@JsonIgnore
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.autorizacoes;
	}

	@Override
	@JsonIgnore
	public String getPassword() {
		return senha;
	}

	@Override
	@JsonIgnore
	public String getUsername() {
		return nome;
	}

	@Override
	@JsonIgnore
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isEnabled() {
		return true;
	}

}

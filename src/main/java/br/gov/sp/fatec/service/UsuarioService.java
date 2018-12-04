package br.gov.sp.fatec.service;

import java.util.List;

import br.gov.sp.fatec.model.Usuario;

public interface UsuarioService {
	
	public Usuario incluirUsuario(String nome, String senha,
			String cpf, String celular, String apartamento, String carro, String bloco, 
			boolean possui_animal_estimacao, String nomeAutorizacao);
	
	public List<Usuario> buscar(String nome);
	
	public Usuario buscar(Long id);
	
	public List<Usuario> todos();
	
	public Usuario salvar(Usuario usuario);

}

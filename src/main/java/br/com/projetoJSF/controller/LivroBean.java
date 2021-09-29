package br.com.projetoJSF.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.projetoJSF.model.Livro;

@Named("livroBean")
@SessionScoped
public class LivroBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Livro livro;

	private List<Livro> livros = new ArrayList<Livro>();
	private int contador = 1;
	private String mensagem;
	
	private String estadoDescricao;
	
	//VERIFICAR COM O PROFESSOR!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	
	public String editarEstado() {

		if (livro.getEstado() == true) {
			return estadoDescricao = "Novo";
		}else {
			return estadoDescricao = "Usado";
		}
		
	}
	
		public String adicionar() {

			boolean status = validarFormulario();
			if (status == true) {
				livro.setId(contador);
				this.contador++;
				livros.add(livro);
				livro = new Livro();

				return "/paginas/livro/listarLivro.xhtml";

			} else {
				mensagem = "Por favor preencha todos os campos.";
				return null;
			}

		}
		

		public boolean validarFormulario() {

			if (livro.getTitulo().equals("") || livro.getTitulo().equals(null)) {
				return false;

			} else if (livro.getDescricao().equals("") || livro.getDescricao().equals(null)) {
				return false;

			} else if (livro.getGenero().equals("") || livro.getGenero().equals(null)) {
				return false;

			} else if (livro.getEditora().equals("") || livro.getEditora().equals(null)) {
				return false;

			}
			return true;
		}

		public void apagar(Livro l) {
			livros.remove(l);
		}

		
		public String editar() {
			int index = livros.indexOf(livro);

			livros.remove(livro);
			livros.add(index, livro);

			livro = new Livro();

			return "/paginas/livro/listarLivro.xhtml";

		}

		public String telaEdicao() {
			return "/paginas/livro/editarLivro.xhtml;";
		}

		public void carregarEdicao(ActionEvent event) {
			Livro l = (Livro) event.getComponent().getAttributes().get("livro");

			livro.setId(l.getId());
			livro.setTitulo(l.getTitulo());
			livro.setEditora(l.getEditora());
			livro.setGenero(l.getGenero());
			livro.setEstado(l.getEstado());
			livro.setDescricao(l.getDescricao());
		}
		
		public String sairEdicao() {
			livro = new Livro();
			return "/paginas/livro/listarLivro.xhtml";
		}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public List<Livro> getLivros() {
		return livros;
	}

	public void setLivros(List<Livro> livros) {
		this.livros = livros;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}

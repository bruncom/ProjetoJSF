package br.com.projetoJSF.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;



import br.com.projetoJSF.model.Pessoa;

@Named("pessoaBean")
@SessionScoped
public class PessoaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	// Inserindo injeção de dependência da classe Pessoa
	// Dentro da classe PessoaBean
	@Inject
	private Pessoa pessoa;

	private List<Pessoa> pessoas = new ArrayList<Pessoa>();

	private int contador = 1;
	private String mensagem;
	
	// Autenticação
	
	
	
	
	

// Método para adicionar pessoas da lista
	public String adicionar() {

		boolean status = validarFormulario();
		if (status == true) {
			pessoa.setId(contador);
			this.contador++;
			pessoas.add(pessoa);
			pessoa = new Pessoa();

			return "/paginas/listarPessoa.xhtml";

		} else {
			mensagem = "Os campos do formulário não estão preenchidos.";
			return null;
		}

	}

// Validando o preenchimento dos campos do formulário
	public boolean validarFormulario() {

		if (pessoa.getNome().equals("") || pessoa.getNome().equals(null)) {
			return false;

		} else if (pessoa.getProfissao().equals("") || pessoa.getProfissao().equals(null)) {
			return false;

		} else if (pessoa.getCidade().equals("") || pessoa.getCidade().equals(null)) {
			return false;

		} else if (pessoa.getIdade().equals("") || pessoa.getIdade().equals(null)) {
			return false;

		} else if (pessoa.getEmail().equals("") || pessoa.getEmail().equals(null)) {
			return false;

		}else if (pessoa.getSenha().equals("") || pessoa.getSenha().equals(null)) {
			return false;

		}
		return true;

	}

	// Método para remover passoas da lista
	public void apagar(Pessoa p) {
		pessoas.remove(p);
	}

	// Método para ditar passoa
	public String editar() {
		int index = pessoas.indexOf(pessoa);

		// Pessoa p = pessoa;

		pessoas.remove(pessoa);
		pessoas.add(index, pessoa);

		pessoa = new Pessoa();

		return "/paginas/listarPessoa.xhtml";

	}

	public String telaEdicao() {
		return "/paginas/editarPessoa.xhtml;";
	}

	public void carregarEdicao(ActionEvent event) {
		Pessoa p = (Pessoa) event.getComponent().getAttributes().get("pessoa");

		pessoa.setId(p.getId());
		pessoa.setNome(p.getNome());
		pessoa.setIdade(p.getIdade());
		pessoa.setCidade(p.getCidade());
		pessoa.setProfissao(p.getProfissao());
		pessoa.setEmail(p.getEmail());
		pessoa.setSenha(p.getSenha());
	}
	
	public String sairEdicao() {
		pessoa = new Pessoa();
		return "/paginas/listarPessoa.xhtml";
	}

	// getters e setters
	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}

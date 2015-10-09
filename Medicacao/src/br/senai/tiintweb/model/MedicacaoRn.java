package br.senai.tiintweb.model;

import java.util.List;

import br.senai.tiintweb.dao.MedicacaoDao;
import br.senai.tiintweb.dominio.Medicacao;

public class MedicacaoRn {
	
	private MedicacaoDao dao; 
	
	public MedicacaoRn(){
		dao = new MedicacaoDao();
	}
	public void salvar (Medicacao medicacao) throws Exception{
		if (medicacao.getNome().trim().isEmpty()){
			throw new Exception("O nome é um campo obrigatório!");
		}

	}
	
	public List<Medicacao> listar() {
		return dao.listarTodos();
	}

	public Medicacao buscarPorId(Long id) {
		return dao.buscarPorId(id);
	}

	public void excluir(Long id) throws Exception {
		dao.excluir(id);
	}
}

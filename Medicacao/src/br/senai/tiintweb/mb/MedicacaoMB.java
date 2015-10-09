package br.senai.tiintweb.mb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import br.senai.tiintweb.dominio.Medicacao;
import br.senai.tiintweb.model.MedicacaoRn;

@ManagedBean
public class MedicacaoMB {
	private List<Medicacao> listamedicacao;
	private Medicacao medicacao;
	private MedicacaoRn rn;

	@PostConstruct
	public void init() {
		medicacao = new Medicacao();
		rn = new MedicacaoRn();
	}
	
	public List<Medicacao> getListamedicao() {
		if(listamedicacao == null){
			listamedicacao = rn.listar();
 		}
		return listamedicacao;
	
	}
	public String salvar() {
		try {
			rn.salvar(medicacao);
		} catch (Exception e) {
			return "";
		}
		return "medicacaolist";
	}

	public String excluir(String idParam){
		Long id = Long.parseLong(idParam);
		try {
			rn.excluir(id);
			listamedicacao = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public String editar(String idParam){
		Long id = Long.parseLong(idParam);
		medicacao = rn.buscarPorId(id);
		return "medicacaoform";
	}
	
	
	public Medicacao getMedicacao() {
		return medicacao;
	}

	public void setMedicacao(Medicacao medicacao) {
		this.medicacao = medicacao;
	}

	public List<Medicacao> getListamedicacao() {
		return listamedicacao;
	}

	public void setListamedicacao(List<Medicacao> listamedicacao) {
		this.listamedicacao = listamedicacao;
	}

}

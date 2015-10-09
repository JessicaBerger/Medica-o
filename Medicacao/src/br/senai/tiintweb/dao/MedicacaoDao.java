package br.senai.tiintweb.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.senai.tiintweb.dominio.Medicacao;

public class MedicacaoDao extends Dao {

	private final String INSERT = "INSERT INTO user (nome, dosagem, intervalo, duracao) values (?,?,?,?)";
	private final String UPDATE = "UPDATE user SET nome = ?,  dosagem = ?, intervalo = ?, duracao = ?, WHERE id = ?";
	private final String DELETE = "DELETE FROM user WHERE id = ?";
	private final String SELECT = "SELECT * FROM medicacao";
	private final String SELECT_ID = "SELECT * FROM medicao WHERE id = ?";

	public void salvar(Medicacao medicacao) throws Exception {
		if (medicacao.getId() == 0) {
			inserir(medicacao);
		} else {
			alterar(medicacao);
		}
	}

	private void inserir(Medicacao medicacao) throws Exception {
		try {
			PreparedStatement ps = getConnection().prepareStatement(INSERT);
			ps.setString(1, medicacao.getNome());
			ps.setString(2, medicacao.getDosagem());
			ps.setString(3, medicacao.getIntervalo());
			ps.setString(4, medicacao.getDuracao());

			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Erro ao tentar salvar a medicação");
		}

	}

	private void alterar(Medicacao medicacao) {
		try {
			PreparedStatement ps = getConnection().prepareStatement(UPDATE);
			ps.setString(1, medicacao.getNome());
			ps.setString(2, medicacao.getDosagem());
			ps.setString(3, medicacao.getIntervalo());
			ps.setString(4, medicacao.getDuracao());

			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro ao tentar alterar a medicação");
		}

	}

	public void excluir(Long id) throws Exception {
		try {
			PreparedStatement ps = getConnection().prepareStatement(DELETE);
			ps.setLong(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro a executar o delete: " + e);
			throw new Exception("Erro ao tentar excluir");
		}
	}

	public List<Medicacao> listarTodos() {
		List<Medicacao> medicacoes = new ArrayList<Medicacao>();
		try {
			PreparedStatement ps = getConnection().prepareStatement(SELECT);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Medicacao medicacao = new Medicacao();
				medicacao.setNome(rs.getString("nome"));
				medicacao.setDosagem(rs.getString("dosagem"));
				medicacao.setIntervalo(rs.getString("dosagem"));
				medicacao.setDuracao(rs.getString("duracao"));
				medicacao.setId(rs.getLong("id"));
				medicacoes.add(medicacao);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro ao executar o select de medicao: " + e);
		}
		return medicacoes;
	}

	public Medicacao buscarPorId(Long id) {
		try {
			PreparedStatement ps = getConnection().prepareStatement(SELECT_ID);
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Medicacao medicacao = new Medicacao();
				medicacao.setNome(rs.getString("nome"));
				medicacao.setDosagem(rs.getString("dosagem"));
				medicacao.setIntervalo(rs.getString("dosagem"));
				medicacao.setDuracao(rs.getString("duracao"));
				medicacao.setId(rs.getLong("id"));
				return medicacao;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Erro ao executar o select de user: " + e);
		}
		return null;
	}

}

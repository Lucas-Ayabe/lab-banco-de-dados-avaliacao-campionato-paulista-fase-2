package com.fatec.campeonatopaulista.persistence;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.fatec.campeonatopaulista.models.Jogo;

import org.springframework.stereotype.Component;

@Component("sqlServerJogoDAO")
public class SQLServerJogoDAO implements JogoDAO {
	private Connection connection;

	public SQLServerJogoDAO(Connection connection) {
		this.connection = connection;
	}

	public SQLServerJogoDAO() {
		try {
			this.connection = ConnectionFactory.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Jogo> encontrarTodosPelaData(String date) {
		var dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
		var query = "{CALL sp_pesquisar_rodada(?)}";
		var rows = new ArrayList<Jogo>();

		try (var call = this.connection.prepareCall(query)) {
			call.setString(1, date);
			call.execute();
			var result = call.getResultSet();

			while (result.next()) {
				rows.add(
						new Jogo(
								result.getInt("CodigoJogo"),
								result.getString("NomeTimeA"),
								result.getString("NomeTimeB"),
								result.getInt("GolsTimeA"),
								result.getInt("GolsTimeB"),
								LocalDate.parse(result
										.getString("DataDoJogo"), dateFormatter)));
			}

		} catch (Exception exception) {
			exception.printStackTrace();
		}

		return rows;
	}

	@Override
	public List<Jogo> encontrarTodos() {
		var dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
		var query = "SELECT * FROM v_jogos";
		var rows = new ArrayList<Jogo>();

		try (var stmt = this.connection.prepareStatement(query)) {
			stmt.execute();
			var result = stmt.getResultSet();

			while (result.next()) {
				rows.add(
						new Jogo(
								result.getInt("CodigoJogo"),
								result.getString("NomeTimeA"),
								result.getString("NomeTimeB"),
								result.getInt("GolsTimeA"),
								result.getInt("GolsTimeB"),
								LocalDate.parse(result
										.getString("DataDoJogo"), dateFormatter)));
			}

		} catch (Exception exception) {
			exception.printStackTrace();
		}

		return rows;
	}

	@Override
	public void embaralhar() throws SQLException {
		try (var callInserirGrupos = this.connection.prepareCall("CALL sp_inserir_grupos")) {
			callInserirGrupos.execute();
		}

		try (var callInserirJogos = this.connection.prepareCall("CALL sp_inserir_jogos")) {
			callInserirJogos.execute();
		}
	}

	@Override
	public void atualizarResultado(
			int codigo, int golsDoTimeA, int golsDoTimeB) {
		var sql = "UPDATE Jogos SET GolsTimeA = ?, GolsTimeB = ? WHERE CodigoJogo = ?";
		try (var stmt = connection.prepareStatement(sql)) {
			stmt.setInt(1, golsDoTimeA);
			stmt.setInt(2, golsDoTimeB);
			stmt.setInt(3, codigo);

			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Jogo> listarQuartasDeFinal() {
		var query = "SELECT * FROM dbo.fn_gerar_quartas_de_final()";
		var rows = new ArrayList<Jogo>();

		try (var stmt = this.connection.prepareStatement(query)) {
			stmt.execute();
			var result = stmt.getResultSet();

			while (result.next()) {
				rows.add(
						new Jogo(
								result.getInt("CodigoJogo"),
								result.getString("NomeTimeA"),
								result.getString("NomeTimeB")));
			}

		} catch (Exception exception) {
			exception.printStackTrace();
		}

		return rows;
	}
}

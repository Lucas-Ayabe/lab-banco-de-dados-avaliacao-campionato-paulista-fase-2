package com.fatec.campeonatopaulista.persistence;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fatec.campeonatopaulista.models.Time;

import org.springframework.stereotype.Component;

import com.fatec.campeonatopaulista.models.Grupo;
import com.fatec.campeonatopaulista.models.Resultado;

@Component("sqlServerGrupoDAO")
public class SQLServerGrupoDAO implements GrupoDAO {
	private Connection connection;

	public SQLServerGrupoDAO(Connection connection) {
		this.connection = connection;
	}

	public SQLServerGrupoDAO() {
		try {
			this.connection = ConnectionFactory.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Optional<Grupo> procurar(String group) {
		var query = "SELECT * FROM v_grupos WHERE Grupo = ?";
		try (var stmt = this.connection.prepareStatement(query)) {
			stmt.setString(1, group);
			var result = stmt.executeQuery();
			result.next();

			if (result.getInt("Codigo") > 0) {
				var grupo = new Grupo(result.getInt("Codigo"), result.getString("Grupo"));
				do {
					grupo.adicionarTime(new Time(result.getInt("CodigoTime"), result.getString("Nome"),
							result.getString("Cidade"), result.getString("Estadio")));
				} while (result.next());

				return Optional.of(grupo);
			}
		} catch (Exception exception) {
			return Optional.empty();
		}

		return Optional.empty();
	}

	@Override
	public void sort() throws SQLException {
		try (var callInserirGrupos = this.connection.prepareCall("CALL sp_inserir_grupos")) {
			callInserirGrupos.execute();
		}
	}

	@Override
	public List<Resultado> procurarResultados(String grupo) {
		var resultados = new ArrayList<Resultado>();
		var sql = "SELECT * FROM dbo.fn_calcular_resultados_do_grupo(?)";
		try (var stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, grupo);
			var rows = stmt.executeQuery();

			while (rows.next()) {
				resultados.add(
						new Resultado(
								rows.getString("nome_time"),
								rows.getInt("num_jogos_disputados"),
								rows.getInt("vitorias"),
								rows.getInt("derrotas"),
								rows.getInt("empates"),
								rows.getInt("gols_marcados"),
								rows.getInt("gols_sofridos"),
								rows.getInt("saldo_gols"),
								rows.getInt("pontos")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultados;
	}

}

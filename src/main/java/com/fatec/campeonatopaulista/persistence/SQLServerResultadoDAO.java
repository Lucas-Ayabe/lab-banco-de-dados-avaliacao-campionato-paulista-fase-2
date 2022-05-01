package com.fatec.campeonatopaulista.persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fatec.campeonatopaulista.models.Resultado;

import org.springframework.stereotype.Component;

@Component("sqlServerResultadoDAO")
public class SQLServerResultadoDAO implements ResultadoDAO {
	private Connection connection;

	public SQLServerResultadoDAO(Connection connection) {
		this.connection = connection;
	}

	public SQLServerResultadoDAO() {
		try {
			this.connection = ConnectionFactory.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private List<Resultado> mapearLinhasEmResultados(ResultSet linhas) {
		var resultados = new ArrayList<Resultado>();
		try {
			while (linhas.next()) {
				resultados.add(
						new Resultado(
								linhas.getString("nome_time"),
								linhas.getInt("num_jogos_disputados"),
								linhas.getInt("vitorias"),
								linhas.getInt("derrotas"),
								linhas.getInt("empates"),
								linhas.getInt("gols_marcados"),
								linhas.getInt("gols_sofridos"),
								linhas.getInt("saldo_gols"),
								linhas.getInt("pontos")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultados;
	}

	@Override
	public List<Resultado> procurarResultadosGerais() {
		var sql = "SELECT * FROM dbo.fn_calcular_resultados_gerais() ORDER BY pontos DESC, vitorias DESC, gols_marcados DESC, saldo_gols DESC";
		try (var stmt = connection.prepareStatement(sql)) {
			return mapearLinhasEmResultados(stmt.executeQuery());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ArrayList<>();
	}

	@Override
	public List<Resultado> procurarResultadosPorGrupo(String grupo) {
		var sql = "SELECT * FROM dbo.fn_calcular_resultados_do_grupo(?) ORDER BY pontos DESC, vitorias DESC, gols_marcados DESC, saldo_gols DESC";
		try (var stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, grupo);
			return mapearLinhasEmResultados(stmt.executeQuery());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ArrayList<>();
	}

}

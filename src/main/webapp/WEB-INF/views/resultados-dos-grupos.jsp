<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Resultados dos grupos</title>
        <link
            rel="stylesheet"
            href="/css/pico.css"
        />
        <link
            rel="stylesheet"
            href="/css/styles.css"
        />
    </head>
    <body>
		<header class="container pb-0">
			<%@ include file="../templates/navbar.jsp" %>

			<div class="grid align-start">
				<h1>Resultados dos grupos</h1>
                <div></div>
			</div>
		</header>
    	
    	<main class="container pt-0">
			<c:choose>
				<c:when test="${!grupos.isEmpty()}">
					<c:forEach var="resultadoDoGrupo" items="${resultadosDosGrupos}">
						<figure>
							<table>
								<caption>Grupo ${resultadoDoGrupo.getGrupo()}</caption>
								<thead>
									<tr>
										<th scope="col">Time</th>
										<th scope="col">Jogos disputados</th>
										<th scope="col">Vitórias</th>
										<th scope="col">Empates</th>
										<th scope="col">Derrotas</th>
										<th scope="col">Gols Marcados</th>
										<th scope="col">Gols Sofridos</th>
										<th scope="col">Saldo de gols</th>
										<th scope="col">Pontos</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="resultado" items="${resultadoDoGrupo.getResultados()}">
										<tr>
											<td>${resultado.getTime()}</td>
											<td>${resultado.getJogosDisputados()}</td>
                                            <td>${resultado.getVitorias()}</td>
                                            <td>${resultado.getEmpates()}</td>
                                            <td>${resultado.getDerrotas()}</td>
                                            <td>${resultado.getGolsMarcados()}</td>
                                            <td>${resultado.getGolsSofridos()}</td>
                                            <td>${resultado.getSaldoDeGols()}</td>
                                            <td>${resultado.getPontos()}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</figure>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<p>Nenhum grupo sorteado ainda, clique no botão acima para gerar os grupos.</p>
				</c:otherwise>
			</c:choose>
		</main>
    </body>
</html>

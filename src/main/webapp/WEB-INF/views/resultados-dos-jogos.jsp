<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Resultado dos jogos</title>
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
    			<h1>Resultados dos jogos</h1>
                <div></div>
    		</div>
    	</header>
    	<main class="container pt-0">
			<form method="POST" action="/jogos/resultados">
				<c:choose>
					<c:when test="${!jogos.isEmpty()}">
						<figure>
							<table>
								<caption></caption>
								<thead>
									<tr>
										<th scope="col">Time A</th>
										<th scope="col">Time B</th>
										<th scope="col">Gols do Time A</th>
										<th scope="col">Gols do Time B</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="jogo" items="${jogos}">
										<input name="codigo[]" type="hidden" value="${jogo.getCodigo()}" />
										<tr>
											<td><c:out value="${jogo.getNomeDoTimeA()}"></c:out></td>
											<td><c:out value="${jogo.getNomeDoTimeB()}"></c:out></td>
											<td>
												<input name="golsTimeA[]" type="number" min="0" value="${jogo.getGolsDoTimeA()}" />
											</td>
											<td>
												<input name="golsTimeB[]" type="number" min="0" value="${jogo.getGolsDoTimeB()}" />
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</figure>
					</c:when>
					<c:otherwise>
						<p>Nenhum jogo encontrado, tente pesquisar utilizando o formulário acima.</p>
					</c:otherwise>
				</c:choose>

				<button>Cadastrar resultados</button>
			</form>
		</main>
    </body>
</html>

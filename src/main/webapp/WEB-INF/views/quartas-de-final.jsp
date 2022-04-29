<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Quartas de final</title>
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
				<h1>Quartas de final</h1>
                <div></div>
			</div>
		</header>
    	
    	<main class="container pt-0">
			<c:choose>
				<c:when test="${!jogos.isEmpty()}">
                    <figure>
                        <table>
                            <caption>Jogos</caption>
                            <thead>
                                <tr>
                                    <th scope="col">Time A</th>
                                    <th scope="col">Time B</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="jogo" items="${jogos}">
                                    <tr>
                                        <td><c:out value="${jogo.getNomeDoTimeA()}"></c:out></td>
                                        <td><c:out value="${jogo.getNomeDoTimeB()}"></c:out></td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </figure>
				</c:when>
				<c:otherwise>
					<p>Sem previsão ainda.</p>
				</c:otherwise>
			</c:choose>
		</main>
    </body>
</html>

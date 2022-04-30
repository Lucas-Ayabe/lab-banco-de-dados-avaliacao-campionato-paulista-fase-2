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
                    <div class="stack">
                        <c:forEach var="jogo" items="${jogos}">
                            <article class="match grid">
                                <div class="team"><c:out value="${jogo.getNomeDoTimeA()}"></c:out></div>
                                <div class="versus">VS</div>
                                <div class="team"><c:out value="${jogo.getNomeDoTimeB()}"></c:out></div>
                            </article>
                        </c:forEach>
                    </div>
				</c:when>
				<c:otherwise>
					<p>Sem previsão ainda.</p>
				</c:otherwise>
			</c:choose>
		</main>
    </body>
</html>

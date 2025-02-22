<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.Javabeans"%>
<%@ page import="java.util.ArrayList"%>
<%

	ArrayList<Javabeans> lista = (ArrayList<Javabeans>) request.getAttribute("contatos");
%>
<!DOCTYPE html>
<html lang="pt-BR">
	<head>
		<meta charset="UTF-8">
		<title>Agenda de contatos</title>
		<link rel="icon" href="images/favicon.png">
		<link rel="stylesheet" href="style2.css">
	</head>
<body>
	<h1>Agenda de contatos</h1>
	<a href="novo.html" class="botao2">Novo contato</a>
	<a href="report" class="del-btn">Relatório</a>
	<table id="tabela">
		<thead>
			<tr>
				<th>ID</th>
				<th>Nome</th>
				<th>Fone</th>
				<th>Email</th>
				<th>Opções</th>			
			<tbody>
				
				<%for(int i = 0;i<lista.size();i++){ %>
				
					<tr>
						<td><%=lista.get(i).getId() %></td>
						<td><%=lista.get(i).getNome()%></td>
						<td><%=lista.get(i).getFone()%></td>
						<td><%=lista.get(i).getEmail()%></td>
						<td><a href="select?id=<%=lista.get(i).getId() %>" class="botao1">Editar</a> <a href="javascript: confirmar(<%=lista.get(i).getId() %>)" class="del-btn">Excluir</a></td>
					</tr>
				
				<%} %>
			</tbody>
		</thead>
	</table>
	<script src="scripts/confirmador.js"></script>
</body>
</html>
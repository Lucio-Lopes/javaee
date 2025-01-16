<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Editar contato</title>
		<link rel="stylesheet" href="style2.css">
	</head>
	<body>
		<h1>Editar contato</h1>
		<form name="formContato" action="update">
		
			<table>
				
				<tr>
					<td><input type="text" name="id" id="caixa" readonly value="<%out.println(request.getAttribute("id")); %>"></td>
				</tr>
				<tr>
					<td><input type="text" name="nome" placeholder="Nome*" value="<%out.println(request.getAttribute("nome")); %>"></td>
				</tr>
				<tr>
					<td><input type="text" name="fone" placeholder="Fone*" class="caixa1" value="<%out.println(request.getAttribute("fone")); %>"></td>
				</tr>
				<tr>
					<td><input type="text" name="email" placeholder="Email" value="<%out.println(request.getAttribute("email")); %>"></td>
				</tr>
			
			</table>
			<input type="button" value="Editar" class="botao1" onclick="validar()">
		</form>
		<script src="scripts/validador.js"></script>
	</body>
</html>
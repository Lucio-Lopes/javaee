function confirmar(id){
	
	let resposta = confirm("Confirma a exclusão desse contato?");
	if(resposta === true){
		
		location.href = "delete?id=" + id;
		
	}
	
}
package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import model.DAO;
import model.Javabeans;
@WebServlet(urlPatterns ={"/Controller","/main", "/insert", "/select", "/update","/delete","/report"})
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DAO dao = new DAO();
	Javabeans contato = new Javabeans();
    public Controller() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getServletPath();
		
		if(action.equals("/main")){
			contatos(request,response);
		} else if(action.equals("/insert")) {
			
			novoContato(request, response);
			
		} else if(action.equals("/select")) {
			
			listarContato(request, response);
			
		} else if(action.equals("/update")) {
			
			editarContato(request, response);
			
		} else if(action.equals("/delete")) {
			
			removerContato(request, response);
			
		} else if(action.equals("/report")) {
			
			gerarRelatorio(request, response);
			
		}else{
			
			response.sendRedirect("agenda.jsp");
			
		}
		
	}
	
	//Listar contatos
	protected void contatos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//Criando um objeto que irá receber os dados Javabeans
		
		ArrayList<Javabeans> lista = dao.listarContatos();
		
		request.setAttribute("contatos",lista);
		RequestDispatcher rd = request.getRequestDispatcher("Agenda.jsp");
		rd.forward(request, response);
	}
	
	protected void novoContato(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//setar variaveis javabeans
		contato.setNome(request.getParameter("nome"));
		contato.setFone(request.getParameter("fone"));
		contato.setEmail(request.getParameter("email"));
		dao.inserirContato(contato);
		response.sendRedirect("main");
		
	}
	
	protected void listarContato(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String id = request.getParameter("id");
		contato.setId(id);
		dao.selecionarContato(contato);
		request.setAttribute("id", contato.getId());
		request.setAttribute("nome", contato.getNome());
		request.setAttribute("fone", contato.getFone());
		request.setAttribute("email", contato.getEmail());
		RequestDispatcher rd  = request.getRequestDispatcher("editar.jsp");
		rd.forward(request, response);
	}
	protected void editarContato(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		contato.setId(request.getParameter("id"));
		contato.setNome(request.getParameter("nome"));
		contato.setFone(request.getParameter("fone"));
		contato.setEmail(request.getParameter("email"));
		
		dao.alterarContato(contato);
		response.sendRedirect("main");
	}
	protected void removerContato(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String id = request.getParameter("id");
		contato.setId(id);
		dao.deletarContato(contato);
		response.sendRedirect("main");
	}
	protected void gerarRelatorio(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		Document documento = new Document();
		try {
			
			response.setContentType("apllication/pdf");
			
			response.addHeader("Content-Disposition", "inline; filename=" + "contatos.pdf");
			
			PdfWriter.getInstance(documento, response.getOutputStream());
			
			documento.open();
			documento.add(new Paragraph("Lista de contatos:"));
			documento.add(new Paragraph(" "));
			PdfPTable tabela = new PdfPTable(3);
			PdfPCell col1 = new PdfPCell(new Paragraph("Nome"));
			PdfPCell col2 = new PdfPCell(new Paragraph("Fone"));
			PdfPCell col3 = new PdfPCell(new Paragraph("Email"));
			tabela.addCell(col1);
			tabela.addCell(col2);
			tabela.addCell(col3);
			ArrayList<Javabeans> lista = dao.listarContatos();
			for(int i=0;i<lista.size();i++) {
				
				tabela.addCell(lista.get(i).getNome());
				tabela.addCell(lista.get(i).getFone());
				tabela.addCell(lista.get(i).getEmail());
				
			}
			documento.add(tabela);
			documento.close();
		} catch (Exception e) {
			System.out.println(e);
			documento.close();
		}
	}
}

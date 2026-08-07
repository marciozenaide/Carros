package br.com.carros.view;

import java.io.PrintWriter;
import java.util.List;

import br.com.carros.model.Carro;

public class CarroHtmlRenderer {

	public CarroHtmlRenderer() {
	}

	public void renderHeader(PrintWriter out) {
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset='UTF-8'>");
		out.println("<title>Carros</title>");
		out.println("</head>");
	}

	public void renderForm(PrintWriter out, Carro carro, List<String> erros) {
		
		renderStyle(out, 600, 400);

		out.println("<DIV class='minha-div'>");
		out.println("    <h1>Formulario carro</h1>");
		out.println("		<form action='carros' method='post'>");
		out.println("<div>");
		out.println("			<input type='hidden' name='id' value='" + (carro.getId() == null ? "" : carro.getId())
				+ "'></br>");
		out.println("			<lable for='nome'>" + "Nome: " + "</lable>");
		out.println("			<input type='text' name='nome' id='nome' value='" + value(carro.getNome()) + "'></br>");
		out.println("			<lable for='descricao'>" + "Descrição: " + "</lable>");
		out.println("			<input type='text' name='descricao' id='descricao' value='"
				+ value(carro.getDescricao()) + "'></br>");
		out.println("			<lable for='tipo'>" + "Tipo: " + "</lable>");
		out.println("			<input type='text' name='tipo' id='tipo' value='" + value(carro.getTipo()) + "'></br>");
		out.println("</div>");
		out.println("<div>");
		out.println("			<lable for='latitude'>" + "Latitude: " + "</lable>");
		out.println("			<input type='text' name='latitude' id='latitude' value='" + carro.getLatitude()
				+ "'></br>");
		out.println("			<lable for='longitude'>" + "Longitude: " + "</lable>");
		out.println("			<input type='text' name='longitude' id='longitude'  value='" + carro.getLongitude()
				+ "'></br>");
		out.println("</div>");
		out.println("<div>");
		out.println("			<lable for='urlFoto'>" + "URL Foto: " + "</lable>");
		out.println("			<input type='text' name='urlFoto' id='urlFoto' value='" + value(carro.getUrlFoto())
				+ "'></br>");
		out.println("			<lable for='urlVideo'>" + "URL Video: " + "</lable>");
		out.println("			<input type='text' name='urlVideo' id='urlVideo' value='" + value(carro.getUrlVideo())
				+ "'></br>");
		out.println("</div>");
		out.println("<input type='hidden' name='acao' id='acao' value='salvar'></br>");
		out.println("<input type='submit' value='Salvar'></br>");
		out.println("		</form>");

		if (!erros.isEmpty()) {
			out.println("<ul style='color:red'>");

			for (String erro : erros) {
				out.println("<li>" + erro + "</li>");
			}

			out.println("</ul>");
		}
		out.println("</DIV>");
	}

	public void renderListar(PrintWriter out, List<Carro> carros) {
		
		renderStyle(out, 400, 300);

		out.println("<body>");
		out.println("    <h1>Lista de carros</h1>");
		out.println("		<form>");
		out.println("    		<input type='submit' name='acao' id='acao' value='novo'>");
		out.println("		</form>");
		out.println("		<form action='carros'>");
		out.println("		<input type='submit' name='acao' id='acao' value='editar'>");
		out.println("    	<DIV class='minha-div'>");
		out.println("		<ol>");
		for (Carro carro : carros) {
			out.println("		<li>" + carro.getNome());
			out.println("    		<input type='radio' name='id' id='id_" + carro.getId() + "' value=\""
					+ carro.getId() + "\">");
			out.println("		</li>");
		}
		out.println("		</ol>");
		out.println("    	</DIV>");
		out.println("		</form>");
		out.println("</body>");
	}
	
	private void renderStyle(PrintWriter out, int width, int height) {
	    out.println("<style>");
	    out.println(".minha-div {");
	    out.println("width:" + width + "px;");
	    out.println("height:" + height + "px;");
	    out.println("overflow:auto;");
	    out.println("border:1px solid #ccc;");
	    out.println("}");
	    out.println("</style>");
	}

	public void renderFooter(PrintWriter out) {
		out.println("</html>");
	}

	private String value(String texto) {
		return texto == null ? "" : texto;
	}

}

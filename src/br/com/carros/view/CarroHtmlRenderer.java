package br.com.carros.view;

import java.io.PrintWriter;
import java.util.List;

import br.com.carros.i18n.Messages;
import br.com.carros.model.Carro;

public class CarroHtmlRenderer {

	public CarroHtmlRenderer() {
	}

	private static final String HEADER =
		    "<!DOCTYPE html>\n" +
		    "<html>\n" +
		    "<head>\n" +
		    "<meta charset=\"%s\">\n" +
		    "<title>%s</title>\n" +
		    "<link rel='stylesheet' href='resources/css/style.css'>\n" +
		    "</head>\n";
	
	public void renderHeader(PrintWriter out) {
		out.printf(HEADER,"UTF-8",Messages.get("app.title"));
	}

	public void renderForm(PrintWriter out, Carro carro, List<String> erros) {

		out.println("<DIV class='form-div'>");
		out.println("    <h1>" + Messages.get("carro.formulario.carro") + "</h1>");
		out.println("		<form action='carros' method='post'>");
		out.println("<div>");
		out.println("			<input type='hidden' name='id' value='" + (carro.getId() == null ? "" : carro.getId())
				+ "'></br>");
		out.println("			<lable for='nome'>" + Messages.get("carro.nome") + "</lable>");
		out.println("			<input type='text' name='nome' id='nome' value='" + value(carro.getNome()) + "'></br>");
		out.println("			<lable for='descricao'>" + Messages.get("carro.descricao") + "</lable>");
		out.println("			<input type='text' name='descricao' id='descricao' value='"
				+ value(carro.getDescricao()) + "'></br>");
		out.println("			<lable for='tipo'>" + Messages.get("carro.tipo") + "</lable>");
		out.println("			<input type='text' name='tipo' id='tipo' value='" + value(carro.getTipo()) + "'></br>");
		out.println("</div>");
		out.println("<div>");
		out.println("			<lable for='latitude'>" + Messages.get("carro.latitude") + "</lable>");
		out.println("			<input type='text' name='latitude' id='latitude' value='" + carro.getLatitude()
				+ "'></br>");
		out.println("			<lable for='longitude'>" + Messages.get("carro.longitude") + "</lable>");
		out.println("			<input type='text' name='longitude' id='longitude'  value='" + carro.getLongitude()
				+ "'></br>");
		out.println("</div>");
		out.println("<div>");
		out.println("			<lable for='urlFoto'>" + Messages.get("carro.url.foto") + "</lable>");
		out.println("			<input type='text' name='urlFoto' id='urlFoto' value='" + value(carro.getUrlFoto())
				+ "'></br>");
		out.println("			<lable for='urlVideo'>" + Messages.get("carro.url.video") + "</lable>");
		out.println("			<input type='text' name='urlVideo' id='urlVideo' value='" + value(carro.getUrlVideo())
				+ "'></br>");
		out.println("</div>");
		out.println("<input type='hidden' name='acao' id='acao' value='" + Messages.get("botao.salvar") + "'></br>");
		out.println("<input type='submit' value='" + Messages.get("botao.salvar") + "'></br>");
		out.println("		</form>");

		renderErros(out, erros);
		
		out.println("</DIV>");
	}
	
	private void renderErros(PrintWriter out, List<String> erros) {
		if (!erros.isEmpty()) {
			out.println("<ul style='color:red'>");
			
			for (String erro : erros) {
				out.println("<li>" + erro + "</li>");
			}
			
			out.println("</ul>");
		}
	}

	public void renderListar(PrintWriter out, List<Carro> carros) {
		
		out.println("<body>");
		out.println("    <h1>" + Messages.get("carro.lista.de.carro") +"</h1>");
		out.println("		<form>");
		out.println("    		<input type='submit' name='acao' id='acao' value='" + Messages.get("botao.novo") + "'>");
		out.println("		</form>");
		out.println("		<form action='carros'>");
		out.println("		<input type='submit' name='acao' id='acao' value='" + Messages.get("botao.editar") + "'>");
		out.println("    	<DIV class='lista-div'>");
		out.println("<table class='tabela-carros'>");
		out.println("<tr>");
		out.println("	<th>");
		out.println(Messages.get("carro.selecionado"));
		out.println("	</th>");
		out.println("	<th>");
		out.println(Messages.get("carro.nome.coluna"));
		out.println("	</th>");
	    out.println("</tr>");
		for (Carro carro : carros) {
			out.println("<tr>");
			out.println("	<td>");
			out.println("		<input type='radio' name='id' id='id_" + carro.getId() + "' value=\"" + carro.getId() + "\">");
			out.println("	</td>");
			out.println("	<td>");
			out.println(		carro.getNome());
			out.println("	</td>");
			out.println("</tr>");
		}
		out.println("</table>");
		out.println("    	</DIV>");
		out.println("		</form>");
		out.println("</body>");
	}
	
	public void renderFooter(PrintWriter out) {
		out.println("</html>");
	}

	private String value(String texto) {
		return texto == null ? "" : texto;
	}

}

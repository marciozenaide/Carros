package br.com.carros.servlets;


import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.carros.model.Carro;
import br.com.carros.service.CarroService;
import br.com.carros.util.LogFactory;
import br.com.carros.validation.CarroValidator;
import br.com.carros.validation.ValidatorResult;


@WebServlet("/carros")
public class CarroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final CarroService carroService = new CarroService();

	private static final Logger LOGGER = LogFactory.getLogger(CarroServlet.class);

	private static final String ACAO_LISTAR = "listar";
	private static final String ACAO_BUSCAR = "buscar";
	private static final String ACAO_NOVO = "novo";

	private static final String ACAO_EDITAR = "editar";
	private static final String ACAO_SALVAR = "salvar";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		dispatch(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		dispatch(req, resp);
	}

	private void dispatch(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("acao");

		try {

			if (action == null) {
				action = ACAO_LISTAR;
			}

			switch (action) {
			case ACAO_LISTAR:
				listar(resp);
				break;

			case ACAO_BUSCAR:
				buscar(req, resp);
				break;

			case ACAO_NOVO:
				novo(req, resp);
				break;
				
			case ACAO_EDITAR:
				editar(req, resp);
				break;
				
			case ACAO_SALVAR:
				salvar(req, resp);
				break;

			default:
				resp.sendError(HttpServletResponse.SC_NOT_FOUND);
			}

		} catch (Exception e) {

			LOGGER.log(Level.SEVERE, "Erro ao processar ação: " + action, e);

			resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro interno do servidor.");
		}
	}

	private void listar(HttpServletResponse resp) throws IOException {
		List<Carro> carros = carroService.findAll();
		configurarResposta(resp);
		PrintWriter out = resp.getWriter();
		renderHeader(out);
		renderBody(out, carros);
		renderFooter(out);
	}
	
	/*
	 * private void listar(HttpServletResponse resp) throws IOException {
	 * List<Carro> carros = carroService.findAll();
	 * 
	 * configurarResposta(resp);
	 * 
	 * CarroHtmlRenderer renderer = new CarroHtmlRenderer(resp.getWriter());
	 * 
	 * renderer.renderLista(carros); }
	 */

	private void buscar(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	}

	private void novo(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

		configurarResposta(resp);
		PrintWriter out = resp.getWriter();
		Carro carro = new Carro();
		ValidatorResult resultado = new ValidatorResult();
		
		renderHeader(out);
		renderForm(out, carro, resultado.getErros());
		resp.getWriter().println("Tela de cadastro.");
		renderFooter(out);
		
	}

	private void editar(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		configurarResposta(resp);
		String idCarro  = req.getParameter("id");
		LOGGER.log(Level.INFO,"Id carro: " + idCarro);
		long id = 0;
		if (idCarro != null && !idCarro.trim().isEmpty()) {
		    id = Long.valueOf(idCarro);
		}
		
		Optional<Carro> optionalCarro = carroService.findById(id);

		if (!optionalCarro.isPresent()) {
		    resp.sendError(HttpServletResponse.SC_NOT_FOUND);
		    return;
		}

		Carro carro = optionalCarro.get();
		
		PrintWriter out = resp.getWriter();
		
		renderHeader(out);
		renderForm(out, carro, Collections.emptyList());
		resp.getWriter().println("Tela de cadastro.");
		renderFooter(out);
		
		
	}

	private void salvar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		CarroValidator validator = new CarroValidator();
		
		Carro carro = criarCarro(req);

		ValidatorResult resultado = validator.validar(carro);

		if (!resultado.isValid()) {

			req.setAttribute("erros", resultado.getErros());
			
			List<Carro> carros = carroService.findAll();		
			
			configurarResposta(resp);

			PrintWriter out = resp.getWriter();

			renderHeader(out);
			renderForm(out, carro, resultado.getErros());
			renderFooter(out);

			return;

		}

		carroService.save(carro);
		resp.sendRedirect("carros?acao=listar");
	}

	private void renderHeader(PrintWriter out) {
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset='UTF-8'>");
		out.println("<title>Carros</title>");
		out.println("</head>");
	}

	private void renderBody(PrintWriter out, List<Carro> carros) {
		out.println("<body>");
		out.println("    <h1>Lista de carros</h1>");
		out.println("		<form>");
		out.println("    		<input type='submit' name='acao' id='acao' value='novo'>");
		out.println("		</form>");
		out.println("		<form action='carros'>");
				out.println("<input type='submit' name='acao' id='acao' value='editar'>");
				out.println("    <ol>");
				for (Carro carro : carros) {
					out.println("<li>" + carro.getNome());	
					out.println("    <input type='radio' name='id' id='id_"  + carro.getId() + "' value=\"" + carro.getId() + "\">");
					out.println("</li>");
				}
				out.println("	</ol>");
		out.println("		</form>");
		out.println("</body>");
	}
	private void renderForm(PrintWriter out, Carro carro, List<String> erros) {
		
		out.println("    <h1>Formulario carro</h1>");
		out.println("		<form action='carros' method='post'>");
		out.println("<div>");
		out.println("			<input type='hidden' name='id' value='" + (carro.getId() == null ? "" : carro.getId()) + "'></br>");
		out.println("			<lable for='nome'>" + "Nome: " + "</lable></br>");
		out.println("			<input type='text' name='nome' id='nome' value='" + value(carro.getNome()) + "'></br>");
		out.println("			<lable for='descricao'>" + "Descrição: " + "</lable></br>");
		out.println("			<input type='text' name='descricao' id='descricao' value='" + value(carro.getDescricao()) + "'></br>");
		out.println("			<lable for='tipo'>" + "Tipo: " + "</lable></br>");
		out.println("			<input type='text' name='tipo' id='tipo' value='" + value(carro.getTipo()) + "'></br>");
		out.println("</div>");
		out.println("<div>");
		out.println("			<lable for='latitude'>" + "Latitude: " + "</lable></br>");
		out.println("			<input type='text' name='latitude' id='latitude' value='" + carro.getLatitude() + "'></br>");
		out.println("			<lable for='longitude'>" + "Longitude: " + "</lable></br>");
		out.println("			<input type='text' name='longitude' id='longitude'  value='" + carro.getLongitude() + "'></br>");
		out.println("</div>");
		out.println("<div>");
		out.println("			<lable for='urlFoto'>" + "URL Foto: " + "</lable></br>");
		out.println("			<input type='text' name='urlFoto' id='urlFoto' value='" + value(carro.getUrlFoto()) + "'></br>");
		out.println("			<lable for='urlVideo'>" + "URL Video: " + "</lable></br>");
		out.println("			<input type='text' name='urlVideo' id='urlVideo' value='" + value(carro.getUrlVideo()) + "'></br>");
		out.println("</div>");
		out.println("    		<input type='hidden' name='acao' id='acao' value='salvar'></br>");
		out.println("    		<input type='submit' value='Salvar'></br>");
		out.println("		</form>");
		
		if (!erros.isEmpty()) {
		    out.println("<ul style='color:red'>");

		    for (String erro : erros) {
		        out.println("<li>" + erro + "</li>");
		    }

		    out.println("</ul>");
		}
		
	}

	private void renderFooter(PrintWriter out) {
		out.println("</html>");
	}

	private void configurarResposta(HttpServletResponse resp) {
		resp.setContentType("text/html");
		resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
	}
	
	private Carro criarCarro(HttpServletRequest req) throws UnsupportedEncodingException {
		
	    Carro carro = new Carro();
	    
	    String id = req.getParameter("id");

	    if (id != null && !id.trim().isEmpty()) {
	        carro.setId(Long.valueOf(id));
	    }

	    carro.setNome(req.getParameter("nome"));
	    carro.setDescricao(req.getParameter("descricao"));
	    carro.setTipo(req.getParameter("tipo"));
	    carro.setLongitude(toDouble(req.getParameter("longitude")));
	    carro.setLatitude(toDouble(req.getParameter("latitude")));
	    carro.setUrlFoto(req.getParameter("urlFoto"));
	    carro.setUrlVideo(req.getParameter("urlVideo"));

	    return carro;
	}
	
	private Double toDouble(String valor) {
	    try {
	        return valor == null || valor.trim().isEmpty()
	            ? null
	            : Double.valueOf(valor);
	    } catch (NumberFormatException e) {
	        return null;
	    }
	}
	
	private String value(String texto) {
	    return texto == null ? "" : texto;
	}

}

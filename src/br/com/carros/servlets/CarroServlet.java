package br.com.carros.servlets;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;
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

@WebServlet("/carros")
public class CarroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final CarroService carroService = new CarroService();

	private static final Logger LOGGER = LogFactory.getLogger(CarroServlet.class);

	private static final String ACAO_LISTAR = "listar";
	private static final String ACAO_BUSCAR = "buscar";
	private static final String ACAO_NOVO = "novo";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		dispatch(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		dispatch(req, resp);
	}

	private void dispatch(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String action = req.getParameter("action");

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

	private void buscar(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	}

	private void novo(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.getWriter().println("Tela de cadastro.");
	}

	private void editar(HttpServletRequest req, HttpServletResponse resp) throws IOException {

	}

	private void salvar(HttpServletRequest req, HttpServletResponse resp) throws IOException {

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
		out.println("    <ol>");
		for (Carro carro : carros) {
			out.println("<li>" + carro.getNome() + "</li>");
		}
		out.println("	</ol>");
		out.println("</body>");
	}

	private void renderFooter(PrintWriter out) {
		out.println("</html>");
	}

	private void configurarResposta(HttpServletResponse resp) {
	    resp.setContentType("text/html");
	    resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
	}

}

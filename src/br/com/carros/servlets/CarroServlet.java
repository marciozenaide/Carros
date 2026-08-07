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
import br.com.carros.view.CarroHtmlRenderer;

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

	private final CarroHtmlRenderer renderer = new CarroHtmlRenderer();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		dispatch(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		dispatch(req, resp);
	}

	private void dispatch(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String action = req.getParameter("acao").toLowerCase();

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
		renderer.renderHeader(out);
		renderer.renderListar(out, carros);
		renderer.renderFooter(out);
	}

	private void buscar(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	}

	private void novo(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

		configurarResposta(resp);
		PrintWriter out = resp.getWriter();
		Carro carro = new Carro();
		ValidatorResult resultado = new ValidatorResult();

		renderer.renderHeader(out);
		renderer.renderForm(out, carro, resultado.getErros());
		renderer.renderFooter(out);

	}

	private void editar(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

		configurarResposta(resp);
		String idCarro = req.getParameter("id");
		LOGGER.log(Level.INFO, "Id carro: " + idCarro);
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

		renderer.renderHeader(out);
		renderer.renderForm(out, carro, Collections.emptyList());
		renderer.renderFooter(out);

	}

	private void salvar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		CarroValidator validator = new CarroValidator();

		Carro carro = criarCarro(req);

		ValidatorResult resultado = validator.validar(carro);

		if (!resultado.isValid()) {

			req.setAttribute("erros", resultado.getErros());

			List<Carro> carros = carroService.findAll();

			configurarResposta(resp);

			PrintWriter out = resp.getWriter();

			renderer.renderHeader(out);
			renderer.renderForm(out, carro, resultado.getErros());
			renderer.renderFooter(out);

			return;
		}

		carroService.save(carro);
		resp.sendRedirect("carros?acao=listar");
	}
	
	private void configurarResposta(HttpServletResponse resp) {
	    resp.setContentType("text/html; charset=UTF-8");
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
			return valor == null || valor.trim().isEmpty() ? null : Double.valueOf(valor);
		} catch (NumberFormatException e) {
			return null;
		}
	}

}

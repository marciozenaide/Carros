package br.com.carros.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import br.com.carros.i18n.MessageKeys;
import br.com.carros.i18n.Messages;
import br.com.carros.model.Carro;

/*
 * um teste para garantir que vários erros são acumulados.
 */

@DisplayName("Testes de Validação do Modelo Carro")
class CarrosValidatorTest {

	private final CarroValidator validator = new CarroValidator();

	private String repetir(char caractere, int quantidade) {
		StringBuilder texto = new StringBuilder(quantidade);

		for (int i = 0; i < quantidade; i++) {
			texto.append(caractere);
		}

		return texto.toString();
	}

	private Carro criarCarroValido() {
		Carro carro = new Carro();

		carro.setNome("Ferrari");
		carro.setDescricao("Descricao");
		carro.setTipo("Tipo");
		carro.setUrlFoto("http://foto.com");
		carro.setUrlVideo("http://video.com");
		carro.setLatitude(10.0);
		carro.setLongitude(20.0);

		return carro;
	}

	@Nested
	@DisplayName("Validações de Campos Obrigatórios (Null/Empty)")
	class CamposObrigatoriosTest {
		
		@Test
		@DisplayName("Deve acumular erros quando vários campos forem inválidos")
		void deveAcumularErros() {
		    Carro carro = new Carro();

		    ValidatorResult resultado = validator.validar(carro);

		    assertFalse(resultado.isValid());
		    assertEquals(9, resultado.getErros().size());
		}

		@ParameterizedTest
		@NullAndEmptySource
		@ValueSource(strings = { " " })
		@DisplayName("Deve acusar erro quando o nome for inválido")
		void deveValidarNomeObrigatorio(String nome) {
			Carro carro = criarCarroValido();
			carro.setNome(nome);

			ValidatorResult resultado = validator.validar(carro);

			assertFalse(resultado.isValid());
			assertEquals(Messages.get(MessageKeys.ERRO_NOME_OBRIGATORIO), resultado.getErros().get(0));
		}
		
		@ParameterizedTest
		@ValueSource(strings = { "  nome  ", "nome  ", "  nome" })
		@DisplayName("Deve aceitar o nome com espaços ao redor do texto")
		void deveAceitarNomeComEspacos(String nome) {
			Carro carro = criarCarroValido();
			carro.setNome(nome);

			ValidatorResult resultado = validator.validar(carro);

			assertTrue(resultado.isValid());
		}

		@ParameterizedTest
		@NullAndEmptySource
		@ValueSource(strings = {" "})
		@DisplayName("Deve acusar erro quando a descrição for inválida")
		void deveValidarDescricaoObrigatoria(String descricao) {
			Carro carro = criarCarroValido();
			carro.setDescricao(descricao);

			ValidatorResult resultado = validator.validar(carro);

			assertFalse(resultado.isValid());
			assertEquals(Messages.get(MessageKeys.ERRO_DESCRICAO_OBRIGATORIO), resultado.getErros().get(0));
		}

		@ParameterizedTest
		@ValueSource(strings = { "  descrição  ", "descrição  ", "  descrição" })
		@DisplayName("Deve aceitar a descrição com espaços ao redor do texto")
		void deveAceitarDescricaoComEspacos(String descricao) {
			Carro carro = criarCarroValido();
			carro.setDescricao(descricao);

			ValidatorResult resultado = validator.validar(carro);
			
			assertTrue(resultado.isValid());
		}

		@ParameterizedTest
		@NullAndEmptySource
		@ValueSource(strings = { " " })
		@DisplayName("Deve acusar erro quando o tipo for inválido")
		void deveValidarTipoObrigatorio(String tipo) {
			Carro carro = criarCarroValido();
			carro.setTipo(tipo);

			ValidatorResult resultado = validator.validar(carro);

			assertFalse(resultado.isValid());
			assertEquals(Messages.get(MessageKeys.ERRO_TIPO_OBRIGATORIO), resultado.getErros().get(0));
		}
		
		@ParameterizedTest
		@ValueSource(strings = { "  tipo  ", "tipo  ", "  tipo" })
		@DisplayName("Deve aceitar o tipo com espaços ao redor do texto")
		void deveAceitarTipoComEspacos(String tipo) {
			Carro carro = criarCarroValido();
			carro.setTipo(tipo);

			ValidatorResult resultado = validator.validar(carro);

			assertTrue(resultado.isValid());
		}

		@ParameterizedTest
		@NullAndEmptySource
		@ValueSource(strings = { " " })
		@DisplayName("Deve acusar erro quando a URL da Foto for inválida")
		void deveValidarUrlFotoObrigatoria(String urlFoto) {
			Carro carro = criarCarroValido();
			carro.setUrlFoto(urlFoto);

			ValidatorResult resultado = validator.validar(carro);

			assertFalse(resultado.isValid());
			assertEquals(Messages.get(MessageKeys.ERRO_URL_FOTO_OBRIGATORIO), resultado.getErros().get(0));
		}
		
		@ParameterizedTest
		@ValueSource(strings = { "  https://urlFoto.como  ", "https://urlFoto.com  ", "  https://urlFoto.com" })
		@DisplayName("Deve aceitar urlFoto com espaços ao redor do texto")
		void deveAceitarUrlFotoComEspacos(String urlFoto) {
			Carro carro = criarCarroValido();
			carro.setUrlFoto(urlFoto);

			ValidatorResult resultado = validator.validar(carro);

			assertTrue(resultado.isValid());
		}

		@ParameterizedTest
		@NullAndEmptySource
		@ValueSource(strings = { " " })
		@DisplayName("Deve acusar erro quando a URL do Vídeo for inválida")
		void deveValidarUrlVideoObrigatoria(String urlVideo) {
			Carro carro = criarCarroValido();
			carro.setUrlVideo(urlVideo);

			ValidatorResult resultado = validator.validar(carro);

			assertFalse(resultado.isValid());
			assertEquals(Messages.get(MessageKeys.ERRO_URL_VIDEO_OBRIGATORIO), resultado.getErros().get(0));
		}
		
		@ParameterizedTest
		@ValueSource(strings = {  "  https://urlVideo.como  ", "https://urlVideo.com  ", "  https://urlVideo.com"  })
		@DisplayName("Deve aceitar urlVideo com espaços ao redor do texto")
		void deveAceitarUrlVideoComEspacos(String urlVideo) {
			Carro carro = criarCarroValido();
			carro.setUrlVideo(urlVideo);

			ValidatorResult resultado = validator.validar(carro);

			assertTrue(resultado.isValid());
		}

		@ParameterizedTest
		@NullSource
		@DisplayName("Deve acusar erro quando a latitude for inválida")
		void deveValidarLatitudeObrigatoria(Double valor) {
			Carro carro = criarCarroValido();
			carro.setLatitude(valor);

			ValidatorResult resultado = validator.validar(carro);
			assertFalse(resultado.isValid());
			assertEquals(Messages.get(MessageKeys.ERRO_LATITUDE_OBRIGATORIO), resultado.getErros().get(0));
		}

		@ParameterizedTest
		@NullSource
		@DisplayName("Deve acusar erro quando a longitude for inválida")
		void deveValidarLongitudeObrigatoria(Double valor) {
			Carro carro = criarCarroValido();
			carro.setLongitude(valor);

			ValidatorResult resultado = validator.validar(carro);
			assertFalse(resultado.isValid());
			assertEquals(Messages.get(MessageKeys.ERRO_LONGITUDE_OBRIGATORIO), resultado.getErros().get(0));
		}
	}

	@Nested
	@DisplayName("Validações de Limite de Tamanho de Texto (TAMANHO_TEXTO caracteres)")
	class LimiteTextoTest {

		@Test
		@DisplayName("Não deve retornar erro quando os campos de texto estiverem exatamente no limite máximo")
		void naoDeveRetornarErroNoLimiteMaximo() {
			Carro carro = criarCarroValido();
			String textoLimite = repetir('a', ValidationConstants.TAMANHO_TEXTO);

			carro.setNome(textoLimite);
			carro.setDescricao(textoLimite);
			carro.setTipo(textoLimite);
			
			String conteudo = "https://" + repetir('a', ValidationConstants.LIMITE_URL) + ".com.br";
			
			carro.setUrlFoto(conteudo);
			carro.setUrlVideo(conteudo);

			assertTrue(validator.validar(carro).isValid());
		}

		@Test
		@DisplayName("Deve acusar erro quando o nome ultrapassar o limite")
		void deveValidarLimiteNome() {
			Carro carro = criarCarroValido();
			carro.setNome(repetir('a', ValidationConstants.TAMANHO_TEXTO + 1));

			ValidatorResult resultado = validator.validar(carro);
			assertFalse(resultado.isValid());
			assertEquals(Messages.get(MessageKeys.ERRO_NOME_TAMANHO, ValidationConstants.TAMANHO_TEXTO),
					resultado.getErros().get(0));
		}

		@Test
		@DisplayName("Deve acusar erro quando a descrição ultrapassar o limite")
		void deveValidarLimiteDescricao() {
			Carro carro = criarCarroValido();
			carro.setDescricao(repetir('a', ValidationConstants.TAMANHO_TEXTO + 1));

			ValidatorResult resultado = validator.validar(carro);
			assertFalse(resultado.isValid());
			assertEquals(Messages.get(MessageKeys.ERRO_DESCRICAO_TAMANHO, ValidationConstants.TAMANHO_TEXTO),
					resultado.getErros().get(0));
		}

		@Test
		@DisplayName("Deve acusar erro quando o tipo ultrapassar o limite")
		void deveValidarLimiteTipo() {
			Carro carro = criarCarroValido();
			carro.setTipo(repetir('a', ValidationConstants.TAMANHO_TEXTO + 1));

			ValidatorResult resultado = validator.validar(carro);
			assertFalse(resultado.isValid());
			assertEquals(Messages.get(MessageKeys.ERRO_TIPO_TAMANHO, ValidationConstants.TAMANHO_TEXTO),
					resultado.getErros().get(0));
		}

		@Test
		@DisplayName("Deve acusar erro quando a URL da foto ultrapassar o limite")
		void deveValidarLimiteUrlFoto() {
			Carro carro = criarCarroValido();
			
			carro.setUrlFoto("https://" + repetir('a', ValidationConstants.LIMITE_URL + 1) + ".com.br");

			ValidatorResult resultado = validator.validar(carro);
			assertFalse(resultado.isValid());
			assertEquals(Messages.get(MessageKeys.ERRO_URL_FOTO_TAMANHO, ValidationConstants.TAMANHO_TEXTO),
					resultado.getErros().get(0));
		}

		@Test
		@DisplayName("Deve acusar erro quando a URL do vídeo ultrapassar o limite")
		void deveValidarLimiteUrlVideo() {
			Carro carro = criarCarroValido();
			
			carro.setUrlVideo("https://" + repetir('a', ValidationConstants.LIMITE_URL + 1) + ".com.br");

			ValidatorResult resultado = validator.validar(carro);
			assertFalse(resultado.isValid());
			assertEquals(Messages.get(MessageKeys.ERRO_URL_VIDEO_TAMANHO, ValidationConstants.TAMANHO_TEXTO),
					resultado.getErros().get(0));
		}
	}

	@Nested
	@DisplayName("Validações de Coordenadas Geográficas (Latitude e Longitude)")
	class CoordenadasGeograficasTest {

		@ParameterizedTest
		@DisplayName("Deve validar limites permitidos para Latitude (-90 a 90)")
		@CsvSource({ "-90.0, true", "90.0, true", "0.0, true", "-90.1, false", "90.1, false" })
		void deveValidarLimitesLatitude(double lat, boolean valido) {
			Carro carro = criarCarroValido();
			carro.setLatitude(lat);

			ValidatorResult resultado = validator.validar(carro);
			assertEquals(valido, resultado.isValid());

			if (!valido) {
				assertEquals(Messages.get(MessageKeys.ERRO_LATITUDE_INVALIDA), resultado.getErros().get(0));
			}
		}

		@ParameterizedTest
		@DisplayName("Deve validar limites permitidos para Longitude (-180 a 180)")
		@CsvSource({ "-180.0, true", "180.0, true", "0.0, true", "-180.1, false", "180.1, false" })
		void deveValidarLimitesLongitude(double lng, boolean valido) {
			Carro carro = criarCarroValido();
			carro.setLongitude(lng);

			ValidatorResult resultado = validator.validar(carro);
			assertEquals(valido, resultado.isValid());

			if (!valido) {
				assertEquals(Messages.get(MessageKeys.ERRO_LONGITUDE_INVALIDA), resultado.getErros().get(0));
			}
		}
	}
	
	@Nested
	@DisplayName("Validações de URLs")
	class URLsTest {
		
		@ParameterizedTest
		@DisplayName("Deve validar URLs da foto")
		@CsvSource({
				"'https://urlFoto.com', true", 
				"'http://urlFoto.co.uk', true", 
				"'http://urlFoto.com.br', true",
				"'http://urlFoto1234.com.br', true",
				"'http://urlFoto.1234.com.br', true",
				"'https://urlFoto_invalid_url', false",
				"'://urlFoto_invalid_url', false",
				"'urlFoto_invalid_url', false",
				})
		void deveValidarURLFoto(String url, boolean valido) {
			Carro carro = criarCarroValido();
			carro.setUrlFoto(url);

			ValidatorResult resultado = validator.validar(carro);
			assertEquals(valido, resultado.isValid());

			if (!valido) {
				assertEquals(Messages.get(MessageKeys.ERRO_URL_FOTO_INVALIDA), resultado.getErros().get(0));
			}
		}
		
		@ParameterizedTest
		@DisplayName("Deve validar URLs do video")
		@CsvSource({
				"'https://urlVideo.com', true", 
				"'http://urlVideo.co.uk', true", 
				"'http://urlVideo.com.br', true",
				"'http://urlVideo1234.com.br', true",
				"'http://urlVideo.1234.com.br', true",
				"'https://urlVideo_invalid_url', false",
				"'://urlVideo_invalid_url', false",
				"'urlVideo_invalid_url', false",
				})
		void deveValidarURLVideo(String url, boolean valido) {
			Carro carro = criarCarroValido();
			carro.setUrlVideo(url);

			ValidatorResult resultado = validator.validar(carro);
			assertEquals(valido, resultado.isValid());

			if (!valido) {
				assertEquals(Messages.get(MessageKeys.ERRO_URL_VIDEO_INVALIDA), resultado.getErros().get(0));
			}
		}
	} 
}

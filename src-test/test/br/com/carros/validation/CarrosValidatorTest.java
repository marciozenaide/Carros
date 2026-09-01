package test.br.com.carros.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import br.com.carros.i18n.MessageKeys;
import br.com.carros.i18n.Messages;
import br.com.carros.model.Carro;
import br.com.carros.validation.CarroValidator;
import br.com.carros.validation.ValidationConstants;
import br.com.carros.validation.ValidatorResult;

class CarrosValidatorTest {
	
	private final CarroValidator validator = new CarroValidator();

	/*
	 * 1. nome obrigatório 
	 * 2. nome null 
	 * 3. nome com tamanho máximo 
	 * 4. nome acima do limite 
	 * 5. descrição obrigatória 
	 * 6. tipo obrigatório 
	 * 7. URL foto  
	 * 8. URL vídeo ← estamos aqui
	 * 9. latitude 
	 * 10. longitude 
	 * 11. limites das coordenadas
	 */
	
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
	
	@Test
	void deveRetornarErroQuandoNomeNaoForInformado() {

		Carro carro = criarCarroValido();
		
		carro.setNome("");

		ValidatorResult resultado = validator.validar(carro);

		assertFalse(resultado.isValid());
		assertEquals(1, resultado.getErros().size());
		assertEquals(
				Messages.get(
						MessageKeys.ERRO_NOME_OBRIGATORIO), 
						resultado.getErros().get(0)
				);
	}

	@Test
	void deveRetornarErroQuandoNomeForNull() {

		Carro carro = criarCarroValido();
		
		carro.setNome(null);

		ValidatorResult resultado = validator.validar(carro);

		assertFalse(resultado.isValid());
		assertEquals(1, resultado.getErros().size());
		
		assertEquals(
				Messages.get(
						MessageKeys.ERRO_NOME_OBRIGATORIO), 
						resultado.getErros().get(0)
				);
	}

	// nome com tamanho máximo, 255 characteres
	@Test
	void naoDeveRetornarErroQuandoNomeTiverTamanhoMaximo() {

		Carro carro = criarCarroValido();

		carro.setNome(repetir('a',ValidationConstants.TAMANHO_TEXTO));

		ValidatorResult resultado = validator.validar(carro);
		assertTrue(resultado.isValid());
	}

	// nome acima do limite
	@Test
	void deveRetornarErroQuandoNomeUltrapassarTamanhoMaximo() {

	    Carro carro = criarCarroValido();

	    carro.setNome(repetir('a',ValidationConstants.TAMANHO_TEXTO + 1));

	    ValidatorResult resultado = validator.validar(carro);

	    assertFalse(resultado.isValid());
	    assertEquals(1, resultado.getErros().size());

	    assertEquals(
	        Messages.get(
	            MessageKeys.ERRO_NOME_TAMANHO,
	            ValidationConstants.TAMANHO_TEXTO
	        ),
	        resultado.getErros().get(0)
	    );
	}

	@Test
	void deveRetornarErroQuandoOCampoDescricaoNaoForInformado() {

	    Carro carro = criarCarroValido();

	    carro.setDescricao("");

	    ValidatorResult resultado = validator.validar(carro);

	    assertFalse(resultado.isValid());
	    assertEquals(1, resultado.getErros().size());

	    assertEquals(
	        Messages.get(MessageKeys.ERRO_DESCRICAO_OBRIGATORIO),
	        resultado.getErros().get(0)
	    );
	}

	@Test
	void deveRetornarErroQuandoOCampoDescricaoForNull() {

	    Carro carro = criarCarroValido();

	    carro.setDescricao(null);

	    ValidatorResult resultado = validator.validar(carro);

	    assertFalse(resultado.isValid());
	    assertEquals(1, resultado.getErros().size());

	    assertEquals(
	        Messages.get(MessageKeys.ERRO_DESCRICAO_OBRIGATORIO),
	        resultado.getErros().get(0)
	    );
	}

	@Test
	void naoDeveRetornarErroQuandoDescricaoTiverTamanhoMaximo() {

	    Carro carro = criarCarroValido();

	    carro.setDescricao(
	        repetir('a', ValidationConstants.TAMANHO_TEXTO)
	    );

	    ValidatorResult resultado = validator.validar(carro);

	    assertTrue(resultado.isValid());
	}

	@Test
	void deveRetornarErroQuandoDescricaoUltrapassarTamanhoMaximo() {

	    Carro carro = criarCarroValido();

	    carro.setDescricao(
	        repetir('a', ValidationConstants.TAMANHO_TEXTO) + "1"
	    );

	    ValidatorResult resultado = validator.validar(carro);

	    assertFalse(resultado.isValid());
	    assertEquals(1, resultado.getErros().size());

	    assertEquals(
	        Messages.get(
	            MessageKeys.ERRO_DESCRICAO_TAMANHO,
	            ValidationConstants.TAMANHO_TEXTO
	        ),
	        resultado.getErros().get(0)
	    );
	}

	@Test
	void deveRetornarErroQuandoOCampoTipoNaoForInformado() {

		Carro carro = criarCarroValido();
		
		carro.setTipo("");

		ValidatorResult resultado = validator.validar(carro);

		assertFalse(resultado.isValid());
		assertEquals(1, resultado.getErros().size());
		assertEquals(
				Messages.get(
						MessageKeys.ERRO_TIPO_OBRIGATORIO), 
				resultado.getErros().get(0)
				);
	}

	@Test
	void deveRetornarErroQuandoOCampoTipoForNull() {

		Carro carro = criarCarroValido();
		
		carro.setTipo(null);

		ValidatorResult resultado = validator.validar(carro);

		assertFalse(resultado.isValid());
		assertEquals(1, resultado.getErros().size());
		assertEquals(
				Messages.get(
						MessageKeys.ERRO_TIPO_OBRIGATORIO), 
						resultado.getErros().get(0)
					);
	}

	@Test
	void naoDeveRetornarErroQuandoTipoTiverTamanhoMaximo() {

		Carro carro = criarCarroValido();

		carro.setTipo(repetir('a',ValidationConstants.TAMANHO_TEXTO));

		ValidatorResult resultado = validator.validar(carro);
		
		assertTrue(resultado.isValid());
	}

	// Tipo acima do limite
	@Test
	void deveRetornarErroQuandoTipoUltrapassarTamanhoMaximo() {

		Carro carro = criarCarroValido();

		carro.setTipo(repetir('a',ValidationConstants.TAMANHO_TEXTO + 1));

		ValidatorResult resultado = validator.validar(carro);

		assertFalse(resultado.isValid());
		assertEquals(1, resultado.getErros().size());
		assertEquals(
				Messages.get(
						MessageKeys.ERRO_TIPO_TAMANHO, 
						ValidationConstants.TAMANHO_TEXTO), 
						resultado.getErros().get(0)
					);
	}

	@Test
	void deveRetornarErroQuandoOCampoURLFotoNaoForInformado() {

		Carro carro = criarCarroValido();
		
		carro.setUrlFoto("");

		ValidatorResult resultado = validator.validar(carro);

		assertFalse(resultado.isValid());
		assertEquals(1, resultado.getErros().size());
		assertEquals(
				Messages.get(
						MessageKeys.ERRO_URL_FOTO_OBRIGATORIO), 
						resultado.getErros().get(0)
				);
	}

	@Test
	void deveRetornarErroQuandoOCampoURLFotoForNull() {

		Carro carro = criarCarroValido();
		carro.setUrlFoto(null);

		ValidatorResult resultado = validator.validar(carro);

		assertFalse(resultado.isValid());
		assertEquals(1, resultado.getErros().size());
		assertEquals(
				Messages.get(
						MessageKeys.ERRO_URL_FOTO_OBRIGATORIO), 
						resultado.getErros().get(0)
					);
	}

	// Url foto com tamanho máximo, 255 characteres
	@Test
	void naoDeveRetornarErroQuandoUrlFotoTiverTamanhoMaximo() {

		Carro carro = criarCarroValido();

		carro.setUrlFoto(repetir('a',ValidationConstants.TAMANHO_TEXTO));

		ValidatorResult resultado = validator.validar(carro);
		
		assertTrue(resultado.isValid());
	}

	// Url foto acima do limite
	@Test
	void deveRetornarErroQuandoUrlFotoUltrapassarTamanhoMaximo() {

		Carro carro = criarCarroValido();

		carro.setUrlFoto(repetir('a',ValidationConstants.TAMANHO_TEXTO + 1));

		ValidatorResult resultado = validator.validar(carro);

		assertFalse(resultado.isValid());
		assertEquals(1, resultado.getErros().size());
		assertEquals(
				Messages.get(
						MessageKeys.ERRO_URL_FOTO_TAMANHO, 
						ValidationConstants.TAMANHO_TEXTO),
						resultado.getErros().get(0)
				);
	}

	@Test
	void deveRetornarErroQuandoOCampoURLVideoNaoForInformado() {

		Carro carro = criarCarroValido();
		carro.setUrlVideo("");

		ValidatorResult resultado = validator.validar(carro);

		assertFalse(resultado.isValid());
		assertEquals(1, resultado.getErros().size());
		assertEquals(
				Messages.get(
						MessageKeys.ERRO_URL_VIDEO_OBRIGATORIO), 
						resultado.getErros().get(0)
					);
	}

	@Test
	void deveRetornarErroQuandoOCampoURLVideoNaoForNull() {

		Carro carro = criarCarroValido();
		carro.setUrlVideo(null);

		ValidatorResult resultado = validator.validar(carro);

		assertFalse(resultado.isValid());
		assertEquals(1, resultado.getErros().size());
		assertEquals(
				Messages.get(
					MessageKeys.ERRO_URL_VIDEO_OBRIGATORIO), 
					resultado.getErros().get(0)
				);
	}

	// Url video com tamanho máximo, 255 characteres
	@Test
	void naoDeveRetornarErroQuandoUrlVideoTiverTamanhoMaximo() {

		Carro carro = criarCarroValido();

		carro.setUrlVideo(repetir('a',ValidationConstants.TAMANHO_TEXTO));

		ValidatorResult resultado = validator.validar(carro);
		assertTrue(resultado.isValid());
	}

	// Url video acima do limite
	@Test
	void deveRetornarErroQuandoUrlVideoUltrapassarTamanhoMaximo() {

		Carro carro = criarCarroValido();

		carro.setUrlVideo(repetir('a',ValidationConstants.TAMANHO_TEXTO + 1));

		ValidatorResult resultado = validator.validar(carro);

		assertFalse(resultado.isValid());
		assertEquals(1, resultado.getErros().size());
		assertEquals(
				Messages.get(
						MessageKeys.ERRO_URL_VIDEO_TAMANHO, 
						ValidationConstants.TAMANHO_TEXTO),
					resultado.getErros().get(0)
				);
	}
	
	// Latitude inválida
	@Test
	void deveRetornarErroQuandoLatitudeEstiverAcimaDoLimiteMaximo() {
		
		Carro carro = criarCarroValido();
		
		carro.setLatitude(ValidationConstants.LIMITE_LATITUDE + 1);

		ValidatorResult resultado = validator.validar(carro);
		
		assertFalse(resultado.isValid());
		assertEquals(1, resultado.getErros().size());
		assertEquals(
				Messages.get(
						MessageKeys.ERRO_LATITUDE_INVALIDA, 
						ValidationConstants.LIMITE_LATITUDE),
					resultado.getErros().get(0)
				);
	}
	
	// Latitude inválida
	@Test
	void deveRetornarErroQuandoLatitudeEstiverAbaixoDoLimiteMinimo() {
		
		Carro carro = criarCarroValido();
		
		carro.setLatitude(- (ValidationConstants.LIMITE_LATITUDE + 1));

		ValidatorResult resultado = validator.validar(carro);
		
		assertFalse(resultado.isValid());
		assertEquals(1, resultado.getErros().size());
		assertEquals(
				Messages.get(
						MessageKeys.ERRO_LATITUDE_INVALIDA, 
						ValidationConstants.LIMITE_LATITUDE),
				resultado.getErros().get(0)
				);
	}

	// Longitude inválida
		@Test
		void deveRetornarErroQuandoLongitudeEstiverAcimaDoLimiteMaximo() {
			
			Carro carro = criarCarroValido();
			
			carro.setLongitude(ValidationConstants.LIMITE_LONGITUDE + 1);

			ValidatorResult resultado = validator.validar(carro);
			
			assertFalse(resultado.isValid());
			assertEquals(1, resultado.getErros().size());
			assertEquals(
					Messages.get(
							MessageKeys.ERRO_LONGITUDE_INVALIDA, 
							ValidationConstants.LIMITE_LONGITUDE),
							resultado.getErros().get(0)
					);
		}
		
		// Longitude inválida
		@Test
		void deveRetornarErroQuandoLongitudeEstiverAbaixoDoLimiteMinimo() {
			
			Carro carro = criarCarroValido();
			
			carro.setLongitude(- (ValidationConstants.LIMITE_LONGITUDE + 1));

			ValidatorResult resultado = validator.validar(carro);
			
			assertFalse(resultado.isValid());
			assertEquals(1, resultado.getErros().size());
			assertEquals(
					Messages.get(
							MessageKeys.ERRO_LONGITUDE_INVALIDA, 
							ValidationConstants.LIMITE_LONGITUDE),
					resultado.getErros().get(0)
					);
		}
}

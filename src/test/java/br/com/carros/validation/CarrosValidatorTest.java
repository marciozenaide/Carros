package br.com.carros.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

import br.com.carros.i18n.MessageKeys;
import br.com.carros.i18n.Messages;
import br.com.carros.model.Carro;
import br.com.carros.validation.CarroValidator;
import br.com.carros.validation.ValidationConstants;
import br.com.carros.validation.ValidatorResult;

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

@DisplayName("Testes de Validação do Modelo Carro")
class CarrosValidatorTest {
	
	private final CarroValidator validator = new CarroValidator();
	
	// Helper para gerar strings longas
	private String repetir(char caractere, int quantidade) {
	    StringBuilder texto = new StringBuilder(quantidade);

	    for (int i = 0; i < quantidade; i++) {
	        texto.append(caractere);
	    }

	    return texto.toString();
	}
	
	// Factory de objeto válido para isolamento dos testes
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
		
		@ParameterizedTest
        @NullAndEmptySource
        @DisplayName("Deve acusar erro quando o nome for inválido")
        void deveValidarNomeObrigatorio(String nome) {
            Carro carro = criarCarroValido();
            carro.setNome(nome);
            
            ValidatorResult resultado = validator.validar(carro);
            
            assertFalse(resultado.isValid());
            assertEquals(Messages.get(MessageKeys.ERRO_NOME_OBRIGATORIO), resultado.getErros().get(0));
        }
		
		@ParameterizedTest
        @NullAndEmptySource
        @DisplayName("Deve acusar erro quando a descrição for inválida")
        void deveValidarDescricaoObrigatoria(String descricao) {
            Carro carro = criarCarroValido();
            carro.setDescricao(descricao);
            
            ValidatorResult resultado = validator.validar(carro);
            
            assertFalse(resultado.isValid());
            assertEquals(Messages.get(MessageKeys.ERRO_DESCRICAO_OBRIGATORIO), resultado.getErros().get(0));
        }
		
		@ParameterizedTest
        @NullAndEmptySource
        @DisplayName("Deve acusar erro quando o tipo for inválido")
        void deveValidarTipoObrigatorio(String tipo) {
            Carro carro = criarCarroValido();
            carro.setTipo(tipo);
            
            ValidatorResult resultado = validator.validar(carro);
            
            assertFalse(resultado.isValid());
            assertEquals(Messages.get(MessageKeys.ERRO_TIPO_OBRIGATORIO), resultado.getErros().get(0));
        }
		
		@ParameterizedTest
        @NullAndEmptySource
        @DisplayName("Deve acusar erro quando a URL da Foto for inválida")
        void deveValidarUrlFotoObrigatoria(String urlFoto) {
            Carro carro = criarCarroValido();
            carro.setUrlFoto(urlFoto);
            
            ValidatorResult resultado = validator.validar(carro);
            
            assertFalse(resultado.isValid());
            assertEquals(Messages.get(MessageKeys.ERRO_URL_FOTO_OBRIGATORIO), resultado.getErros().get(0));
        }
		
		@ParameterizedTest
        @NullAndEmptySource
        @DisplayName("Deve acusar erro quando a URL do Vídeo for inválida")
        void deveValidarUrlVideoObrigatoria(String urlVideo) {
            Carro carro = criarCarroValido();
            carro.setUrlVideo(urlVideo); // ← Finalização do seu cenário 8
            
            ValidatorResult resultado = validator.validar(carro);
            
            assertFalse(resultado.isValid());
            assertEquals(Messages.get(MessageKeys.ERRO_URL_VIDEO_OBRIGATORIO), resultado.getErros().get(0));
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
            carro.setUrlFoto(textoLimite);
            carro.setUrlVideo(textoLimite);

            assertTrue(validator.validar(carro).isValid());
        }

        @Test
        @DisplayName("Deve acusar erro quando o nome ultrapassar o limite")
        void deveValidarLimiteNome() {
            Carro carro = criarCarroValido();
            carro.setNome(repetir('a', ValidationConstants.TAMANHO_TEXTO + 1));
            
            ValidatorResult resultado = validator.validar(carro);
            assertFalse(resultado.isValid());
            assertEquals(Messages.get(MessageKeys.ERRO_NOME_TAMANHO, ValidationConstants.TAMANHO_TEXTO), resultado.getErros().get(0));
        }

        @Test
        @DisplayName("Deve acusar erro quando a descrição ultrapassar o limite")
        void deveValidarLimiteDescricao() {
            Carro carro = criarCarroValido();
            carro.setDescricao(repetir('a', ValidationConstants.TAMANHO_TEXTO + 1));
            
            ValidatorResult resultado = validator.validar(carro);
            assertFalse(resultado.isValid());
            assertEquals(Messages.get(MessageKeys.ERRO_DESCRICAO_TAMANHO, ValidationConstants.TAMANHO_TEXTO), resultado.getErros().get(0));
        }
    }
	
	@Nested
    @DisplayName("Validações de Coordenadas Geográficas (Latitude e Longitude)")
    class CoordenadasGeograficasTest {

        @ParameterizedTest
        @DisplayName("Deve validar limites permitidos para Latitude (-90 a 90)")
        @CsvSource({
            "-90.0, true",
            "90.0, true",
            "0.0, true",
            "-90.1, false",
            "90.1, false"
        })
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
        @CsvSource({
            "-180.0, true",
            "180.0, true",
            "0.0, true",
            "-180.1, false",
            "180.1, false"
        })
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


}

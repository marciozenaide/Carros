package br.com.carros.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.carros.util.ConnectionFactory;

public final class DatabaseTestConfig {

	private DatabaseTestConfig() {

	}

	public static void initializeDatabase() throws SQLException, IOException {
		executarScript(true);
	}

	public static void resetDatabase() throws SQLException, IOException {
		executarScript(false);
	}

	private static void executarScript(boolean criarTabela) throws SQLException, IOException {
		// Lê schema.sql
		try (Connection connection = ConnectionFactory.getConnection();
				InputStream input = DatabaseTestConfig.class.getClassLoader().getResourceAsStream("schema.sql")) {

			if (input == null) {
				throw new IllegalStateException("schema.sql não encontrado nos recursos de teste.");
			}

			try (BufferedReader reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
					Statement stmt = connection.createStatement()) {

				StringBuilder sql = new StringBuilder();
				String linha;
				while ((linha = reader.readLine()) != null) {
					sql.append(linha).append("\n");
				}

				// Executa cada comando SQL individualmente
				String[] comandos = sql.toString().split(";");

				for (String comando : comandos) {
					String comandoSql = comando.trim();
					if (comandoSql.isEmpty()) {
						continue;
					}
					if (criarTabela) {
						stmt.execute(comandoSql);
					} else if (!comandoSql.startsWith("CREATE")) {
						stmt.execute(comandoSql);
					}
				}
			}
		}
	}
}

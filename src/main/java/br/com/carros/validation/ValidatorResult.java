package br.com.carros.validation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ValidatorResult {

	private final List<String> erros = new ArrayList<>();

	public void addErro(String erro) {
		this.erros.add(erro);
	}

	public boolean isValid() {
		return this.erros.isEmpty();
	}

	public List<String> getErros() {
		return Collections.unmodifiableList(erros);
	}

}

package br.com.rjconsultores.tests.seleniuminstance.enums;

public enum SourceEvent {
	SYSTEM("Sistema"),
	SCREEN("Tela"),
	COMPONENT("Componente"),
	EVENT("Evento"),
	RULE("Regra"),
	ATTRIBUTE("Atributo"),
	REGISTER_SERVICE("Cadastro"),
	REGISTER_FACTORY("Register Factory");
	
	private String description;
	
	private SourceEvent(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
}

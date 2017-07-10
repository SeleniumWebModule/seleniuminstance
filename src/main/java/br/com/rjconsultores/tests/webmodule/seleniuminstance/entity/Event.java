package br.com.rjconsultores.tests.webmodule.seleniuminstance.entity;

import java.util.Collection;
import java.util.LinkedHashSet;

public class Event {
	private String name;
	private String descricao;

	private Collection<Rule> rules; 

	public Event() {
		rules = new LinkedHashSet<>();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void registerRule(Rule rule) {
		rules.add(rule);
	}

	public Collection<Rule> listRules() {
		return rules;
	}
}

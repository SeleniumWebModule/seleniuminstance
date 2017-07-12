package br.com.rjconsultores.tests.webmodule.seleniuminstance.entity;

import java.util.Collection;
import java.util.LinkedHashSet;

import br.com.rjconsultores.tests.seleniuminstance.enums.SourceEvent;
import br.com.rjconsultores.tests.seleniuminstance.exception.ResourceRequiredException;
import br.com.rjconsultores.tests.seleniuminstance.exception.SeleniumInstanceException;

public class Event implements Entity{
	private String name;
	private String description;

	private Collection<Rule> rules;
	
	private SourceEvent sourceEvent;

	public Event() {
		rules = new LinkedHashSet<>();
		sourceEvent = SourceEvent.EVENT;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public void registerRule(Rule rule) {
		rules.add(rule);
	}

	public Collection<Rule> listRules() {
		return rules;
	}

	@Override
	public void validate() throws SeleniumInstanceException {
		if (listRules() == null || listRules().isEmpty()) {
			throw new ResourceRequiredException(sourceEvent, "No mínimo uma regra deve estar associada ao evento.");
		}
	}
}

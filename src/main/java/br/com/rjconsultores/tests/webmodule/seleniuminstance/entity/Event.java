package br.com.rjconsultores.tests.webmodule.seleniuminstance.entity;

import java.util.Collection;
import java.util.LinkedHashSet;

import br.com.rjconsultores.tests.seleniuminstance.enums.SourceEvent;
import br.com.rjconsultores.tests.seleniuminstance.exception.RequiredResourceException;
import br.com.rjconsultores.tests.seleniuminstance.exception.SeleniumInstanceException;
import br.com.rjconsultores.tests.seleniuminstance.util.ValidateUtil;

public class Event implements Entity{
	private String name;
	private String description;
	
	private String msgErrorRuleRequired;
	private String msgErrorRuleNullValue;
	
	private Collection<Rule> rules;
	
	private SourceEvent sourceEvent;
	
	private final int SIZE_FIELD_NAME = 30;
	private final int SIZE_FIELD_DESCRIPTION = 400;

	public Event() {
		rules = new LinkedHashSet<>();
		sourceEvent = SourceEvent.EVENT;
		
		msgErrorRuleRequired = "Uma ou mais regras devem estar associadas ao evento.";
		msgErrorRuleNullValue = "A regra deve ser um valor válido e nulo não é um valor válido.";
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
		ValidateUtil.validateField(sourceEvent, "name", getName(), SIZE_FIELD_NAME);
		ValidateUtil.validateField(sourceEvent, "description", getDescription(), SIZE_FIELD_DESCRIPTION);
		
		if (listRules() == null || listRules().isEmpty()) {
			throw new RequiredResourceException(sourceEvent, msgErrorRuleRequired);
		}
		
		for (Rule rule: listRules()) {
			if (rule == null) {
				throw new RequiredResourceException(sourceEvent, msgErrorRuleNullValue);
			}
			
			rule.validate();
		}
	}
}

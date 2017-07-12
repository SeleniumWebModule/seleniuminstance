package br.com.rjconsultores.tests.webmodule.seleniuminstance.entity;

import java.util.Collection;
import java.util.LinkedHashSet;

import br.com.rjconsultores.tests.seleniuminstance.enums.SourceEvent;
import br.com.rjconsultores.tests.seleniuminstance.exception.RequiredResourceException;
import br.com.rjconsultores.tests.seleniuminstance.exception.SeleniumInstanceException;
import br.com.rjconsultores.tests.seleniuminstance.util.ValidateUtil;

public class Rule implements Entity{
	private String name;
	private String description;
	private String msgErrorAttributeRequireError;
	private String msgErrorAttributeNullValue;
	
	private Collection<Attribute> attributes;
	
	private SourceEvent sourceEvent;
	
	public Rule() {
		attributes = new LinkedHashSet<>();
		sourceEvent = SourceEvent.RULE;
		
		msgErrorAttributeRequireError = "Um ou mais atributos deve estar associados � regra.";
		msgErrorAttributeNullValue = "O atributo deve ser um valor v�lido e nulo n�o � um valor v�lido.";
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
	
	public void registerAttribute(Attribute attribute) {
		attributes.add(attribute);
	}
	
	public Collection<Attribute> listAttributes() {
		return attributes;
	}

	@Override
	public void validate() throws SeleniumInstanceException {
		ValidateUtil.validateField(sourceEvent, "name", getName(), 30);
		ValidateUtil.validateField(sourceEvent, "description", getDescription(), 400);

		if (listAttributes() == null || listAttributes().isEmpty()) {
			throw new RequiredResourceException(sourceEvent, msgErrorAttributeRequireError);
		}
		
		for (Attribute attribute: listAttributes()) {
			if (attribute == null) {
				throw new RequiredResourceException(sourceEvent, msgErrorAttributeNullValue);
			}
			
			attribute.validate();
		}
	}
}

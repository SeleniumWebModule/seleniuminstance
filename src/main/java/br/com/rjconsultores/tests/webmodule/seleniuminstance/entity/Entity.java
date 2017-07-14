package br.com.rjconsultores.tests.webmodule.seleniuminstance.entity;

import java.util.Collection;

import br.com.rjconsultores.tests.seleniuminstance.exception.SeleniumInstanceException;
import br.com.rjconsultores.tests.seleniuminstance.util.GenerateUtil;

public interface Entity {
	public void validate() throws SeleniumInstanceException;
	
	public void setId(String id);
	
	public String getId();
	
	public void setParentId(String id);
	
	public void generateIdsForChildrens();
	
	default void generateIdForEntity(Collection<Entity> entities) {
		for (Entity entity: entities) {			
			entity.setId(GenerateUtil.getIDForNewObject());
			entity.setParentId(getId());
		}
	}
}
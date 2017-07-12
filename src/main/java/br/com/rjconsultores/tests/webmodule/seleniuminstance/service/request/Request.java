package br.com.rjconsultores.tests.webmodule.seleniuminstance.service.request;

import br.com.rjconsultores.tests.seleniuminstance.enums.OperationType;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.entity.System;

public class Request {
	private System system;
	private OperationType operationType;
	
	public System getSystem() {
		return system;
	}
	
	public void setSystem(System system) {
		this.system = system;
	}
	
	public OperationType getOperationType() {
		return operationType;
	}
	
	public void setOperationType(OperationType operationType) {
		this.operationType = operationType;
	}
}

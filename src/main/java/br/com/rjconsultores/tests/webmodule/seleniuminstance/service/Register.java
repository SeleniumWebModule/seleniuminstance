package br.com.rjconsultores.tests.webmodule.seleniuminstance.service;

import javax.inject.Inject;

import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.factory.RegisterFactory;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.service.request.Request;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.service.response.Response;

@Controller
@Path("register")
public class Register {

	@Inject
	private Result result;
	
	@Post("screen")
	@Consumes("application/json")
	public Response doRegisterSystem(Request request) {
		Response response = RegisterFactory.INSTANCE().registerScreen(request);
		
		//para uso nos casos de teste
		//TODO - RESOLVER O USO DE INJECT COM TESTES UNITÁRIOS
		if (result == null) {
			return response;
		}
		
		result.use(Results.json()).withoutRoot().from(response).serialize();
		
		return response;
	}
}

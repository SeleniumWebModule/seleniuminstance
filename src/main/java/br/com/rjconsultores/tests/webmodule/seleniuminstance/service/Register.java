package br.com.rjconsultores.tests.webmodule.seleniuminstance.service;

import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.serialization.gson.WithoutRoot;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.entity.Screen;
import br.com.rjconsultores.tests.webmodule.seleniuminstance.factory.RegisterFactory;

@Controller
@Path("/register")
public class Register {

	@Consumes(value = "application/json", options = WithoutRoot.class)
	@Path("system")
	@Post
	public void doRegisterSystem(System system) {
		RegisterFactory.INSTANCE().registerSystem(system);	
	}
	
	@Consumes(value = "application/json", options = WithoutRoot.class)
	@Path("screen")
	@Post
	public void doRegisterScreen(Screen screen) {
		RegisterFactory.INSTANCE().registerScreen(screen);	
	}
}

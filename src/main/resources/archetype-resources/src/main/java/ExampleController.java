package ${package};

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleController {

	@RequestMapping(value = "/")
	public String home() {
		return "Request sent: " + new ExampleProducer().sendMessage();
	}

}

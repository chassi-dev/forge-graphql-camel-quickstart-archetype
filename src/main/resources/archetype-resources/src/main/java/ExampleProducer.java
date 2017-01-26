package ${package};

import org.apache.camel.CamelContext;
import org.apache.camel.CamelContextAware;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class ExampleProducer implements CamelContextAware {

	static CamelContext camelContext;

	@Override
	public CamelContext getCamelContext(){ return camelContext; }

	@Override
	public void setCamelContext(CamelContext injectedCamelContext){
		camelContext = injectedCamelContext;
	}

	public HashMap<String, String> sendMessage(){
		HashMap<String, String> message = new HashMap<>();
		message.put("hello", "from the producer");
		this.camelContext.createProducerTemplate().sendBody("direct:producer", message);
		return message;
	}

}

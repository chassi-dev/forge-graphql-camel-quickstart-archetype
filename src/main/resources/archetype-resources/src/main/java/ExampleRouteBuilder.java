package ${package};

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

@Component
public class ExampleRouteBuilder extends RouteBuilder {

	private final boolean usePubSub = false;

	@Override
	public void configure() throws Exception {

		// this will not work if you are not integrated with activeMQ
		if(this.usePubSub){

			this.buildPubSubProducerRoute();

		} else {

			this.buildLocalProducerRoute();

		}

	}


	private void buildPubSubProducerRoute(){

		// a simple example to read from a direct starting point and produce to the queue
		from("direct:producer")
						.to("log:producer")
						.to("amq:example");
	}


	private void buildLocalProducerRoute(){
		from("direct:producer")

						// example: turn our hashmap body into json with marshaling
						.marshal().json(JsonLibrary.Gson)

						.to("log:out")

						// send to mock consumer
						.to("mock:consumer");
	}
}

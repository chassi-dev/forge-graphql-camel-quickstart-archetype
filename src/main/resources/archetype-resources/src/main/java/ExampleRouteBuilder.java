package ${package};

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;

import java.util.HashMap;

public class ExampleRouteBuilder extends RouteBuilder {

	private final boolean usePubSub;

	private ExampleRouteBuilder(){
		this.usePubSub = false;
	}

	public ExampleRouteBuilder(boolean usePubSub){
		this.usePubSub = usePubSub;
	}

	@Override
	public void configure() throws Exception {

		// this will not work if you are not integrated with activeMQ
		if(this.usePubSub){

			this.buildPubSubConsumerRoute();
			this.buildPubSubProducerRoute();

		} else {

			this.buildLocalConsumerRoute();
			this.buildLocalProducerRoute();

		}

		// start a thread to produce
		new Thread(() -> {
			try {
				while(true){
					Thread.sleep(2500);
					getContext().createProducerTemplate().sendBody("direct:producer", "hello from producer");
				}
			} catch (InterruptedException e){
				e.printStackTrace();
			}
		}).start();
	}


	private void buildPubSubConsumerRoute(){

		// a simple example to consume from the queue given a certain channel
		from("amq:example").to("log:consumer");
	}

	private void buildPubSubProducerRoute(){

		// a simple example to read from a direct starting point and produce to the queue
		from("direct:producer")
						.to("log:producer")
						.to("amq:example");
	}

	private void buildLocalConsumerRoute(){
		from("direct:consumer")

						// example: unmarshal our json into a hashmap
						.unmarshal().json(JsonLibrary.Gson, java.util.HashMap.class)
						.to("log:consumer");
	}

	private void buildLocalProducerRoute(){
		from("direct:producer")

						// example: turn our input string into a hashmap with a processor
						.process(new Processor() {
							@Override
							public void process(Exchange exchange) throws Exception {
								Message in = exchange.getIn();
								HashMap<String, String> message = new HashMap<>();
								message.put("message", (String)in.getBody());
								Message out = exchange.getOut();
								out.setBody(message);
							}
						})
						.to("log:asHashmap")

						// example: turn our hashmap body into json with marshaling
						.marshal().json(JsonLibrary.Gson)
						.to("log:asJson")

						// send to consumer endpoint
						.to("direct:consumer");
	}
}

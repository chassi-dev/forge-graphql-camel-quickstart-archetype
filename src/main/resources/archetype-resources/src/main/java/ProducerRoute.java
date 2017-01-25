package com.saasforge;

import org.apache.camel.builder.RouteBuilder;

public class ProducerRoute extends RouteBuilder {

	private final boolean usePubSub;

	private ProducerRoute(){
		this.usePubSub = false;
	}

	public ProducerRoute(boolean usePubSub){
		this.usePubSub = usePubSub;
	}

	@Override
	public void configure() throws Exception {
		if(this.usePubSub){
			this.buildPubSubRoute();
		} else {
			this.buildLocalRoute();
		}
	}


	private void buildPubSubRoute(){
		from("direct:start")
			.wiretap("log:out")
			.to("amq:example")
	}

	private void buildLocalRoute(){
		from("direct:start")
			.wiretap("log:out")
			.to("mock:consumer")
	}

}
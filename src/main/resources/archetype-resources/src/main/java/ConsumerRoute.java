package com.saasforge;

import org.apache.camel.builder.RouteBuilder;

public class ConsumerRoute extends RouteBuilder {

	private final boolean usePubSub;

	private ConsumerRoute(){
		this.usePubSub = false;
	}

	public ConsumerRoute(boolean usePubSub){
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
		from("amq:example").to("log:consumer")
	}

	private void buildLocalRoute(){
		from("mock:consumer").to("log:consumer")
	}

}
package com.saasforge;

import org.apache.camel.main.Main;

/**
 * Hello world!
 *
 */
public class App{
    public static void main(String... args){

        System.out.println("************************************");
        System.out.println("* Press ctrl+c to stop the example *");
        System.out.println("************************************");

        Main main = new Main();

        ConsumerRoute consumerRoute = new ConsumerRoute();
        ProducerRoute producerRoute = new ProducerRoute();

        main.addRouteBuilder(consumerRoute);
        main.addRouteBuilder(producerRoute);

        main.run();


    }
}

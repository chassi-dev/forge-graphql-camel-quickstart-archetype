package ${package};

import org.apache.camel.main.Main;

/**
 * Hello world!
 *
 */
public class App{
    public static void main(String... args) throws Exception {

        System.out.println("************************************");
        System.out.println("* Press ctrl+c to stop the example *");
        System.out.println("************************************");

        Main main = new Main();

        main.addRouteBuilder(new ExampleRouteBuilder(false));

        main.run();


    }
}

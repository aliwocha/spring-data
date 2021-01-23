package pl.javastart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import pl.javastart.controllers.RentalShopController;

@SpringBootApplication
public class DeviceRentalShopApp {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx =
                SpringApplication.run(DeviceRentalShopApp.class, args);

        RentalShopController controller = ctx.getBean(RentalShopController.class);
        controller.mainLoop();
    }
}

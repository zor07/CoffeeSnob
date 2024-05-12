package com.zor07.coffeesnob;

import com.zor07.coffeesnob.entity.Product;
import com.zor07.coffeesnob.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CoffeeSnobApplication implements CommandLineRunner {

    @Autowired
    private ProductService productService;

    public static void main(String[] args) {
        SpringApplication.run(CoffeeSnobApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Product toSave = new Product();
        toSave.setName("test");
        toSave.setDescription("test description");
        productService.save(toSave);

        Product toUpdate = productService.findAll().get(0);
        System.out.println(toUpdate); // Проверим, что продукт сохранился
        toUpdate.setName("test2");
        toUpdate.setDescription("test description2");
        productService.save(toUpdate);

        Product byId = productService.findById(toUpdate.getId());
        System.out.println(byId); // Проверим метод findById ну и что продукт обновился

        productService.delete(byId);
        System.out.println(productService.findAll().size()); // проверим что продукт удалился
    }
}

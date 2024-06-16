package com.zor07.coffeesnob;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


class CoffeeSnobApplicationTests {

    @Test
    void contextLoads() {
        System.out.println(
                new BCryptPasswordEncoder().encode("admin")
        );

        System.out.println(
                new BCryptPasswordEncoder().encode("demo")
        );
    }

}

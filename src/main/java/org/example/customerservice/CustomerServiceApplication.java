package org.example.customerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

}

/*    TODO UPPGIFT

Kolla på att skriva bättre felmeddelande (t.ex, om kunden inte finns när man bokar. Just nu krashar sidan!).
--- funkar just nu på login.
--- fixat i edit, provkör gärna och kolla om ni hittar någon bugg. statuskoder!!!!!!

Unik mail?

*/


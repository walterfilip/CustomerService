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



flytta allt från CustomerService På hotelsidan ---------- fixat
----Flytta bort hjälpfuntion från CustomerController i Hotelbooking ---------fixat!

Kolla på att skriva bättre felmeddelande (t.ex, om kunden inte finns när man bokar. Just nu krashar sidan!).
--- funkar just nu på login.
--- fixat i edit, provkör gärna och kolla om ni hittar någon bugg.

radera konto ifall man inte har någon aktiv bokning. då krashar den om CS ligger nere.
ändra uppgifter krashar också

Se till att API anropen ska ha rätt status meddelande. (response entities?).
-- finns just nu i login.

Steg 8 – Felhantering mellan tjänsterna
Stäng av kundtjänsten och försök skapa en bokning – vad händer? -- tänker att detta funkar nu
Tjänsten ska inte krascha! den ska ge ett tydligt felmeddelande -- det gör den nu

Kolla över DTOS (delete det som inte behövs), se till så Customerservice inte returnerar lösenord till Hotellbooking

2 Dockerfiler, en för varje app. 1 docker compose fil för allting sammansatt.

Fundera på ytterligare microservice tjänst för vg (recensioner t.ex).
--- eventuellt lägga in en knapp direkt i min sida?




FRÅGA OM GITHUB
DTO

*/


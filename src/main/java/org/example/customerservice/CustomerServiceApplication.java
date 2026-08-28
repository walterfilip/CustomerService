package org.example.customerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CustomerServiceApplication {

  public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }



/*    TODO UPPGIFT



flytta allt från CustomerService På hotelsidan

Kolla på att skriva bättre felmeddelande (t.ex, om kunden inte finns när man bokar. Just nu krashar sidan!).
Se till att API anropen ska ha rätt status meddelande. (response entities?).
Kolla på lösningar för ActiveCustomer problemet.(linda?)


Steg 8 – Felhantering mellan tjänsterna
Stäng av kundtjänsten och försök skapa en bokning – vad händer?
Tjänsten ska inte krascha! den ska ge ett tydligt felmeddelande

Kolla på att ta bort Customer objekt/modellen från HotelBooking och istället bara utgå från customer ID utifrån booking tabellen.
Kolla på hur lösenordet ska hanteras mellan HotelBooking och CustomerService, kan man skapa en separat request för att inte skicka lösen.

2 Dockerfiler, en för varje app. 1 docker compose fil för allting sammansatt.
Fundera på ytterligare microservice tjänst för vg (recensioner t.ex).



FRÅGA OM GITHUB
DTO

*/
}

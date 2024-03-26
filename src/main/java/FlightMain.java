import exception.ValidationException;
import model.Flight;
import model.Ticket;
import model.User;
import repository.DBimplementations.FlightRepository;
import repository.DBimplementations.TicketRepository;
import repository.DBimplementations.UserRepository;
import repository.JdbcUtils;
import uitls.PasswordEncoder;


import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.util.Arrays;
import java.util.Properties;

/***
 * Problema 1.
 * Mai multe agenții de turism folosesc o companie de zbor pentru a transporta clienții la diferite destinații
 * turistice. Agențiile folosesc un sistem soft pentru a cumpăra bilete pentru turiști.
 * Angajații de la agențiile de turism folosesc o aplicație desktop cu următoarele funcționalități:
 *
 * 1. Login. După autentificarea cu success, o nouă fereastră se deschide în care sunt afișate informațiile
 * despre zboruri (destinația, data și ora plecării, aeroportul și numărul de locul disponbile).
 *
 * 2. Căutare. După autentificarea cu succes, angajatul poate căuta un zbor introducând destinația și data
 * plecării. Aplicația va afișa în altă lista/alt tablou/etc. toate zborurile pentru aceea destinație, ora plecării
 * și numărul de locuri disponibile.
 *
 * 3. Cumpărare. Angajatul poate cumpara bilete pentru clienți pentru o anumită destinație, la o anumită dată,
 * respectiv oră de plecare. La cumpărarea unui bilet, angajatul introduce numele clientului, numele
 * turiștilor, adresa clientului și numărul de locuri. După cumpărarea unui bilet, toți angajații de la agențiile
 * de turism văd lista actualizată a zborurilor și numărul de locuri disponibile. Dacă pentru un anumit zbor
 * nu mai sunt locuri disponbile, acel zbor nu va mai apărea în lista zborurilor afișate pe interfață.
 *
 * 4. Logout.
 */

public class FlightMain {
    public static void main(String[] args)  {
        System.out.println("Hello and welcome!");
        Properties properties = new Properties();

        try {
            properties.load(new FileReader("bd.config"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        JdbcUtils jdbcUtils = new JdbcUtils(properties);
//        Connection con = jdbcUtils.getConnection();
        UserRepository userRepository = new UserRepository(properties);
        System.out.println(userRepository.findByUsernameAndPassword("mihneas","1234"));
        User user = new User("mihneas");
        user.setPassword("1234");
//        try{
//            System.out.println(userRepository.save(user));
//        }catch (ValidationException e){
//            System.out.println(e);
//        }
//        FlightRepository flightRepository = new FlightRepository(properties);
//        System.out.println(flightRepository.findByDestinationAndDate("Bucuresti", Date.valueOf("2025-05-17").toLocalDate()));
//        System.out.println(flightRepository.findAllAvailable());
//
//        Flight flight =  new Flight("fdgfdg",null,null,null,6);
//        flight.setId(1);
//        TicketRepository ticketRepository = new TicketRepository(properties);
//        Ticket ticket = new Ticket("darius", Arrays.asList("elena","gheorghe","badea"),"Baia Mare",4,flight);
//        try {
//            ticketRepository.save(ticket);
//        } catch (ValidationException e) {
//            System.out.println(e);
//

    }
}
package controller;

import exception.Message;
import exception.ServiceException;
import exception.ValidationException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Flight;
import model.Ticket;
import service.FlightService;
import service.TicketService;
import uitls.factory.Container;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class MainController implements Initializable {
    public TableColumn tableColumnSearchDestination;
    public TableColumn tableColumnSearchDepartureDate;
    public TableColumn tableColumnSearchDepartureTime;
    public TableColumn tableColumnSearchAirport;
    public TableColumn tableColumnSearchSeatsAvailable;
    public TextField textFieldClientName;
    public TextField textFieldTouristsNames;
    public TextField textFieldClientAddress;
    public Button buttonBuyTicket;
    public Spinner spinnerNoOfSeats;

    private Stage stage;
    private Container container;
    private FlightService flightService;
    private TicketService ticketService;

    @FXML
    public TableView tableViewSearchFlights;
    @FXML
    public TextField textFieldDestination;
    @FXML
    public DatePicker datePickerDepartureDate;
    @FXML

    public Button buttonSearch;
    @FXML
    public TableView tableViewAllFlights;
    @FXML

    public TableColumn tableColumnAllDestination;
    @FXML

    public TableColumn tableColumnAllDepartureDate;
    @FXML

    public TableColumn tableColumnAllDepartureTime;
    @FXML

    public TableColumn tableColumnAllAirport;
    @FXML

    public TableColumn tableColumnAllSeatsAvailable;

    private ObservableList<Flight> dataAllFlights = FXCollections.observableArrayList();
    private ObservableList<Flight> dataSearchFlights = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void initializeTableViewAllFlights(){
        tableColumnAllDestination.setCellValueFactory(new PropertyValueFactory<Flight,String>("destination"));
        tableColumnAllDepartureDate.setCellValueFactory(new PropertyValueFactory<Flight, LocalDate>("departureDate"));
        tableColumnAllDepartureTime.setCellValueFactory(new PropertyValueFactory<Flight, LocalTime>("departureTime"));
        tableColumnAllAirport.setCellValueFactory(new PropertyValueFactory<Flight,String>("airport"));
        tableColumnAllSeatsAvailable.setCellValueFactory(new PropertyValueFactory<Flight,Integer>("seatsNo"));

        tableViewAllFlights.setItems(dataAllFlights);
    }
    public void initializeTableViewSearchFlights(){
        tableColumnSearchDestination.setCellValueFactory(new PropertyValueFactory<Flight,String>("destination"));
        tableColumnSearchDepartureDate.setCellValueFactory(new PropertyValueFactory<Flight, LocalDate>("departureDate"));
        tableColumnSearchDepartureTime.setCellValueFactory(new PropertyValueFactory<Flight, LocalTime>("departureTime"));
        tableColumnSearchAirport.setCellValueFactory(new PropertyValueFactory<Flight,String>("airport"));
        tableColumnSearchSeatsAvailable.setCellValueFactory(new PropertyValueFactory<Flight,Integer>("seatsNo"));

        tableViewSearchFlights.setItems(dataSearchFlights);
    }

    public void setResources(Container container, Stage stage) {
        this.stage = stage;
        this.container = container;
        this.flightService = container.getFlightService();
        this.ticketService = container.getTicketService();

        spinnerNoOfSeats.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1,300,1));

        initializeTableViewAllFlights();
        initializeTableViewSearchFlights();
        initializeAllFlights();
    }

    public void initializeAllFlights(){
        List<Flight> allFlights = flightService.findAllAvailable();
        dataAllFlights.setAll(allFlights);
    }

    public void handleButtonSearch(ActionEvent actionEvent) {
        String destination;
        LocalDate departureDate;
        destination = textFieldDestination.getText();
        departureDate = datePickerDepartureDate.getValue();
        if(destination == null || destination == ""){
            Message.showError("No destination provided");
            return;
        }
        if(departureDate.isBefore(LocalDate.now())){
            Message.showError("Date should be in future");
            return;
        }
        List<Flight> searchedFlights = flightService.findByDestinationAndDate(destination,departureDate);
        if(searchedFlights.isEmpty()){
            Message.showMessage("Info","No flights found");
            dataSearchFlights.setAll();
            tableViewSearchFlights.setItems(dataSearchFlights);
            return;
        }
        dataSearchFlights.setAll(searchedFlights);
        tableViewSearchFlights.setItems(dataSearchFlights);
    }

    public void handleButtonBuyTicket(ActionEvent actionEvent) {
        String clientName = textFieldClientName.getText();
        String clientAddress = textFieldClientAddress.getText();
        String touristsNames = textFieldTouristsNames.getText();
        Integer noOfSeats = (Integer) spinnerNoOfSeats.getValue();
        List<String> touristNamesList;

        Flight selectedFlight = (Flight) tableViewSearchFlights.getSelectionModel().getSelectedItem();
        if(selectedFlight == null){
            Message.showError("No flight selected");
            return;
        }
        if(Objects.equals(clientName, "") || clientName == null){
            Message.showError("No client name provided");
            return;
        }
        if(Objects.equals(clientAddress, "") || clientAddress == null){
            Message.showError("No client address provided");
            return;
        }

        if(Objects.equals(touristsNames, "") || touristsNames == null){
            touristNamesList = new ArrayList<>();
        }
        else{
            touristNamesList = separateTouristsNames(touristsNames);
        }

        Ticket ticket = new Ticket(clientName,touristNamesList,clientAddress,noOfSeats,selectedFlight);
        try{
            ticketService.buyTicket(ticket);
            Message.showMessage("BuyTicket","Ticket Bought With Success");
        } catch (ValidationException | ServiceException e) {
            Message.showError(e.getMessage());
        }
    }

    public List<String> separateTouristsNames(String touristsName){
        List<String> touristsParsed = List.of(touristsName.split(","));
        List<String> touristsNameFormated = new ArrayList<>();
        for(String name : touristsParsed){
            while(name.charAt(0) == ' '){
                name = name.substring(1);
            }
            while(name.charAt(name.length()-1) == ' '){
                name = name.substring(0,name.length()-1);
            }
            touristsNameFormated.add(name);
        }
        return touristsNameFormated;
    }
}

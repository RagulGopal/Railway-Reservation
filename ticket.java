import java.util.Date;

public class ticket {
    String pnr;
    String passengerName;
    String source;
    String destination;
    Date travelDate;
    int seatNumber;
    String status;
    double fare;

    ticket(String pnr, String passengerName, String source, String destination,
           Date travelDate, int seatNumber, String status, double fare) {

        this.pnr = pnr;
        this.passengerName = passengerName;
        this.source = source;
        this.destination = destination;
        this.travelDate = travelDate;
        this.seatNumber = seatNumber;
        this.status = status;
        this.fare = fare;
    }

    void displayTicketInfo() {
        System.out.println("PNR            : " + pnr);
        System.out.println("Passenger Name : " + passengerName);
        System.out.println("Source         : " + source);
        System.out.println("Destination    : " + destination);
        System.out.println("Travel Date    : " + travelDate);
        System.out.println("Seat Number    : " + seatNumber);
        System.out.println("Fare           : ₹" + fare);
        System.out.println("Status         : " + status);
    }
}


public class train {
    int trainID;
    String trainName;
    String source;
    String destination;
    int totalSeats;
    int availableSeats;
    // Fare per ticket
    double fare;

    train(int trainID, String trainName, String source, String destination, int totalSeats, int availableSeats, double fare) {
        this.trainID = trainID;
        this.trainName = trainName;
        this.source = source;
        this.destination = destination;

        this.totalSeats = totalSeats;
        this.availableSeats = availableSeats;
        this.fare = fare;
    }

    void displaytrainInfo() {
        System.out.println("Train ID : " + trainID);

        System.out.println("Name     : " + trainName);
        System.out.println("Route    : " + source + " → " + destination);
        System.out.println("Seats    : " + availableSeats + "/" + totalSeats);
        System.out.println("Fare     : ₹" + fare);
        System.out.println("-----------------------------------------------------------------");
    }
}


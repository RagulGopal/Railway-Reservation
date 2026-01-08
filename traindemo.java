import java.text.SimpleDateFormat;
import java.util.*;

public class traindemo {

    static int seatCounter = 1;

    public static String generatePNR() {
        return "PNR" + (100000 + new Random().nextInt(900000));
    }

    public static void main(String[] args) {

        ArrayList<train> Alltrain = new ArrayList<>();
        Alltrain.add(new train(1, "Rajdhani Express", "Erode", "Chennai", 300, 150, 150.00));
        Alltrain.add(new train(2, "Shatabdi Express", "Erode", "Bangalore", 250, 100, 120.00));
        Alltrain.add(new train(3, "Kovai SF Express", "Kovai", "Chennai", 400, 200, 100.00));
        Alltrain.add(new train(4, "Yercaud Express", "Tirupur", "Chennai", 350, 175, 180.00));
        Alltrain.add(new train(5, "Cbe Express", "Erode", "Covai", 280, 140, 160.00));

        ArrayList<ticket> Allticket = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        int option = 0;

        while (option != 5) {

            System.out.println("\n===== TRAIN BOOKING SYSTEM =====");
            System.out.println("1. Display All Trains");
            System.out.println("2. Book Ticket");
            System.out.println("3. Cancel Ticket");
            System.out.println("4. Check PNR Status");
            System.out.println("5. Exit");
            System.out.print("\nEnter Option: ");
            option = sc.nextInt();

            switch (option) {

                case 1:
                    System.out.println("\n--- All Trains ---");
                    for (train t : Alltrain) t.displaytrainInfo();
                    break;

                case 2:
                    sc.nextLine();
                    System.out.print("\nEnter Source: ");
                    String userSource = sc.nextLine();

                    System.out.print("Enter Destination: ");
                    String userDestination = sc.nextLine();

                    ArrayList<train> availableTrains = new ArrayList<>();

                    for (train t : Alltrain) {
                        if (t.source.equalsIgnoreCase(userSource) &&
                            t.destination.equalsIgnoreCase(userDestination)) {
                            availableTrains.add(t);
                        }
                    }

                    if (availableTrains.isEmpty()) {
                        System.out.println("\n❌ No trains available for this route!");
                        break;
                    }

                    System.out.println("\nAvailable Trains:");
                    for (train t : availableTrains) t.displaytrainInfo();

                    System.out.print("\nEnter Train ID to book: ");
                    int selectedID = sc.nextInt();

                    train selectedTrain = null;
                    for (train t : availableTrains) {
                        if (t.trainID == selectedID) {
                            selectedTrain = t;
                            break;
                        }
                    }

                    if (selectedTrain == null) {
                        System.out.println("\n❌ Invalid Train ID!");
                        break;
                    }

                    if (selectedTrain.availableSeats <= 0) {
                        System.out.println("\n❌ No seats available!");
                        break;
                    }

                    sc.nextLine();
                    System.out.print("\nEnter Passenger Name: ");
                    String passengerName = sc.nextLine();

                    System.out.print("Enter Travel Date (dd-MM-yyyy): ");
                    String dateInput = sc.nextLine();

                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                    Date travelDate;

                    try {
                        travelDate = sdf.parse(dateInput);
                    } catch (Exception e) {
                        System.out.println("❌ Invalid date format!");
                        break;
                    }

                    String newPNR = generatePNR();
                    int seatNo = seatCounter++;

                    selectedTrain.availableSeats--;

                    ticket newTicket = new ticket(
                            newPNR,
                            passengerName,
                            selectedTrain.source,
                            selectedTrain.destination,
                            travelDate,
                            seatNo,
                            "BOOKED",
                            selectedTrain.fare
                    );

                    Allticket.add(newTicket);

                    System.out.println("\n✅ Ticket Booked Successfully!");
                    newTicket.displayTicketInfo();
                    break;

                case 3:
                    sc.nextLine();
                    System.out.print("\nEnter PNR to Cancel: ");
                    String cancelPNR = sc.nextLine();

                    boolean cancelled = false;

                    for (ticket t : Allticket) {
                        if (t.pnr.equalsIgnoreCase(cancelPNR)) {

                            if (t.status.equals("CANCELLED")) {
                                System.out.println("❌ Ticket already cancelled!");
                            } else {
                                t.status = "CANCELLED";

                                double cancellationCharge = t.fare * 0.05;
                                double refundAmount = t.fare - cancellationCharge;

                                System.out.println("\n✔ Ticket Cancelled Successfully!");
                                System.out.println("Fare Amount         : ₹" + t.fare);
                                System.out.println("Cancellation Charge : ₹" + cancellationCharge);
                                System.out.println("Refund Amount       : ₹" + refundAmount);
                                System.out.println("💰 Refund will be credited to your bank within 7 working days.");
                            }

                            cancelled = true;
                            break;
                        }
                    }

                    if (!cancelled) System.out.println("❌ PNR not found!");
                    break;

                case 4:
                    sc.nextLine();
                    System.out.print("\nEnter PNR: ");
                    String pnrInput = sc.nextLine();

                    boolean found = false;

                    for (ticket t : Allticket) {
                        if (t.pnr.equalsIgnoreCase(pnrInput)) {

                            System.out.println("\n===== FULL TICKET DETAILS =====");
                            t.displayTicketInfo();  // NEW — FULL DETAILS

                            if (t.status.equals("CANCELLED")) {
                                double cancellationCharge = t.fare * 0.05;
                                double refundAmount = t.fare - cancellationCharge;

                                System.out.println("Refund Amount : ₹" + refundAmount);
                                System.out.println("(Will be credited within 7 working days)");
                            }

                            found = true;
                            break;
                        }
                    }

                    if (!found) System.out.println("❌ Invalid PNR!");
                    break;

                case 5:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid option, try again.");
            }
        }

        sc.close();
    }
}

    

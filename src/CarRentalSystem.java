import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CarRentalSystem {
    private List<Car> cars;
    private List<Customer> customers;
    private List<Rental> rentals;

    public CarRentalSystem(){
        cars = new ArrayList<>();
        customers = new ArrayList<>();
        rentals = new ArrayList<>();
    }

    public void addCar(Car car){
        cars.add(car);
    }
    public void addCustomer(Customer customer){
        customers.add(customer);
    }
    public void rentalCar(Car car, Customer customer, int days){
        if (car.isAvailable()){
            car.rent();
            rentals.add(new Rental(car, customer, days));
        } else {
            System.out.println("Car is not available for rent.");
        }
    }
    public void returnCar(Car car){
        Rental rentalToRemove = null;
        for (Rental rental: rentals) {
            if (rental.getCar() == car){
                rentalToRemove = rental;
                car.returnCar();
                break;
            }
        }

        if (rentalToRemove != null){
            rentals.remove(rentalToRemove);
        } else {
            System.out.println("Car was not rented.");
        }
    }

    public  void menu(){
        Scanner scanner = new Scanner(System.in);

        while(true){
            System.out.println("===== Car Rental System =====");
            System.out.println("1. Rent A Car");
            System.out.println("2. Return A Car");
            System.out.println("3. Exit");
            System.out.print("Enter Your Choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 1){
                System.out.println("\n== Rent A Car ==\n");
                System.out.print("Enter Your Name: ");
                String customerName = scanner.nextLine();

                System.out.println("\nAvailable Cars: ");
                for (Car car: cars) {
                    if(car.isAvailable()){
                        System.out.println(car.getCarId() +" - "+ car.getBrand() + " " + car.getModel());
                    }
                }
                System.out.print("\nEnter The Car ID You Want To Rent: ");
                String carId = scanner.next();
                System.out.print("Enter The Number Of Days For Rental: ");
                int rentalDays = scanner.nextInt();
                scanner.nextLine();

                Customer newCustomer = new Customer(customerName, "CUS" +(customers.size()+1));
                addCustomer(newCustomer);

                Car selectedCar = null;
                for (Car car: cars) {
                    if (car.getCarId().equals(carId) && car.isAvailable()){
                        selectedCar = car;
                        break;
                    }
                }
                if (selectedCar != null) {
                    double totalPrice = selectedCar.calculatePrice(rentalDays);
                    System.out.println("\n== Rental Information ==\n");
                    System.out.println("Customer ID: " + newCustomer.getCustomerId());
                    System.out.println("Customer Name: " + newCustomer.getName());
                    System.out.println("Car: " + selectedCar.getBrand() + " " + selectedCar.getModel());
                    System.out.println("Rental Days: " + rentalDays);
                    System.out.printf("Total Price: $%.2f%n", totalPrice);

                    System.out.print("\nConfirm rental(Y/N): ");
                    String confirmed = scanner.nextLine();

                    if (confirmed.equalsIgnoreCase("Y")) {
                        rentalCar(selectedCar, newCustomer, rentalDays);
                        System.out.println("\nCar rented successfully.");
                    } else {
                        System.out.println("\nRental Canceled");
                    }
                } else {
                    System.out.println("\nInvalid car selection or not available for rent.");
                }
            } else if (choice == 2) {
                    System.out.println("\n== Return A Car ==\n");
                    System.out.print("Enter the car ID you want to return: ");
                    String carIdReturn = scanner.nextLine();

                    Car carToReturn = null;
                    for (Car car : cars) {
                        if (car.getCarId().equals(carIdReturn) && !car.isAvailable()){
                            carToReturn = car;
                            break;
                        }
                    }
                    if (carToReturn != null){
                          Customer customer = null;
                        for (Rental rental: rentals) {
                            if (rental.getCar() == carToReturn){
                                customer = rental.getCustomer();
                                break;
                            }
                        }
                        if (customer != null){
                            returnCar(carToReturn);
                            System.out.println("Car returned successfully by "+ customer.getName());
                        } else {
                            System.out.println("Car was not rented or rented information is missing.");
                        }
                    } else {
                        System.out.println("Invalid car ID or Car is not rented");
                    }
            } else if (choice == 3) {
                    break;
            } else {
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
        System.out.println("\nThank You for using the Car Rental System.");
    }
}

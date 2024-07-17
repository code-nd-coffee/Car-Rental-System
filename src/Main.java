public class Main {


    public static void main(String[] args) {
        CarRentalSystem carRentalSystem = new CarRentalSystem();
        Car car1 = new Car("C001","Toyota", "Camry", 60.0);
        Car car2 = new Car("C002","TATA", "Nexon", 70.0);
        Car car3 = new Car("C003","BMW", "Q7", 170.0);

        carRentalSystem.addCar(car1);
        carRentalSystem.addCar(car2);
        carRentalSystem.addCar(car3);

        carRentalSystem.menu();

    }
}
package org.example;


class Driver extends Person {
    private String licensePlate;

    public Driver(String name, int age, String destination, String licensePlate) {
        super(name, age, destination);
        this.licensePlate = licensePlate;
    }

    // Getter and setter for licensePlate
    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    // Method to check if a driver can pick up a passenger
    public boolean canPickUpPassenger(Passenger passenger) {
        return this.getDestination().equals(passenger.getDestination());
    }
}

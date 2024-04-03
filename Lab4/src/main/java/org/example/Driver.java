package org.example;


class Driver extends Person {
    private String licensePlate;
    private String[] alldestinations;

    public Driver(String name, int age, String destination, String licensePlate) {
        super(name, age, destination);
        this.licensePlate = licensePlate;
    }

    public String[] getAlldestinations() {
        return alldestinations;
    }

    public void setAlldestinations(String[] alldestinations) {
        this.alldestinations = alldestinations;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    @Override
    public String getDestination() {
        return super.getDestination();
    }

    @Override
    public int getIdDestination() {
        return super.getIdDestination();
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public boolean canPickUpPassenger(Passenger passenger) {
        return this.getDestination().equals(passenger.getDestination());
    }
}

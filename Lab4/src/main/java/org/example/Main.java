package org.example;
import com.github.javafaker.Faker;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;


public class Main {
    Problem problem=new Problem();
    public static void main(String[] args) {
        Main Adia=new Main();
        Adia.bonus();
    }
    public void bonus(){
        Problem problem1=new Problem(5000,5000,0.1);
        Map<Driver,Passenger> solution=problem1.solveBetterGreedy();
        System.out.println("Solution: " +solution);
    }

    public void homework(){
        List<String> allDestinations = problem.getAllDestinations();
        System.out.println("All destinations: " + allDestinations);

        Map<String, List<Person>> destinationMap = problem.getDestinationMap();
        System.out.println("Destination map: " + destinationMap);

        Map<Driver,Passenger> solution=problem.solveBetterGreedy();
        System.out.println("Solution: " +solution);
    }

    public void compulsory(){
        Passenger passenger1 = new Passenger("Maria", 25, "Strada Henri", false);
        Passenger passenger2 = new Passenger("Nicolae", 30, "Strada Henri", true);
        Passenger passenger3 = new Passenger("Lenuta", 30, "Strada Henri", true);


        Driver driver1 = new Driver("Klaus", 35, "Strada Henri", "ABC123");
        Driver driver2 = new Driver("Liviu", 40, "Strada Tineretului", "XYZ789");
        Driver driver3 = new Driver("Silviu", 42, "Strada Tineretului", "XYZ789");

        System.out.println(driver1.getName() + " can pick up " + passenger1.getName() + ": " + driver1.canPickUpPassenger(passenger1));
        System.out.println(driver1.getName() + " can pick up " + passenger2.getName() + ": " + driver1.canPickUpPassenger(passenger2));
        System.out.println(driver2.getName() + " can pick up " + passenger1.getName() + ": " + driver2.canPickUpPassenger(passenger1));

        LinkedList<Driver> driversList = new LinkedList<>();
        driversList.add(driver1);
        driversList.add(driver2);
        driversList.add(driver3);

        Collections.sort(driversList, Comparator.comparingInt(Person::getAge));

        System.out.println("Drivers sorted by age:");
        for (Driver driver : driversList) {
            System.out.println(driver.toString());
        }


        TreeSet<Passenger> passengersSet = new TreeSet<>(Comparator.comparing(Person::getName));
        passengersSet.add(passenger1);
        passengersSet.add(passenger2);
        passengersSet.add(passenger3);

        System.out.println("\nPassengers sorted by name:");
        for (Passenger passenger : passengersSet) {
            System.out.println(passenger.toString());
        }

        ArrayList<Person> persons= (ArrayList<Person>) generateRandomPersons(9);
        List<Driver> drivers = persons.stream()
                .filter(person -> person instanceof Driver)
                .map(person -> (Driver) person)
                .collect(Collectors.toList());

        List<Passenger> passengers = persons.stream()
                .filter(person -> person instanceof Passenger)
                .map(person -> (Passenger) person)
                .collect(Collectors.toList());

        System.out.println("Drivers:");
        drivers.forEach(driver -> System.out.println(driver.toString()));

        System.out.println("\nPassengers:");
        passengers.forEach(passenger -> System.out.println(passenger.toString()));
    }

    private List<Person> generateRandomPersons(int count) {
        List<Person> persons = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            if (random.nextBoolean()) {
                persons.add(new Driver("Driver" + i, random.nextInt(50), problem.helper.whichAddr((i%6==0)?6:i%6), "License" + i));
            } else {
                persons.add(new Passenger("Passenger" + i, random.nextInt(50), problem.helper.whichAddr((i%6==0)?6:i%6), random.nextBoolean()));
            }
        }
        return persons;
    }

}

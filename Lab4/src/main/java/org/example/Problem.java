package org.example;

import com.github.javafaker.Faker;

import java.util.*;
import java.util.stream.Collectors;

public class Problem {
    public Helper helper=new Helper();
    ArrayList<Person> people=new ArrayList<>(110);

    public Problem(){
        people= (ArrayList<Person>) generateRandomPersonsWithFaker(10);
        people.forEach(person -> helper.addAdress(person.getDestination()));
        people.forEach(person -> person.setIdDestination(helper.whichId(person.getDestination())));
    }
    private List<Person> generateRandomPersonsWithFaker(int count){
        Faker faker = new Faker();
        List<String> fakeAdresses= new ArrayList<>();
        for(int i=1; i<=((count/3>=1)?count/4:1); ++i)
            fakeAdresses.add(faker.address().streetAddress());
        List<Person> persons = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            if (random.nextBoolean()) {
                persons.add(new Driver("Driver " +  faker.name().fullName(), random.nextInt(50), (fakeAdresses.get(random.nextInt(50)%fakeAdresses.size())), "License" + i));
            } else {
                persons.add(new Passenger("Passenger " +  faker.name().fullName(), random.nextInt(50), (fakeAdresses.get(random.nextInt(50)%fakeAdresses.size())), random.nextBoolean()));
            }
        }
        return persons;

    }
    public void testFaker(){

        people= (ArrayList<Person>) generateRandomPersonsWithFaker(9);
        List<Driver> drivers = people.stream()
                .filter(person -> person instanceof Driver)
                .map(person -> (Driver) person)
                .collect(Collectors.toList());

        List<Passenger> passengers = people.stream()
                .filter(person -> person instanceof Passenger)
                .map(person -> (Passenger) person)
                .collect(Collectors.toList());

        System.out.println("Drivers:");
        drivers.forEach(driver -> System.out.println(driver.toString()));

        System.out.println("\nPassengers:");
        passengers.forEach(passenger -> System.out.println(passenger.toString()));
    }

    public List<String> getAllDestinations() {
        return people.stream()
                .map(person -> person.getDestination())
                .collect(Collectors.toList());
    }

    public Map<Driver,Passenger> solveGreedy(){
        Map<Driver,Passenger> matches=new HashMap<>();

        List<Driver> drivers=people.stream()
                .filter(person -> person instanceof Driver)
                .map(person -> (Driver) person)
                .collect(Collectors.toList());
        List<Passenger> passengers =people.stream()
                .filter(person -> person instanceof Passenger)
                .map(person -> (Passenger) person)
                .collect(Collectors.toList());

        drivers.sort(Comparator.comparing(Person::getDestination));
        passengers.sort(Comparator.comparing(Person::getDestination));

        Iterator<Driver> driverIterator = drivers.iterator();
        Iterator<Passenger> passengerIterator = passengers.iterator();
        while (driverIterator.hasNext() && passengerIterator.hasNext()) {
            Driver driver = driverIterator.next();
            Passenger passenger = passengerIterator.next();

            if (driver.getDestination().equals(passenger.getDestination())) {
                // If driver and passenger have the same destination, add them to the matches
                matches.put(driver, passenger);
                driverIterator.remove();
                passengerIterator.remove();
            }
        }
        return matches;
    }

    public Map<String, List<Person>> getDestinationMap() {
        Map<String, List<Person>> destinationMap = new HashMap<>();

        for(Person person:people){
            destinationMap.computeIfAbsent(person.getDestination(), k -> new ArrayList<>()).add(person);
        }
        return destinationMap;
    }
}

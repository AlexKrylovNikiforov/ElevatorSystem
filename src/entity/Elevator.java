package entity;

import java.util.ArrayList;
import java.util.List;

public class Elevator {
    private final int MAX_CAPACITY = 5;
    private int currentFloor;
    private Direction currentDirection;
    private List<Passenger> passengers;

    public Elevator() {
        this.currentFloor = 1;
        this.passengers = new ArrayList<>();
        this.currentDirection = Direction.UP;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void addPassenger(Passenger passenger) {
        this.passengers.add(passenger);
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }

    public void setCurrentDirection(Direction currentDirection) {
        this.currentDirection = currentDirection;
    }

    public void loadPassengers(List<Passenger> newPassengers) {
        passengers.addAll(newPassengers);
    }

    public void unloadPassengers() {
        passengers.removeIf(passenger -> passenger.getNeededFloor() == currentFloor);
    }

    public boolean hasAvailableSpace () {
        return passengers.size() < MAX_CAPACITY;
    }

    public boolean hasPassengers() {
        return !passengers.isEmpty();
    }

}

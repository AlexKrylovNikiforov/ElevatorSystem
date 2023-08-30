package entity;

import java.util.ArrayList;
import java.util.List;

public class Floor {
    private final int floorNumber;

    private final int MAX_CAPACITY;

    private List<Passenger> currentPassengerList;

    public Floor(int floorNumber, int max_capacity) {
        this.floorNumber = floorNumber;
        MAX_CAPACITY = max_capacity;
        currentPassengerList = new ArrayList<>(MAX_CAPACITY);
    }
    public int getMaxCapacity() {
        return MAX_CAPACITY;
    }
    public boolean hasAvailableSpace() {
        return currentPassengerList.size() < MAX_CAPACITY;
    }
    public int getFloorNumber() {
        return floorNumber;
    }

    public List<Passenger> getCurrentPassengers() {
        return currentPassengerList;
    }

    public void addPassenger (Passenger passenger) {
        currentPassengerList.add(passenger);
    }
}

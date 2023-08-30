package entity;

import java.util.ArrayList;
import java.util.List;

public class Building {

    private final int maxFloorCount;
    private final List<Floor> currentFloors;

    public boolean isEmpty() {
        return currentFloors.isEmpty();
    }

    public Building(int maxFloorCount) {
        this.maxFloorCount = maxFloorCount;
        currentFloors = new ArrayList<>();
    }

    public List<Floor> getCurrentFloors() {
        return currentFloors;
    }

    public int getMaxFloorCount()    {
        return maxFloorCount;
    }

    public boolean hasPassengersOnNextFloor(Elevator elevator) {
        int nextFloor = elevator.getCurrentDirection() == Direction.UP ? elevator.getCurrentFloor() + 1 : elevator.getCurrentFloor() - 1;
        Floor nextFloorObj = getFloorAt(nextFloor);
        return nextFloorObj.getCurrentPassengers().size() > 0;
    }

    public Floor getFloorAt(int floorNum) {
        return currentFloors.get(floorNum);
    }

    public int getNumFloors() {
        return currentFloors.size();
    }
}

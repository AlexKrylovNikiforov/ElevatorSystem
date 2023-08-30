package controller;

import entity.*;
import formatter.OutputFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class App implements RandomNumberGenerator{

    private Elevator elevator = new Elevator();
    private Building building = new Building(getRandomNumber(20));

    private ElevatorControlPanel controlPanel = new ElevatorControlPanel(elevator, building);
    private OutputFormatter outputFormatter = new OutputFormatter();
    public void start() {
        initializeObjects();
        outputFormatter.outputBuilding(building, elevator);
        while(!building.isEmpty()) {
            Floor currentFloor = building.getFloorAt(elevator.getCurrentFloor());
            if(elevator.hasPassengers()) {
                controlPanel.unloadPassengers();
                controlPanel.loadPassengers(currentFloor.getCurrentPassengers());
                controlPanel.moveToNextFloor();
            }
            else if(!elevator.hasPassengers()) {
                if(hasActiveCalls()) {
                    if(hasFloorCalls(currentFloor)) {
                        controlPanel.loadPassengers(currentFloor.getCurrentPassengers());
                        controlPanel.moveToNextFloor();
                    }
                }
                if(currentFloor.getFloorNumber() == 1 || currentFloor.getFloorNumber() == building.getMaxFloorCount()) {
                    elevator.setCurrentDirection(elevator.getCurrentDirection() == Direction.UP ? Direction.DOWN : Direction.UP);
                    controlPanel.moveToNextFloor();
                }

            }
        }
    }

    public Building getBuilding() {
        return building;
    }
    public Elevator getElevator() {
        return controlPanel.getElevator();
    }

    private void initializeObjects() {
        setFloors(this.building);
        setPassengersForEveryFloor(this.building);
    }
    private void setFloors (Building building) {
        for (int i = 1; i <= building.getMaxFloorCount(); i++) {
            building.getCurrentFloors().add(new Floor(i, 10));
        }
    }
    private void setPassengersForEveryFloor (Building building) {
        List<Floor> currentFloors = building.getCurrentFloors();
        for (Floor floor: currentFloors) {
            int counter = getRandomNumber(10);
            int passengerCount = 1;
            int floorNumber = floor.getFloorNumber();
            List<Passenger> currentPassengers = floor.getCurrentPassengers();
            while (passengerCount < counter) {
                int neededFloor = getNeededFloor(floorNumber, building.getMaxFloorCount());
                currentPassengers.add(new Passenger(floorNumber, neededFloor));
                passengerCount++;
            }
        }
    }
    private int getNeededFloor(int floorNumber, int maxCount) {
        int neededFloor = getRandomNumber(maxCount);
        if(neededFloor == floorNumber || neededFloor == 0) {
            neededFloor += getRandomNumber(getRandomNumber(maxCount));
        }
        return neededFloor;
    }

    private boolean hasActiveCalls() {
        for (int floorNum = 1; floorNum <= building.getNumFloors(); floorNum++) {
            Floor floor = building.getFloorAt(floorNum);
            for (Passenger passenger : floor.getCurrentPassengers()) {
                if (elevator.getCurrentDirection() == Direction.UP && passenger.getNeededFloor() > floorNum) {
                    return true;
                } else if (elevator.getCurrentDirection() == Direction.DOWN && passenger.getNeededFloor() < floorNum) {
                    return true;
                }
            }
        }
        return false;
    }

    //TODO: разбить метод
    private boolean hasFloorCalls(Floor floor) {
        List<Passenger> passengersOnFloor = floor.getCurrentPassengers();
        List<Passenger> passengersWithActiveCalls = passengersOnFloor.stream()
                .filter(p -> p.getNeededFloor() > elevator.getCurrentFloor() && elevator.getCurrentDirection() == Direction.UP)
                .filter(p -> p.getNeededFloor() < elevator.getCurrentFloor() && elevator.getCurrentDirection() == Direction.DOWN)
                .collect(Collectors.toList());
        if(passengersWithActiveCalls.isEmpty()) {
            return false;
        }
        return true;
    }
    @Override
    public int getRandomNumber(int x) {
        Random random = new Random();
        return random.nextInt(x) +1;
    }
}

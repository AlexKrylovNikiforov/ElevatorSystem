package controller;

import entity.Building;
import entity.Direction;
import entity.Elevator;
import entity.Passenger;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class ElevatorControlPanel implements RandomNumberGenerator {

    private final Elevator elevator;

    private final Building building;

    public ElevatorControlPanel(Elevator elevator, Building building) {
        this.elevator = elevator;
        this.building = building;
    }

    public Elevator getElevator() {
        return elevator;
    }

    public void unloadPassengers() {
        List<Passenger> passengers = elevator.getPassengers();
        passengers.removeIf(p -> p.getNeededFloor() == elevator.getCurrentFloor());
    }

    public void loadPassengers(List<Passenger> passengers) {
        List<Passenger> passengersInElevator = elevator.getPassengers();

        while (elevator.hasAvailableSpace() && !passengers.isEmpty()) {
            List<Passenger> validPassengers = passengers.stream()
                    .filter(p -> p.getNeededFloor() > elevator.getCurrentFloor() && elevator.getCurrentDirection() == Direction.UP)
                    .filter(p -> p.getNeededFloor() < elevator.getCurrentFloor() && elevator.getCurrentDirection() == Direction.DOWN)
                    .collect(Collectors.toList());

            if(validPassengers.isEmpty()) {
                break;
            }
            Passenger p = validPassengers.remove(0);
            passengersInElevator.add(p);
            passengers.remove(p);
        }

    }

    public void moveToNextFloor() {
        while(true) {
            int nextFloor = elevator.getCurrentDirection() == Direction.UP ? elevator.getCurrentFloor() + 1 : elevator.getCurrentFloor() - 1;
            boolean shouldMoveToFloor = shouldMoveToFloor(nextFloor);
            if(shouldMoveToFloor) {
                elevator.setCurrentFloor(nextFloor);
                moveToNextFloor();
            }
            else {
                break;
            }
        }
    }

    private boolean shouldMoveToFloor(int floor) {
        if (elevator.getCurrentDirection() == Direction.UP) {
            List<Passenger> passengers = building.getFloorAt(floor).getCurrentPassengers();
            for (Passenger passenger : passengers) {
                if (passenger.getNeededFloor() > floor) {
                    return true;
                }
            }
        } else if (elevator.getCurrentDirection() == Direction.DOWN) {
            List<Passenger> passengersOnFloor = building.getFloorAt(floor).getCurrentPassengers();
            for (Passenger passenger : passengersOnFloor) {
                if (passenger.getNeededFloor() < floor) {
                    return true;
                }
            }
        }
        return false;
    }


    @Override
    public int getRandomNumber(int x) {
        Random random = new Random();
        return random.nextInt(x+1);
    }
}

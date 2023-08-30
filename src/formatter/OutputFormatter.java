package formatter;

import entity.Building;
import entity.Elevator;
import entity.Floor;
import entity.Passenger;

public class OutputFormatter {
    private StringBuilder stringBuilder = new StringBuilder();

    public void outputBuilding (Building building, Elevator elevator) {
        for(Floor floor: building.getCurrentFloors()) {
            System.out.println(outputFloor(floor, elevator));
        }
    }

    private String outputFloor(Floor floor, Elevator elevator) {
        stringBuilder.append(" " + floor.getFloorNumber() + " | ")
                .append(outputElevator(elevator))
                .append(outputFloorPassengers(floor));
        return stringBuilder.toString();
    }
    private String outputFloorPassengers(Floor floor) {
        StringBuilder passengers = new StringBuilder();
        for(Passenger p: floor.getCurrentPassengers()) {
            passengers.append(p.getCurrentFloor() + p.getDirection().getLabel() + " ");
        }
        return passengers.toString().trim();
    }

    private String outputElevator(Elevator elevator) {
        int length = 19;
        stringBuilder.append(" " + elevator.getCurrentDirection().getLabel() + "  ");
        for(Passenger p: elevator.getPassengers()) {
            stringBuilder.append(p.getNeededFloor() + p.getDirection().getLabel() + " ");
        }
        int spacesToAdd = length - stringBuilder.length();
        if(spacesToAdd > 0) {
            stringBuilder.append(" ".repeat(spacesToAdd));
        }
        return stringBuilder.toString();
    }
}

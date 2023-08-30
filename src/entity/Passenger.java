package entity;

public class Passenger {
    private int currentFloor;
    private int neededFloor;

    public Passenger(int currentFloor, int neededFloor) {
        this.currentFloor = currentFloor;
        this.neededFloor = neededFloor;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public int getNeededFloor() {
        return neededFloor;
    }

    public Direction getDirection() {
        if(currentFloor < neededFloor) {
            return Direction.UP;
        }
        else return Direction.DOWN;
    }
}

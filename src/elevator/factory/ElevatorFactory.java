package elevator.factory;

import elevator.model.Elevator;

public class ElevatorFactory {
	
	public static Elevator getElevator(){
		
		return new Elevator();
		
	}

}

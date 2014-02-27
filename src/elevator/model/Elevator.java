package elevator.model;

import java.util.Collections;
import java.util.Vector;

public class Elevator {

	private int currentFloor;
	private int maxCapacity;
	private int totalPeople;
	private int totalFloors;
	private int numberFloors;
	private String elevatorStatus;
	private String doorStatus;
	private Vector<Integer> stopRouteUp;
	private Vector<Integer> stopRouteDown;

	public int getNumberFloors() {
		return numberFloors;
	}

	public void setNumberFloors(int numberFloors) {
		this.numberFloors = numberFloors;
	}

	public Elevator() {
		currentFloor = 0;
		maxCapacity = 8;
		totalPeople = 0;
		elevatorStatus = "Parado";
		doorStatus = "Aberta";
		stopRouteUp = new Vector<>();
		stopRouteDown = new Vector<>();
		totalFloors = 20;
	}

	public int getTotalPeople() {
		return totalPeople;
	}

	public void removePeople(int totalPeople) {

		if ((totalPeople < 0) || (this.totalPeople-totalPeople)<0) {

			throw new IllegalArgumentException(
					"Argumento inválido");

		} else {

			this.totalPeople = this.totalPeople - totalPeople;

		}

	}

	public void setTotalPeoples(int totalPeople) {

		if (totalPeople < 0) {

			throw new IllegalArgumentException(
					"Não é possivel inserir valores negativos");

		}

		if ((totalPeople + this.totalPeople) > maxCapacity) {

			throw new IllegalArgumentException(
					"Quantidade de passageiros excedida! Capacidade maxima:"
							+ maxCapacity);

		}

		else {
			this.totalPeople = this.totalPeople + totalPeople;
		}

	}

	public int getCurrentFloor() {
		return currentFloor;
	}

	public void setCurrentFloor(int currentFloor) {
		this.currentFloor = currentFloor;
	}



	public int getMaxCapacity() {
		return maxCapacity;
	}

	public void setMaxCapacity(int maxCapacity) {
		this.maxCapacity = maxCapacity;
	}

	public String getElevatorStatus() {
		return elevatorStatus;
	}

	public void setElevatorStatus(String status) {
		this.elevatorStatus = status;
	}

	public String getDoorStatus() {
		return doorStatus;
	}

	public void setDoorStatus(String doorStatus) {
		this.doorStatus = doorStatus;
	}

	public void addFloorInStopRouteUp(int floor) {
		if ((floor > totalFloors) || (floor < 0)) {
			throw new IllegalArgumentException(
					"Não existe o andar selecionado! Selecione de 0 a "
							+ totalFloors);
		} else {
			stopRouteUp.add(floor);
			Collections.sort(stopRouteUp);
		}

	}

	public Vector<Integer> getStopRouteUp() {
		return stopRouteUp;
	}

	public void addFloorInStopRouteDown(int floor) {
		if ((floor > totalFloors) || (floor < 0)) {
			throw new IllegalArgumentException(
					"Não existe o andar selecionado! Selecione de 0 a "
							+ totalFloors);

		} else {

			stopRouteDown.add(floor);
			Collections.sort(stopRouteDown, Collections.reverseOrder());

		}

	}

	public Vector<Integer> getStopRouteDown() {
		return stopRouteDown;
	}

}

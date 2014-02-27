package elevator.controller;

import java.util.Scanner;

import elevator.factory.ElevatorFactory;
import elevator.model.Elevator;

public class ElevatorController {

	private Elevator elevator;
	private Scanner input;
	boolean finish;
	String aux;

	public ElevatorController() {

		elevator = ElevatorFactory.getElevator();
		input = new Scanner(System.in);
		finish = false;

	}

	public void init() {

		System.out.println("*************Elevator Simulation*************\n");

		while (!finish) {

			System.out.println(this.getElevatorInfo());
			this.enterPassenger();
			this.selectFloor();
			this.moveElevator();
			System.out.println(this.getElevatorInfo());
			this.exitPassenger();
			this.moveElevator();

			if ((elevator.getTotalPeople() == 0)
					&& (elevator.getStopRouteUp().isEmpty())
					&& (elevator.getStopRouteDown().isEmpty())) {
				System.out
						.println("Não temos mais passageiros e nem rotas! Deseja encerrar o Simulador? S ou N");
				String c;
				c = input.next().toUpperCase();
				if (c.equals("S")) {
					finish = true;
				}
			}

		}
		System.out.println("O elevador finalizou a rota");

	}

	public void enterPassenger() {

		System.out.println("Quantos passageiros estão Entrando?");
		int numPeople = input.nextInt();
		this.addPeople(numPeople);
		if (!elevator.getStopRouteDown().isEmpty()
				|| !elevator.getStopRouteUp().isEmpty())
			elevator.setDoorStatus("Fechada");

	}

	public void exitPassenger() {

		System.out.println("Quantos passageiros estão Saindo?");
		int numPeople = input.nextInt();
		this.removePeople(numPeople);

	}

	public void selectFloor() {

		String c;

		System.out.println("Deseja selecionar o andar? S ou N");
		c = input.next().toUpperCase();

		while (c.equals("S")) {

			System.out.println("Selecione o andar de parada:");
			int stopFloor = input.nextInt();
			this.addFloorStop(stopFloor);
			System.out.println("Deseja selecionar outro andar? S ou N");
			c = input.next().toUpperCase();

		}
		;

	}

	public String getElevatorInfo() {
		String info = "";
		info = "\n**************************************\n"
				+ "Estatus do Elevador: " + elevator.getElevatorStatus()
				+ "\nNo andar " + elevator.getCurrentFloor() + " \n"
				+ "Sua porta está: " + elevator.getDoorStatus() + "\n" + "Com "
				+ elevator.getTotalPeople() + " passageiros.";

		return info;
	}

	public void addFloorStop(int floor) {

		if (elevator.getCurrentFloor() < floor) {

			try {
				elevator.addFloorInStopRouteUp(floor);
			} catch (IllegalArgumentException e) {

				System.out.println(e.getMessage());
			}

			if (elevator.getDoorStatus() == "Aberta") {
				elevator.setDoorStatus("Fechada");
			}
		}

		if (elevator.getCurrentFloor() > floor) {
			try {
				elevator.addFloorInStopRouteDown(floor);
			}

			catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}

			if (elevator.getDoorStatus() == "Aberta") {
				elevator.setDoorStatus("Fechada");
			}

		}

	}

	void changeElevatorStatus() {
		if ((elevator.getElevatorStatus() == "Parado")
				&& (!elevator.getStopRouteUp().isEmpty())) {
			elevator.setElevatorStatus("Subindo");
			aux = "Subindo";
		}

		if ((elevator.getStopRouteUp().isEmpty())
				&& (!elevator.getStopRouteDown().isEmpty())) {
			elevator.setElevatorStatus("Descendo");
			aux = "Descendo";
		}

		if ((elevator.getStopRouteUp().isEmpty())
				&& (elevator.getStopRouteDown().isEmpty())
				&& (elevator.getTotalPeople() == 0)) {
			elevator.setElevatorStatus("Parado");
			finish = true;
		}

	}

	public void moveElevator() {
		if (elevator.getDoorStatus() == "Fechada") {

			this.changeElevatorStatus();

			if (elevator.getElevatorStatus() == "Subindo" || aux == "Subindo") {

				elevator.setCurrentFloor(elevator.getStopRouteUp()
						.firstElement());
				// System.out.println("Foi para o andar "+
				// elevator.getCurrentFloor());
				elevator.getStopRouteUp().remove(0);
				System.out.println("Subindo");
				elevator.setDoorStatus("Aberta");
				elevator.setElevatorStatus("Parado");
				aux = "Subindo";
			}

			if (elevator.getElevatorStatus() == "Descendo" || aux == "Descendo") {

				elevator.setCurrentFloor(elevator.getStopRouteDown()
						.firstElement());
				// System.out.println("Foi para o andar "+
				// elevator.getCurrentFloor());
				elevator.getStopRouteDown().remove(0);
				System.out.println("Descendo");
				elevator.setDoorStatus("Aberta");
				elevator.setElevatorStatus("Parado");
				aux = "Descendo";

			}

			if (finish) {
				elevator.setCurrentFloor(0);
				// System.out.println("Foi para o andar "+
				// elevator.getCurrentFloor());
				elevator.setDoorStatus("Aberta");

			}
		}
	}

	public void addPeople(int people) {

		try {
			elevator.setTotalPeoples(people);
		}

		catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

	}

	public void removePeople(int people) {

		try {
			elevator.removePeople(people);
		}

		catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

	}

}

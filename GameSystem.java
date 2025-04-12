package chap01;

import java.util.Scanner;

public class GameSystem {
	public static void EasterEgg() {
		System.out.println("들어와서는 안되는 공간에 들어섰습니다..");
		String itemName = "";

		if (Inventory.slots.size() >= Inventory.MaxSlot) {
			System.out.println("모든 욕심을 버리고 다시 오십시오..");
		} else {
			itemName = "이계에서온반자동심판자";
			Inventory.Slot.addItem(itemName, "무기");
			System.out.println("이질적인 무언가를 손에 얻었습니다.. 뭐든지 한 방에 처리할 수 있습니다.");
		}

		if (Inventory.slots.size() >= Inventory.MaxSlot) {
			System.out.println("모든 욕심을 버리고 다시 오십시오..");
		} else {
			itemName = "쉽게배우는자바프로그래밍";
			Inventory.Slot.addItem(itemName, "소모품");
			System.out.println("이질적인 무언가를 손에 얻었습니다.. 수업 교재입니다.. 수업이 뭐지?");
		}

		if (Inventory.slots.size() >= Inventory.MaxSlot) {
			System.out.println("모든 욕심을 버리고 다시 오십시오..");
		} else {
			itemName = "김형균교수님의객지프수업";
			Inventory.Slot.addItem(itemName, "소모품");
			System.out.println("이질적인 소리가 들립니다.. 유익합니다..");

		}
	}
}

class GameStart {
	Scanner in = new Scanner(System.in);
	String user_name;

	public static void Start() {
		Scanner in = new Scanner(System.in);
		System.out.print("사용자명을 입력하세요. : ");
		String user_name = in.next();
		System.out.println("이름이 입력되었습니다.");
		System.out.println("게임에 입장하였습니다.");

		Status.name = user_name;
		Status.lev = 1;
		Status.hp = 80;
		Status.power = 15;
		Status.defense = 25;
		Status.exp = 0;
		Status.coin = 0;
		Status.mp = 0;
	}
}

class GameOver {
	static Scanner in = new Scanner(System.in);

	public static void dead(String reason) {
		System.out.println("\n****[ 게임 오버 ]****");
		switch (reason) {
		case "defeat":
			System.out.println("전투에서 패배하여 쓰러졌습니다...");
			Status.coin = 0;
			Inventory.slots.clear();
			System.out.println("모든 것을 잃고 마을에서 부활합니다.");
			Status.hp = Status.maxHp;
			User.showMenu();
			break;
		case "happyhappyhappy":
			System.out.println(Status.name + "은(는) 이곳이 게임 세상인 걸 인지해버렸습니다.");
			System.exit(0);
			break;
		default:
			System.out.println("알 수 없는 이유로 게임이 종료되었습니다.");

		}
	}
}

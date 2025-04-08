package chap01;

import java.util.Scanner;

public class PotionStore {
	static Scanner in = new Scanner(System.in);

	static void potionStoreMenu() {
		boolean continueBuy = true;

		while (continueBuy) {
			System.out.println("***************************");
			System.out.println("포션 상점에 입장했습니다.");
			System.out.println("보유 골드: " + Status.coin);
			System.out.println("구입할 포션을 선택하세요.");
			System.out.println("0. 상점 나가기");
			System.out.println("1. hp포션 (30골드)");
			System.out.println("2. mp포션 (50골드)");
			System.out.println("3. 강화포션 (100골드)");
			System.out.println("***************************");
			System.out.print(">> 선택: ");

			int choice = in.nextInt();
			in.nextLine();

			String itemName = "";
			int price = 0;

			switch (choice) {
			case 0:
				System.out.println("상점을 떠납니다.");
				continueBuy = false;
				break;
			case 1:
				itemName = "hp포션";
				price = 30;
				break;
			case 2:
				itemName = "mp포션";
				price = 50;
				break;
			case 3:
				itemName = "강화포션";
				price = 100;
				break;
			default:
				System.out.println("잘못된 선택입니다. 다시 입력하세요.");
				continue;
			}

			if (choice == 0)
				break;

			if (Inventory.slots.size() >= Inventory.MaxSlot) {
				System.out.println("인벤토리가 가득 찼습니다. 욕심이 과합니다..");
				continue;
			}

			if (Status.coin < price) {
				System.out.println("이것을 사기엔 지갑이 가볍습니다. 현재 보유: " + Status.coin);
			} else {
				Inventory.Slot.addItem(itemName, "소모품");
				Status.coin -= price;
				System.out.println(itemName + "을(를) 구매했습니다. 남은 골드: " + Status.coin);
			}
		}
	}
}

class WeaponStore {
	static Scanner in = new Scanner(System.in);

	static void WeaponStoreMenu() {
		boolean continueBuy = true;

		while (continueBuy) {
			System.out.println("***************************");
			System.out.println("무기 상점에 입장했습니다.");
			System.out.println("보유 골드: " + Status.coin);
			System.out.println("구입할 무기을 선택하세요.");
			System.out.println("0. 상점 나가기");
			System.out.println("1. 목검 (500골드)");
			System.out.println("2. 나무방패 (500골드)");
			System.out.println("***************************");
			System.out.print(">> 선택: ");

			int choice = in.nextInt();
			in.nextLine();

			String itemName = "";
			int price = 0;

			switch (choice) {
			case 0:
				System.out.println("상점을 떠납니다.");
				continueBuy = false;
				break;
			case 1:
				itemName = "목검";
				price = 500;
				break;
			case 2:
				itemName = "나무방패";
				price = 500;
				break;
			default:
				System.out.println("잘못된 선택입니다. 다시 입력하세요.");
				continue;
			}

			if (choice == 0)
				break;

			if (Inventory.slots.size() >= Inventory.MaxSlot) {
				System.out.println("인벤토리가 가득 찼습니다. 욕심이 과합니다..");
				continue;
			}

			if (Status.coin < price) {
				System.out.println("이것을 사기엔 지갑이 가볍습니다. 현재 보유: " + Status.coin);
			} else {
				Inventory.Slot.addItem(itemName, "소모품");
				Status.coin -= price;
				System.out.println(itemName + "을(를) 구매했습니다. 남은 골드: " + Status.coin);
			}
		}
	}
}

class BookStore {
	static Scanner in = new Scanner(System.in);

	static void BookStoreMenu() {
		boolean continueBuy = true;

		while (continueBuy) {
			System.out.println("***************************");
			System.out.println("서점에 입장했습니다.");
			System.out.println("보유 골드: " + Status.coin);
			System.out.println("구입할 서적을 선택하세요.");
			System.out.println("0. 상점 나가기");
			System.out.println("1. 마법공학입문 (250골드)");
			System.out.println("***************************");
			System.out.print(">> 선택: ");

			int choice = in.nextInt();
			in.nextLine();

			String itemName = "";
			int price = 0;

			switch (choice) {
			case 0:
				System.out.println("상점을 떠납니다.");
				continueBuy = false;
				break;
			case 1:
				itemName = "마법공학입문";
				price = 250;
				break;
			case 666:
				break;
			default:
				System.out.println("잘못된 선택입니다. 다시 입력하세요.");
				continue;
			}

			if (choice == 0)
				break;

			if (Inventory.slots.size() >= Inventory.MaxSlot) {
				System.out.println("인벤토리가 가득 찼습니다. 욕심이 과합니다..");
				continue;
			}

			if (Status.coin < price) {
				System.out.println("이것을 사기엔 지갑이 가볍습니다. 현재 보유: " + Status.coin);
			} else {
				Inventory.Slot.addItem(itemName, "소모품");
				Status.coin -= price;
				System.out.println(itemName + "을(를) 구매했습니다. 남은 골드: " + Status.coin);
			}
		}
	}
}
package chap01;

import java.util.ArrayList;
import java.util.Scanner;

class Inventory {
	static final int MaxSlot = 8;
	public static ArrayList<Slot> slots = new ArrayList<>();

	public static boolean haveItem(String name) {
		for (Slot s : slots) {
			if (s.name.equals(name))
				return true;
		}
		return false;
	}

	static class Slot {
		String name, type;
		int count;

		Slot(String name, String type) {
			this.name = name;
			this.type = type;
			this.count = 1;
		}

		boolean Full() {
			return count >= 8;
		}

		public static void showInventory() {
			System.out.println("\n****[인벤토리 목록]****");
			for (int i = 0; i < slots.size(); i++) {
				Slot s = slots.get(i);
				System.out.println((i + 1) + ". " + s.name + " (" + s.type + ") x" + s.count);
			}
		}

		public static void discard(int index) {
			if (index < 1 || index > slots.size()) {
				System.out.println("잘못된 선택입니다.");
				return;
			}
			Slot s = slots.get(index - 1);
			System.out.println(s.name + " x" + s.count + " 을(를) 전부 버렸습니다.");
			slots.remove(index - 1);
		}

		public static void addItem(String name, String type) {
			if (slots.size() >= MaxSlot) {
				System.out.println("인벤토리가 가득 찼습니다. 버릴 아이템을 선택하세요.");
				return;
			}
			for (int i = 0; i < slots.size(); i++) {
				Slot s = slots.get(i);
				for (int j = 0; j < slots.size(); j++) {
					if (s.name.equals(name) && s.type.equals(type) && !s.Full()) {
						s.count++;
						System.out.println(name + "을(를) 추가했습니다. (x" + s.count + ")");
						return;
					}
				}
			}
			Slot newSlot = new Slot(name, type);
			slots.add(newSlot);
			System.out.println(name + "을(를) 새로 추가했습니다. (x" + newSlot.count + ")");
		}

	}

	public static void handleItem(Scanner in) {
		if (slots.isEmpty()) {
			System.out.println("인벤토리가 비어 있습니다.");
			return;
		}

		Slot.showInventory();
		System.out.println("1. 아이템 사용");
		System.out.println("2. 아이템 버리기");
		System.out.print(">> 선택: ");
		int action = in.nextInt();
		in.nextLine();

		switch (action) {
		case 1:
			System.out.print("사용할 아이템 이름을 입력하세요: ");
			String useItem = in.nextLine();
			if (Item.getType(useItem).equals("전투소모품")) {
				System.out.println("전투 중이 아닙니다. 사용할 수 없습니다.");
				return;
			}
			if (haveItem(useItem)) {
				ItemUse1.use(useItem, false); // 전투 상황 아님
			} else {
				System.out.println("해당 아이템이 없습니다.");
			}
			break;

		case 2:
			System.out.print("버릴 아이템 번호를 입력하세요: ");
			int discardIndex = in.nextInt();
			in.nextLine(); // 버퍼 클리어
			Slot.discard(discardIndex);
			break;

		default:
			System.out.println("잘못된 선택입니다.");
		}
	}

}

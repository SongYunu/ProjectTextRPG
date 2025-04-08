package chap01;

import java.util.Random;

class Item {
	static String hp포션, mp포션, 강화포션, 마법공학입문, happy포션, 쉽게배우는자바프로그래밍, 김형균교수님의객지프수업;
	static String 연막탄, 밧줄;
	static String 나무방패, 목검, 이계에서온반자동심판자;

	public static String getType(String itemName) {
		switch (itemName) {
		case "hp포션":
		case "mp포션":
		case "happy포션":
		case "강화포션":
		case "마법공학입문":
		case "쉽게배우는자바프로그래밍":
		case "김형균교수님의객지프수업":
			return "소모품";

		case "연막탄":
		case "밧줄":
			return "전투소모품";

		case "나무방패":
		case "목검":
		case "집에서뜯어온파이프":
		case "이계에서온반자동심판자":
			return "무기";

		default:
			return "존재하지 않는 종류입니다.";
		}
	}

}

class ItemUse1 {
	public static void use(String name, boolean inBattle) {
		for (int i = 0; i < Inventory.slots.size(); i++) {
			Inventory.Slot s = Inventory.slots.get(i);
			if (s.name.equals(name)) {
				String type = Item.getType(name);

				switch (type) {
				case "소모품":
					if (name.equals("hp포션")) {
						int heal = (int) (Status.maxHp * 0.2);
						Status.hp += heal;
						if (Status.hp > Status.maxHp)
							Status.hp = Status.maxHp;
						System.out.println("HP가 " + heal + " 건강해졌습니다. 현재 HP: " + Status.hp);
					} else if (name.equals("mp포션")) {
						if (Status.mp <= 0) {
							System.out.println("mp가 존재하지 않습니다..");
							return;
						}
						int recover = (int) (Status.mp * 0.2);
						Status.mp += recover;
						if (Status.mp > Status.maxMp)
							Status.mp = Status.maxMp;
						System.out.println("MP가 " + recover + " 두뇌회전이 빨라집니다. 현재 MP: " + Status.mp);
					} else if (name.equals("happy포션")) {
						System.out.println("빠른 서렌은 때때로 행복해지는 방법입니다.");
						GameOver.dead("happyhappyhappy");
						return;
					} else if (name.equals("강화포션")) {
						Status.power += 3;
						System.out.println("근육이 펌핑됩니다.\n 현재 Power : " + Status.power);
					} else if (name.equals("마법공학입문")) {
						Status.maxMp += 3;
						System.out.println("어려운 공부를 하니 머리가 트입니다. 최대 mp가 상승합니다.\n 현재 MaxMP: " + Status.maxMp);
					} else if (name.equals("쉽게배우는자바프로그래밍")) {
						if (Status.usedEasyJava) {
							System.out.println("이 아이템은 이미 사용했습니다!");
							return;
						}
						Status.maxMp += 100;
						Status.usedEasyJava = true;
						System.out.println("자바 공부로 고능해졌습니다. 최대 mp가 상승합니다.\n 현재 MaxMP: " + Status.maxMp);
					} else if (name.equals("김형균교수님의객지프수업")) {
						if (Status.usedObjProg) {
							System.out.println("이 아이템은 이미 사용했습니다!");
							return;
						}
						Status.maxMp += 100;
						Status.usedObjProg = true;
						System.out.println("객지프 수업을 들어 고능해졌습니다. 최대 mp가 상승합니다.\n 현재 MaxMP: " + Status.maxMp);
					}
					break;

				case "전투소모품":
					System.out.println(name + "은(는) 현재 사용할 수 없습니다.");
					return;

				case "무기":
					System.out.println("항시 사용중입니다.");
					return;

				default:
					System.out.println("이 아이템은 사용할 수 없습니다.");
					return;
				}

				s.count--;
				if (s.count <= 0) {
					Inventory.slots.remove(i);
					System.out.println(name + "이(가) 전부 소모되었습니다.");
				}
				return;
			}
		}
		System.out.println(name + "이(가) 인벤토리에 없습니다.");
	}

}

class ItemUse2 {
	static int skipTurn = 0;

	public static boolean use(String name) {
		for (int i = 0; i < Inventory.slots.size(); i++) {
			Inventory.Slot s = Inventory.slots.get(i);
			if (s.name.equals(name)) {
				String type = Item.getType(name);
				switch (type) {
				case "전투소모품":
					if (name.equals("밧줄")) {
						System.out.println("몬스터가 포박당했습니다. 2턴 간 공격할 수 있습니다.");
						skipTurn = 2;
					} else if (name.equals("연막탄")) {
						Random num = new Random();
						if (num.nextInt(100) < 70) {
							System.out.println("시야를 가려 도망치는데 성공했습니다.");
							useAndRemove(i, s);
							return true;
						} else {
							System.out.println("인간의 간사한 수법으로는 맹수의 눈길을 피하지 못합니다.");
						}
					}
					useAndRemove(i, s);
					return false;
				}
			}
		}
		System.out.println(name + "이(가) 인벤토리에 없습니다.");
		return false;
	}

	private static void useAndRemove(int index, Inventory.Slot s) {
		s.count--;
		if (s.count <= 0) {
			Inventory.slots.remove(index);
			System.out.println(s.name + "을(를) 모두 사용했습니다.");
		}
	}
}

class ItemUse3 {
	public static void PassiveItem() {
		Status.passiveAttackBonus = 0;
		Status.passiveDefenseBonus = 0;
		for (Inventory.Slot s : Inventory.slots) {
			if (s.name.equals("목검")) {
				Status.passiveAttackBonus += 10;
			} else if (s.name.equals("나무방패")) {
				Status.passiveDefenseBonus += 10;
			} else if (s.name.equals("이계에서온반자동심판자")) {
				Status.passiveAttackBonus += 99999;
			}
		}
	}
}

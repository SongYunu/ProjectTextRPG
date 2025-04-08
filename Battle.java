package chap01;

import java.util.ArrayList;
import java.util.Scanner;

public class Battle {
	static Scanner in = new Scanner(System.in);

	public static void BattleStart() {
		while (true) {
			ArrayList<Monster> monsterList = new ArrayList<>();
			monsterList.add(new Monster("너구리", 1, 20, 20, 5, 10, 10));
			monsterList.add(new Monster("살쾡이", 5, 2000, 100, 20, 30, 50));

			System.out.println("\n**** [싸울 상대를 선택하세요] ****");
			System.out.println("0. 집에 간다.");
			System.out.println("1. Level 1. 너구리");
			System.out.println("2. Level 5. 살쾡이");
			System.out.print(">> 선택: ");
			int answer = in.nextInt();

			switch (answer) {
			case 0:
				System.out.println("집으로 돌아갑니다.");
				return;
			case 1:
				start(monsterList.get(0));
				return;
			case 2:
				start(monsterList.get(1));
				return;
			default:
				System.out.println("존재하지 않는 선택지입니다. 다시 입력하세요.");
			}
		}
	}

	public static void start(Monster monster) {
		System.out.println("\n " + monster.name + "이(가) 길을 막아섰습니다.");
		monster.MonsterStatus();

		while (monster.MonsterAlive() && Status.hp > 0) {
			System.out.println("\n**** [어떻게 할까..??] ****");
			System.out.println("1. 공격  2. 아이템 사용");
			System.out.print(">> 선택: ");
			int choice = in.nextInt();
			in.nextLine();
			boolean continueBattle = true;

			while (continueBattle) {
				switch (choice) {
				case 1:
					monster.takeDamage(Status.power);
					if (monster.MonsterAlive()) {
						monsterAttack(monster);
					} else {
						System.out.println(monster.name + "은(는) 자연과 하나가 되었습니다.");
						System.out.println(Status.name + " 은(는) " + monster.rewardExp + " 경험치와 " + monster.rewardCoin
								+ " 골드를 얻었습니다.");
						Status.exp += monster.rewardExp;
						Status.coin += monster.rewardCoin;
						Status.levelUp();
					}
					continueBattle = false;
					break;

				case 2:
					Inventory.Slot.showInventory();
					System.out.print("사용할 아이템 이름 입력: ");
					String itemName = in.nextLine();

					String type = Item.getType(itemName);
					if (type.equals("전투소모품")) {
						ItemUse2.use(itemName);
					} else if (type.equals("소모품")) {
						ItemUse1.use(itemName, true);
					} else {
						System.out.println("사용할 수 없는 아이템입니다.");
					}
					continueBattle = false;
					break;

				default:
					System.out.println("잘못된 입력입니다.");
					break;
				}
			}
		}
	}

	public static void monsterAttack(Monster monster) {
		if (ItemUse2.skipTurn > 0) {
			System.out.println(monster.name + "은(는) 포박당해있습니다.");
			ItemUse2.skipTurn--;
			return;
		}

		int dmg = monster.power - Status.defense;
		if (dmg < 0)
			dmg = 0;
		Status.hp -= dmg;
		if (Status.hp > 0)
			System.out.println(monster.name + "이(가) 공격하여 " + dmg + "만큼 피해를 입었습니다. 현재 HP: " + Status.hp);
		else
			System.out.println(monster.name + "이(가) 공격하여 " + dmg + "만큼 피해를 입었습니다. 현재 HP: 0");

		if (Status.hp <= 0) {
			GameOver.dead("defeat");
		}
	}
}

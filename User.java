package chap01;

import java.util.Scanner;

public class User {
	static Scanner sc = new Scanner(System.in);

	public static void showMenu() {
		while (true) {
			System.out.println("\n**** [시간이 빈다.. 무엇을 할까?] ****");
			System.out.println("1. 전투에 들어간다");
			System.out.println("2. 아이템을 사용한다");
			System.out.println("3. 상점에 간다");
			System.out.println("4. 나의 멋진 능력을 확인한다");
			System.out.print(">> 선택: ");
			int answer = sc.nextInt();
			switch (answer) {
			case 1:
				Battle.BattleStart();
				break;
			case 2:
				Inventory.handleItem(sc);
				break;
			case 3:
				System.out.println("\\n**** [눈 앞에 재밌어보이는 가게가 너무나도 많다..] ****");
				System.out.println("1. 포션상점에 간다");
				System.out.println("2. 무기상점에 간다");
				System.out.println("3. 서점에 간다");
				int answer2 = sc.nextInt();
				switch (answer2) {
				case 1:
					PotionStore.potionStoreMenu();
					;
					break;
				case 2:
					WeaponStore.WeaponStoreMenu();
					break;
				case 3:
					BookStore.BookStoreMenu();
					break;
				default:
					System.out.println("오늘은 아이쇼핑만 하기로 했다.");
				}

				break;
			case 4:
				Status.UI();
				break;
			case 20243222:
				GameSystem.EasterEgg();
				break;
			default:
				System.out.println("잘못된 입력입니다.");
			}
		}
	}

}

class Status {
	public static int lev, power, hp, defense, mp, exp, coin;
	public static String name;

	static void UI() {
		ItemUse3.PassiveItem();
		System.out.println("***************************\n");
		System.out.println(Status.name + "님의 모험을 함께하고 있습니다.");
		System.out.println("Level : " + Status.lev);
		System.out.println("Power : " + Status. getTotalPower());
		System.out.println("Defense : " + Status.getTotalDefense());
		System.out.println("HP : " + Status.hp);
		System.out.println("MP : " + Status.mp);
		System.out.println("Exp : " + Status.exp);
		System.out.println("Coin : " + Status.coin);
		System.out.println("***************************\n");
	}

	static int maxHp = 100;
	static int maxMp = 0;
	static int maxLev = 100;

	public static int passiveAttackBonus = 0;
	public static int passiveDefenseBonus = 0;

	public static int getTotalPower() {
		return power + passiveAttackBonus;
	}

	public static int getTotalDefense() {
		return defense + passiveDefenseBonus;
	}

	static boolean usedEasyJava = false;
	static boolean usedObjProg = false;

	public static void levelUp() {
		if (exp >= maxLev) {
			exp -= maxLev;
			lev++;
			maxLev += 50 * lev;
			maxHp += lev * 10;
			hp = maxHp;
			power += (int) (lev * (0.5));
			defense += (int) (lev * (0.5));
			mp = mp * 2;
			System.out.println("== 레벨 업! ==");
			System.out.println(name + "는 " + lev + "레벨이 되어 유능해졌습니다.");
			System.out.println("HP가 회복되고 능력치가 상승했습니다.");
			int bonusMoney = lev * 50;
			coin += bonusMoney;
			System.out.println("레벨업을 하여 " + bonusMoney + " 골드를 얻었습니다. 현재 소지금: " + coin);
			UI();
		}
	}

}

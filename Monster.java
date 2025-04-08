package chap01;

class Monster {
	public String name;
	public int level, hp, power, defense, mp;

	public int rewardExp;
	public int rewardCoin;

	public void MonsterStatus() {
		System.out.println("***************************\n");
		System.out.println(name + "의 전투력을 전투력 측정기로 보았다..");
		System.out.println("Level : " + level);
		System.out.println("Power : " + power);
		System.out.println("Defense : " + defense);
		System.out.println("HP : " + hp);
		System.out.println("MP : " + mp);
		System.out.println("***************************\n");
	}

	public boolean MonsterAlive() {
		return hp > 0;
	}

	public void takeDamage(int dmg) {
		int realDmg = dmg - defense;
		if (realDmg < 0)
			realDmg = 0;
		hp -= realDmg;
		System.out.println(name + "에게 " + realDmg + "의 피해를 입혔습니다.");

	}

	public Monster(String name, int level, int hp, int power, int defense, int exp, int coin) {
		this.name = name;
		this.level = level;
		this.hp = hp;
		this.power = power;
		this.defense = defense;
		this.rewardExp = exp;
		this.rewardCoin = coin;
	}

}

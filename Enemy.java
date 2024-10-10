public class Enemy {
    private String enemyName;
    private String enemyDescription;
    private Weapon weapon;
    private double enemyDamage;
    private double enemyHP;

    public Enemy(String enemyName,String enemyDescription, Weapon weapon, double enemyDamage, double enemyHP) {
        this.enemyName = enemyName;
        this.enemyDescription = enemyDescription;
        this.enemyDamage = enemyDamage;
        this.weapon = weapon;
        this.enemyHP = enemyHP;
    }

    public void takeDamage(double damage) {
        enemyHP -= damage;
        if (enemyHP < 0) {
            enemyHP = 0;
        }
    }

    public String getEnemyName() {
        return enemyName;
    }

    public String getEnemyDescription() {
        return enemyDescription;
    }

    public double getEnemyDamage() {
        return enemyDamage;
    }

    public Weapon getEnemyWeapon() {
        return weapon;
    }

    public double getEnemyHP() {
        return enemyHP;
    }

    @Override
    public String toString() {
        return enemyName + ", "+enemyDescription;
    }
}






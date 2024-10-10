public class Weapon extends Item {

    private int attackPoints;
    private double remainingUses;

    public Weapon(String itemName, String itemDescription, int attackPoints, double remainingUses) {
        super(itemName, itemDescription);
        this.attackPoints = attackPoints;
        this.remainingUses = remainingUses;
    }

    public double getAttackPoints() {
        return attackPoints;
    }

    public double getRemainingUses() {
        return remainingUses;
    }

    public void useWeapon() {
        if (remainingUses > 0) {
            remainingUses --; // denne kode sørger for at remaininguses går ned med 1 pr. gang der bliver brugt noget ammo.
        } else {
            System.out.println("No remaining ammunition for " + getItemName());
        }
    }
}


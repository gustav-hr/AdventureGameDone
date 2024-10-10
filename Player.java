import java.lang.reflect.Array;
import java.util.ArrayList;

public class Player {

    private int healthPoints;
    private Room currentRoom;
    private ArrayList<Item> itemArrayList;
    private ArrayList<Item> equipped;
    private Weapon currentweapon;

    // Herunder står alt som har noget med HP at gøre.
    @Override
    public String toString() {
        return "Health " + healthPoints;
    }

    public void addHealthPoints(int healthPoints) {
        this.healthPoints += healthPoints;
    }

    public int getHealthPoints() {
        return healthPoints;
    }


    public Player(Room firstRoom, int healthPoints) {
        this.healthPoints = healthPoints;
        this.currentRoom = firstRoom;
        this.itemArrayList = new ArrayList<>();
        this.equipped = new ArrayList<>(); // Denne linje laver en ny arraylist, der sørger for at når man skriver "equip <weapon>" så får man at vide at man equipper sit våben.
    }

    // Herunder står alt der har noget med spillerens lokation at gøre, og om det er muligt at bevæge sig den vej.

    public void getS() {
        if (currentRoom.getS() != null) {
            currentRoom = currentRoom.getS();
        } else {
            System.out.println("Unfortunately, a wall is blocking the way.");
        }
    }

    public void getN() {
        if (currentRoom.getN() != null) {
            currentRoom = currentRoom.getN();
        } else {
            System.out.println("Unfortunately, a wall is blocking the way.");
        }
    }


    public void getE() {
        if (currentRoom.getE() != null) {
            currentRoom = currentRoom.getE();
        } else {
            System.out.println("Unfortunately, a wall is blocking the way.");
        }

    }

    public void getW() {
        if (currentRoom.getW() != null) {
            currentRoom = currentRoom.getW();
        } else {
            System.out.println("Unfortunately, a wall is blocking the way.");

        }
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void addItemToInventory(Item item) {
        itemArrayList.add(item);
    }

    public ArrayList<Item> getItemArrayList() {
        return itemArrayList;
    }
// ______________________ HER ER WEAPONS - DELEN MED AT SKULE EQUIPPE ET VÅBEN.

    public void addWeaponToEquipped(Item equippedWeapon) {
        equipped.add(equippedWeapon);
    }

    public ArrayList getEquippedWeapon() {
        return equipped;
    }

    public Weapon getCurrentweapon() {
        return (Weapon) equipped.get(0);
    }
}
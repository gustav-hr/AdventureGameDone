public class Map {

    private Room current;

    public Room getRoom() {
        return current;
    }

    public Map() {
        this.createMap();
    }

    // Herinde ligger hele mit map. Både med hvad der er i lokalerne, hvilke veje man kan gå etc.
    public void createMap() {

        Room room1 = new Room(1, "There are 2 doors. Take a look around to see if there is anything you can use on your journey to room 5.");
        Room room2 = new Room(2, "There are 2 doors in this room.  ");
        Room room3 = new Room(3, "There are 2 doors, and a candle lights up the room. ");
        Room room4 = new Room(4, "There are 2 doors ");
        Room room5 = new Room(5, "Congrats! You've reached room 5.");
        Room room6 = new Room(6, "There are 2 doors. ");
        Room room7 = new Room(7, "There are 2 doors. ");
        Room room8 = new Room(8, "There are 3 doors. ");
        Room room9 = new Room(9, "There are 2 doors.");

        // Her laver jeg nogle items.
        Item flashlight = new Item("Flashlight", "A bright lamp");

        // Her tildeler jeg mine items et rum de skal være i.
        room1.addItem(flashlight);

        // Her laver jeg food, som man kan samle op og indtage.
        Food coffee = new Food("Coffee", "A scolden hot coffee", 10);
        Food polo = new Food("Polo", "A pack of Polo mints", 15);
        Food cigarette = new Food("Cigarette", "A Marlboro gold cigarette", -15);

        //Her tildeler jeg mit food et rum som det skal befinde sig i.

        room2.addItem(polo);
        room3.addItem(cigarette);
        room1.addItem(coffee);

        // Her laver jeg våben.
        MeleeWeapon knife = new MeleeWeapon("Knife", "A rusty knife that deals 25 damage", 25, Double.POSITIVE_INFINITY);
        RangedWeapon gun = new RangedWeapon("Gun", "A shiny handgun with 6 bullets that deals 50 damage", 50, 6);
        RangedWeapon shotgun = new RangedWeapon("Shotgun", "A double-barrel shotgun", 150, 2);

        // Her tildeler jeg mine våben hvilket rum de skal være i.
        room4.addItem(knife);
        room2.addItem(gun);
        room9.addItem(shotgun);

        // Her laver jeg weapons som enemies kan bruge og senere hen droppe.
        Weapon Axe = new Weapon("Axe", "Fyr'alath the Dreamrender", 50 ,Double.POSITIVE_INFINITY);

        // Her laver jeg enemies som jeg tildeler hhv. navn, våben, damage og HP.
        Enemy Fyrakk = new Enemy("Fyrakk","the final boss ", Axe, 120, 50);

        // Her adder jeg enemies til et rum.
        room8.addEnemy(Fyrakk);

        // Her er alle mine rooms, der gør at jeg kan bevæge mig rundt i spillet. Skal ikke pilles ved.
        room1.setN(null);
        room1.setE(room2);
        room1.setS(room4);
        room1.setW(null);

        room2.setN(null);
        room2.setE(room3);
        room2.setS(null);
        room2.setW(room1);

        room3.setN(null);
        room3.setE(null);
        room3.setS(room6);
        room3.setW(room2);

        room4.setN(room1);
        room4.setE(null);
        room4.setS(room7);
        room4.setW(null);

        room5.setN(null);
        room5.setE(null);
        room5.setS(room8);
        room5.setW(null);

        room6.setN(room3);
        room6.setE(null);
        room6.setS(room9);
        room6.setW(null);

        room7.setN(room4);
        room7.setE(room8);
        room7.setS(null);
        room7.setW(null);

        room8.setN(room5);
        room8.setE(room9);
        room8.setS(null);
        room8.setW(room7);

        room9.setN(room6);
        room9.setE(null);
        room9.setS(null);
        room9.setW(room8);

        current = room1;

    }

}

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class UserInterface {

    public void start() {

        Adventure adventure = new Adventure();
        System.out.println("Hello and welcome to the text adventure game! Please begin the game by giving your character a name:");
        Scanner scanner = new Scanner(System.in);
        String playerName;
        String name = scanner.nextLine();
        playerName = name;
        System.out.println("Your characters name is " + playerName + ". The objective for " + playerName + " is to reach room 5 without losing 100 HP.");
        System.out.println("To move around, use commands: 'go north', 'go east', go west', 'go south'.");
        System.out.println("You can at all times write 'help', 'look', 'exit', or 'backpack'.");
        System.out.println("If there are any items or consumables in the room your character is in, you can always pick them up and drop them whereever you like. For items, simply write: 'take <item>' or 'drop <item>'. For consumables, write 'eat <consumable> ' or 'drop <consumable>");
        System.out.println("After you have taken a weapon, you need to write 'equip <weapon name>' to equip it to your character.");
        System.out.println(playerName + " starts in room 1. Tip: Take a look around to see if there is anything useful for " + playerName + " to begin the game.");

        Scanner input = new Scanner(System.in);
        String useraction = "";

        while (!useraction.equalsIgnoreCase("exit")) {
            useraction = input.nextLine();
            String[] word = useraction.split(" ");
            String action = word[0];

            switch (action) {

                case "health", "hp", "HP" -> {
                    System.out.println(adventure.getPlayer().toString());
                }
                // Her gør jeg så man kan "consume" hvad end food man har.
                case "consume" -> {
                    if (word.length < 2) {
                        System.out.println("Consume what?");
                    } else {
                        String foodName = word[1].trim();
                        Food foodToConsume = null;

                        // Dene kode nedenunder sørger for at tjekke backpack og se om der er noget food i, som man kan consume.
                        for (Item item : adventure.getPlayer().getItemArrayList()) {
                            if (item instanceof Food && item.getItemName().equalsIgnoreCase((foodName))) {
                                foodToConsume = (Food) item;
                                break;
                            }
                        }
                        if (foodToConsume != null) {
                            adventure.getPlayer().addHealthPoints((int) foodToConsume.getHealthPoints()); // Denne linje sørger for at plusse det man indtager med ens HP.
                            adventure.getPlayer().getItemArrayList().remove(foodToConsume); // Denne linje fjerner den mad man har indtaget fra ens inventory, hvis man vælger at sige "consume".
                            System.out.println(playerName + " consumed " + foodName + ".");
                            System.out.println(playerName + " is now at " + adventure.getPlayer().getHealthPoints() + " HP");
                        } else if ((adventure.getPlayer().getHealthPoints() <= 0)) {
                            System.out.println("You died - game over.");
                            System.exit(0);
                        } else {
                            System.out.println("You don't have that consumable.");
                        }
                    }
                }
                // Her laver jeg hvordan man bruger sit weapon til at angribe mulige fjender.

                case "attack" -> {

                    if (word.length < 2) {
                        System.out.println("Attack what?");
                    } else {

                        if (!adventure.getPlayer().getCurrentRoom().getEnemies().isEmpty()) { //Denne kode sørger for at det hele kører, hvis der er enemies i rummet

                            if (word[1].equalsIgnoreCase(adventure.getPlayer().getCurrentRoom().getEnemy().getEnemyName())) {  // hvis useren indtaster et navnet rigtig på enemien i rummet

                                if (!adventure.getPlayer().getEquippedWeapon().isEmpty()) {

                                    Weapon currentWeapon = adventure.getPlayer().getCurrentweapon();

                                    if (currentWeapon.getRemainingUses() > 0) {
                                        // Denne kode OVENOVER sørger for at man kun kan angribe hvis ens våben har over 0 "uses" tilbage. Dette gælder både melee og ranged.
                                        // Denne kode OVENOVER henter den første "enemy" i rummet, så vi kan angribe vedkommende.
                                        adventure.getPlayer().getCurrentRoom().getEnemy().takeDamage(currentWeapon.getAttackPoints());
                                        // Denne linje OVENOVER tjekker hvor meget ens våben giver i skade, og fjerner dét fra hvor meget HP ens fjende har.
                                        currentWeapon.useWeapon();
                                        // Denne kode OVENOVER går tilbage til weapon-klassen hvor man siger UseWeapon, og den går derfor automatisk ned med én pr gang man affyrer noget ranged.
                                        System.out.println(playerName + " attacked " + adventure.getPlayer().getCurrentRoom().getEnemies() + " with " + currentWeapon.getItemName() + ". There are currently: " + currentWeapon.getRemainingUses() + " ammunition left on " + currentWeapon);
                                        System.out.println(adventure.getPlayer().getCurrentRoom().getEnemy().getEnemyName() + " now has " + adventure.getPlayer().getCurrentRoom().getEnemy().getEnemyHP() + " HP left.");
                                        if (adventure.getPlayer().getCurrentRoom().getEnemy().getEnemyHP() < 1) {
                                            System.out.println(adventure.getPlayer().getCurrentRoom().getEnemy().getEnemyName() + " has been defeated. Good job! ");
                                            // Denne kode nedeunder sørger for at "enemyweapon" kan droppes.
                                            Weapon enemyWeapon = adventure.getPlayer().getCurrentRoom().getEnemy().getEnemyWeapon();
                                            if (enemyWeapon != null) {
                                                adventure.getPlayer().getCurrentRoom().addItem(enemyWeapon);
                                                System.out.println(adventure.getPlayer().getCurrentRoom().getEnemy().getEnemyName() + " dropped " + enemyWeapon.getItemName() + ".");

                                            }
                                            adventure.getPlayer().getCurrentRoom().getEnemies().remove(adventure.getPlayer().getCurrentRoom().getEnemy());
                                            // Denne kode OVENOVER sørger for at fjerne enemy, hvis enemy har under 1 HP tilbage.
                                            //Denne kode under fortæller at man dør hvis man ryger under 0 hp.

                                        } else {
                                            // I denne kode giver min enemy mig skade igen, når jeg angriber.
                                            adventure.getPlayer().addHealthPoints((int) -adventure.getPlayer().getCurrentRoom().getEnemy().getEnemyDamage());
                                            System.out.println(adventure.getPlayer().getCurrentRoom().getEnemy().getEnemyName() + " strikes " + playerName + " for " + adventure.getPlayer().getCurrentRoom().getEnemy().getEnemyDamage() + "." + playerName + " now has " + adventure.getPlayer().getHealthPoints() + " HP left.");
                                        }
                                        if (adventure.getPlayer().getHealthPoints() <= 0) {
                                            System.out.println("You have been defeated by " + adventure.getPlayer().getCurrentRoom().getEnemy().getEnemyName() + ". Game over.");
                                            System.exit(0);
                                        }

                                    } else {
                                        System.out.println("There are no ammo left");
                                    }
                                } else {
                                    System.out.println("You cant attack before you equip a weapon");
                                }
                            } else {
                                System.out.println("There are no such enemies to attack here");
                            }

                        } else { // Hvis ikke der er enemies i rummet, vil denne kode komme:
                            System.out.println("There are no enemies in this room");
                        }
                    }
                }

                case "ammunition", "ammo", "Ammunition" -> {
                    System.out.println("There is: " + adventure.getPlayer().getCurrentweapon().getRemainingUses() + " ammo left in " + adventure.getPlayer().getCurrentweapon());
                }

                // Her laver jeg at man kan equippe et våben. Når man skriver "attack"(ovenover) så bruger man det våben man har equippet.

                case "equip" -> {
                    if (word.length < 2) {
                        System.out.println("Equip what?");
                    } else {
                        String itemName = word[1].trim();
                        Item itemToEquip = null;

                        // Tjekker om våbenet findes i inventory
                        for (Item item : adventure.getPlayer().getItemArrayList()) {
                            if (item.getItemName().equalsIgnoreCase(itemName)) {
                                itemToEquip = item;
                                break;
                            }
                        }
                        // Denne kode tjekker om jeg har item i mit inventory. Hvis ikke, får jeg outprint herunder:
                        if (itemToEquip == null) {
                            System.out.println("You don't have that item in your inventory.");
                        }
                        // Her tjekker jeg om det jeg prøver at equippe er et våben, og outpriner hvis det ikke er et våben.
                        else if (!(itemToEquip instanceof Weapon)) {
                            System.out.println(itemToEquip.getItemName() + " is not a weapon.");
                        }
                        // Her tjekker min kode igen om det er et våben jeg forsøger at equippe, og hvis det er et våben, equipper den det som våben.
                        else {
                            Weapon weaponChosen = (Weapon) itemToEquip;
                            if (!adventure.getPlayer().getEquippedWeapon().isEmpty()) {
                                adventure.getPlayer().getEquippedWeapon().remove(0);
                                adventure.getPlayer().addWeaponToEquipped(weaponChosen);
                                System.out.println(playerName + " equipped " + weaponChosen.getItemName());
                            } else {
                                adventure.getPlayer().addWeaponToEquipped(weaponChosen);
                                System.out.println(playerName + " equipped " + weaponChosen.getItemName());
                            }
                        }
                    }
                }
                case "equipped" -> {
                    System.out.println(adventure.getPlayer().getEquippedWeapon() + " is curently equipped.");
                }

                case "take", "Take", "tAke", "TAKE" -> {
                    if (word.length < 2) {
                        System.out.println("Take what?"); // Hvis man bare skriver "take" uden noget efterfølgende, genkender koden at man vil samle noget op, men ikke hvad man vil samle op.
                    } else {
                        String itemName = word[1].trim(); // dette fjerner eventuelle unødvendige mellemrum.
                        Item item = adventure.getPlayer().getCurrentRoom().takeItem(itemName); // Her finder vi item og fjerner det igen fra Listen, hvis man tager det op.
                        if (item != null) {
                            System.out.println(playerName + " took " + item.getItemName());
                            adventure.getPlayer().addItemToInventory(item);
                        } else {
                            System.out.println("No such item in this room. ");
                        }
                    }
                }
                case "drop" -> {
                    if (word.length < 2) { // Ligesom før med at skulle "take", så er det bare drop.
                        System.out.println("Drop what?");
                    } else {
                        String itemName = word[1].trim();
                        Item itemToDrop = null;
                        for (Item item : adventure.getPlayer().getItemArrayList()) {
                            if (item.getItemName().equalsIgnoreCase(itemName)) {
                                itemToDrop = item;
                                break;
                            }
                        }
                        if (itemToDrop != null) {
                            adventure.getPlayer().getCurrentRoom().dropItem(itemToDrop);
                            adventure.getPlayer().getItemArrayList().remove(itemToDrop);
                            adventure.getPlayer().getEquippedWeapon().remove(0);
                            System.out.println("You dropped " + itemName + " in room " + adventure.getPlayer().getCurrentRoom().getRoomNumber() + ".");
                        } else {
                            System.out.println("You don't have that item.");
                        }
                    }
                }
                case "go" -> {
                    if (word.length < 2) {
                        System.out.println("Go where?");
                    } else {

                        if (word[1].equalsIgnoreCase("north") || word[1].equalsIgnoreCase("n")) {
                            adventure.getPlayer().getN();
                            System.out.println(playerName + " is now in room " + adventure.getPlayer().getCurrentRoom().getRoomNumber() + ".");
                            System.out.println(adventure.getPlayer().getCurrentRoom().getRoomDescription());
                        } else if (word[1].equalsIgnoreCase("south") || word[1].equalsIgnoreCase("s")) {
                            adventure.getPlayer().getS();
                            System.out.println(playerName + " is now in room " + adventure.getPlayer().getCurrentRoom().getRoomNumber() + ".");
                            System.out.println(adventure.getPlayer().getCurrentRoom().getRoomDescription());
                        } else if (word[1].equalsIgnoreCase("east") || word[1].equalsIgnoreCase("e")) {
                            adventure.getPlayer().getE();
                            System.out.println(playerName + " is now in room " + adventure.getPlayer().getCurrentRoom().getRoomNumber() + ".");
                            System.out.println(adventure.getPlayer().getCurrentRoom().getRoomDescription());
                        } else if (word[1].equalsIgnoreCase("west") || word[1].equalsIgnoreCase("w")) {
                            adventure.getPlayer().getW();
                            System.out.println(playerName + " is now in room " + adventure.getPlayer().getCurrentRoom().getRoomNumber() + ".");
                            System.out.println(adventure.getPlayer().getCurrentRoom().getRoomDescription());
                        }
                    }
                }

                case "inventory", "i", "backpack", "b", "inv" -> {
                    System.out.println(playerName + " has " + adventure.getPlayer().getItemArrayList() + " items in the backpack.");
                }


                case "look", "l", "Look" -> {
                    List<Item> items = adventure.getPlayer().getCurrentRoom().getItems();
                    if (items.isEmpty()) {
                        System.out.println("There is nothing useful for " + playerName + " to pick up in this room.");
                    } else {
                        System.out.println("You see the following items in the current room:");
                        for (Item item : items) {
                            System.out.println(item);

                        }
                    }
                    List<Enemy> enemies = adventure.getPlayer().getCurrentRoom().getEnemies();
                    if (enemies.isEmpty()) {
                        System.out.println();
                    } else {
                        System.out.println(enemies + " is in the room! Attack the enemy to ensure your survival.");
                    }
                }

                case "help" -> {
                    System.out.println("To move around, write either 'go north', 'go east', go west', 'go south'.");
                    System.out.println("You can at all times write 'help', 'look' or 'exit'.");
                }

                default -> {
                    System.out.println("Unknown command");
                }

            }


        }
    }

}

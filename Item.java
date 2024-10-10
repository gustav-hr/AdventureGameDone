public class Item {
    private String itemName;
    private String itemDescription;

    public Item(String name, String itemDescription) {
        this.itemName = name;
        this.itemDescription = itemDescription;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    @Override
    public String toString() {
        return itemName + ": " + itemDescription;

    }
}

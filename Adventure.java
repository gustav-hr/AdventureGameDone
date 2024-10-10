import java.util.Locale;
import java.util.Optional;
import java.util.Scanner;

public class Adventure {

    private Map map;
    private Player player;

    public Adventure() {
        map = new Map();
        player = new Player(map.getRoom(), 100);
    }
    public Player getPlayer() {
        return player;
    }


}

package  baby_boomers;
import java.util.HashMap;

/**
 * @author sabrina.diehl
 */

public class Room {
    private String description;
    private HashMap<String, Room> exits;
    Item item;
    


    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<>();
        this.item = null;
    }
    
    public Room(String description, Item item)
    {
        this(description);
        this.item = item;    
    }
    public Item getItem() {
        return item;
    }
    
    public void setItem(Item item) {
        this.item = item;
    }

    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }

    public Room getExit(String direction)
    {    
        return exits.get(direction);
    }
    
    public String getExitString()
    {
        String exitString = "Saídas:";
        for(String exit : exits.keySet()) {
            exitString += " " + exit;
        }
        return exitString;
    }
    
    public String getLongDescription()
    {
        String itemStr = (item != null) 
                ? "Que contém " + item.getLongDescription() + ".\n"
                : "";
        
        return "Você está " + description + ".\n" +
                itemStr + 
                getExitString();
    }
}


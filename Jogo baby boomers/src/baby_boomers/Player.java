package  baby_boomers;
import java.util.ArrayList;
import java.util.Stack;

/**
 * @author sabrina.diehl
 */

public class Player {
    private int maxWeight;
    private int currentWeight;
    private Room currentRoom;
    Stack<Room> previousRooms;
    ArrayList< Item > itensPlayer = new ArrayList< Item >();

    public Player(int pesoMax){
        this.maxWeight = pesoMax;  
        previousRooms = new Stack<>();
        itensPlayer = new ArrayList<Item>();
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }
        
    public Room getCurrentRoom() {
        return currentRoom;
    }
    
    public void goRoom(String direction) {
      Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("Não há uma porta!");
        }
        else {
            previousRooms.push(currentRoom);
            currentRoom = nextRoom;
            printLocationInfo();
        }  
    }
    public void returnRoom(Command command) {
        if (previousRooms.empty()) {
            System.out.println("Você já está no início!");
        } else {
            currentRoom = previousRooms.pop();
            printLocationInfo();
        }
    }
    
    public void incrementCurrentWeight(int increment) {
        if (increment <= maxWeight) {
            this.currentWeight = increment;
        }else{
            System.out.println("Essa comida é muito pesada, você não consegue transportar");
        }
    }
    
    public void decrementCurrentWeight(int decrement) {
            this.currentWeight -= decrement; 
    }
           
    public int getCurrentWeight(){
       return currentWeight;
    }
    
    public void setCurrentWeight( int currentWeight ){
       this.currentWeight = currentWeight;
    }
    
    public int getMaxWeight(){
        return maxWeight;
    }
    
    public void addItem(Item item){
       itensPlayer.add(item);   
    }

    public ArrayList<Item> getItensPlayer() {
        return itensPlayer;
    }
    
    public void printLocationInfo()
    {
        System.out.println(getCurrentRoom().getLongDescription());
    }

    public Stack<Room> getPreviousRooms() {
        return previousRooms;
    }

    
}

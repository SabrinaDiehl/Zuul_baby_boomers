package  baby_boomers;

/**
 * @author sabrina.diehl
 */

public class Item {
    private String description;
    private int weightItem;
    private String nome;
    
    public Item(String nome,String description, int weight) {
        this.nome = nome;
        this.description = description;
        this.weightItem = weight;
    }
    
    public String getNome() {
        return nome;
    }

    public String getDescription() {
        return description;
    }
    
    public int getWeight(){
        return weightItem;
    }
    
    public String getLongDescription() {
        return nome + description + " e pesa " + weightItem;
    }
    
    public boolean canBePickedUp(Player p){
        int pesoA = p.getCurrentWeight();
        int weight = (pesoA + p.getCurrentRoom().getItem().getWeight());
        if(weight <= p.getMaxWeight()&& weight >=0 ){
            p.incrementCurrentWeight(weight);
            return true;
        }
        else{
            return false; 
        }
    }
}


package es.pagoru.HiddenWalls2D;

/**
 * Created by pablo on 20/9/16.
 */
public class Player {

    private String symbol;
    private Vector2Di positon;
    private int maxHearts;
    private int hearts;

    public Player(String symbol){
        this.symbol = symbol.substring(0, 1);
        this.positon = new Vector2Di(0, 0);
        addHearts(5);
    }

    public void addPosition(Vector2Di positon){
        this.positon.add(positon);
    }

    public void setPosition(Vector2Di positon){
        this.positon = positon;
    }

    public Vector2Di getPosition(){
        return positon;
    }

    public String getSymbol(){
        return symbol;
    }

    public int getHearts(){
        if(hearts > maxHearts){
            this.maxHearts = hearts;
        }
        return hearts;
    }

    public String getPrintableHearts(){
        String pHearts = "[";
        for(int h = 0; h < maxHearts; h++){
            if(h != 0){
                pHearts += " ";
            }
        }
        return  pHearts;
    }

    public void addHearts(int hearts){
        this.hearts += hearts;
    }

}

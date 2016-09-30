package es.pagoru.HiddenWalls2D;

/**
 * Created by pablo on 20/9/16.
 */
public class Player {

    /**
     * Simbol que es mostrará de l'usuari
     */
    private String symbol;

    /**
     * Posició de l'usuari
     */
    private Vector2Di positon;

    /**
     * Vida actual de l'usuari
     * max 5
     */
    private int hearts;

    /**
     * Si l'usuari te la piqueta
     */
    private boolean pickaxe;

    public Player(String symbol){
        this.symbol = symbol.substring(0, 1);
        this.positon = new Vector2Di(0, 0);
        addHearts(5);
    }

    /**
     * Suma la posició especificada a l'usuari
     * @param positon
     */
    public void addPosition(Vector2Di positon){
        this.positon.add(positon);
    }

    /**
     * Retorna la posició actual de l'usuari
     * @return
     */
    public Vector2Di getPosition(){
        return positon;
    }

    /**
     * Retorna el simbol del usuari
     * @return
     */
    public String getSymbol(){
        return symbol;
    }

    /**
     * Retorna les vidas que li quedan a l'usuari
     * @return
     */
    public int getHearts(){
        return hearts;
    }

    /**
     * Retorna les vides de l'usuari de forma que es puguin mostrar
     * [❤ ❤ ❤ ❤ ❤] ... [❤ ❤ ❤ ❤ ☠]
     * @return
     */
    public String getPrintableHearts(){
        String pHearts = "[";
        for(int h = 0; h < 5; h++){
            if(h != 0){
                pHearts += " ";
            }
            if(getHearts() > h){
                pHearts += "❤";
            } else {
                pHearts += "☠";
            }
        }
        return pHearts + "]";
    }

    /**
     * Suma les vidas especificades a l'usuari
     * @param hearts
     */
    public void addHearts(int hearts){
        this.hearts += hearts;
        if(this.hearts > 5){
            this.hearts = 5;
        }
    }

    /**
     * Afegeix a l'usuari la piqueta
     */
    public void addPickaxe(){
        this.pickaxe = true;
    }

    /**
     * Comproba si l'usuari te la piqueta
     * @return
     */
    public boolean hasPickaxe(){
        return pickaxe;
    }

}

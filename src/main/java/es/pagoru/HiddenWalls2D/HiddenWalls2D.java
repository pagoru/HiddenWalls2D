package es.pagoru.HiddenWalls2D;

/**
 * Created by pablo on 20/9/16.
 */
public class HiddenWalls2D {

    public static void main(String[] args){

        Map map = new Map(Map.DEFAULT_MAP);

        for(int y = 0; y < Map.HEIGHT; y++){
            for(int x = 0; x < Map.WIDTH; x++){
                System.out.print(map.getPosition(new Vector2Di(x, y)).getSymbol() + " ");
            }
            System.out.println();
        }
        System.out.print("[❤ ❤ ❤ ❤ ❤]");

    }

}

package es.pagoru.HiddenWalls2D;

/**
 * Created by pablo on 20/9/16.
 */
public class Map {

    public static final int WIDTH = 30;
    public static final int HEIGHT = 10;

    public static final int[] DEFAULT_MAP ={
            2,2,2,2,1,2,1,1,1,2,2,2,5,2,2,2,1,2,2,2,2,1,2,2,2,2,2,2,2,2,
            2,2,2,1,2,2,1,2,2,2,1,2,1,1,1,2,1,2,4,4,2,2,2,1,2,1,1,1,1,1,
            2,1,1,1,2,1,1,2,1,2,1,2,2,2,1,2,1,1,2,2,2,1,2,1,2,2,2,2,2,2,
            2,2,1,2,2,2,2,2,1,1,1,2,1,2,1,2,2,2,2,4,2,1,1,1,5,2,1,1,4,1,
            1,1,1,2,1,1,1,1,1,2,1,2,1,1,1,4,1,1,2,2,2,2,2,1,1,2,1,2,2,2,
            4,1,2,2,2,2,2,2,1,2,1,2,1,2,1,2,1,1,2,2,2,1,2,1,2,1,1,1,1,2,
            2,1,2,1,1,1,1,2,2,5,1,2,1,2,1,2,2,2,2,2,2,1,2,2,2,1,2,2,2,2,
            2,1,2,2,2,1,2,2,1,1,1,2,2,2,1,2,1,1,2,1,1,1,2,1,2,1,4,1,1,2,
            2,1,2,1,4,1,2,1,1,4,1,1,1,1,1,2,1,2,2,1,4,2,2,1,2,1,2,1,2,2,
            2,2,2,1,2,1,2,2,2,2,2,2,2,3,2,2,1,2,2,1,2,1,5,1,2,1,2,1,0,2
    };

    public enum MapType {
        EXIT("E"),
        WALL("#"),
        ROAD("-"),
        PICKAXE("?"),
        BOMBS("@"),
        HEARTS("H"),
        EMPTY(" ");

        private String symbol;

        MapType(String symbol){
            this.symbol = symbol;
        }

        public String getSymbol(){
            return symbol;
        }
    }

    private int[] map = new int[WIDTH * HEIGHT];
    /**
     * 0 - No visitat
     * 1 - Visitat
     */
    private boolean[] trail = new boolean[WIDTH * HEIGHT];
    private Player player = new Player("P");

    public Map(int[] map){
        this.map = map;
        player.setPosition(new Vector2Di(1, 0));
        trail[5] = true;
    }

    /**
     * Retorna el mapa
     * @return
     */
    public int[] getMap(){
        return map;
    }

    public void addPosition(Vector2Di position, MapType type){
        if(position.getX() > WIDTH || position.getY() > HEIGHT){
            return ;
        }
        this.map[(position.getY() * WIDTH) + position.getX()] = type.ordinal();
    }

    public MapType getPosition(Vector2Di position){
        if(position.getX() > WIDTH || position.getY() > HEIGHT){
            return MapType.WALL;
        }
        if(!canPlayerSeePosition(position)){
            return MapType.EMPTY;
        }
        return MapType.values()[this.map[(position.getY() * WIDTH) + position.getX()]];
    }

    private boolean getTrail(Vector2Di position){
        return trail[(position.getY() * WIDTH) + position.getX()];
    }

    public Player getPlayer(){
        return player;
    }

    public boolean canPlayerSeePosition(Vector2Di position){
        for(int y = -1; y <= 1; y++){
            for(int x = -1; x <= 1; x++){

                Vector2Di pos = position.add(new Vector2Di(x, y));

                if(pos.getX() >= 0 && pos.getX() < WIDTH
                        && pos.getY() >= 0 && pos.getY() < HEIGHT){
                    if(getTrail(pos)){
                        return true;
                    }
                }

            }
        }
        return true;
    }

}

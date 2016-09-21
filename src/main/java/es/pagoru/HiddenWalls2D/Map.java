package es.pagoru.HiddenWalls2D;

/**
 * Created by pablo on 20/9/16.
 */
public class Map {

    public static final int WIDTH = 30;
    public static final int HEIGHT = 10;

    public static final int[] DEFAULT_MAP ={
            2,2,2,2,1,2,1,1,1,2,2,2,5,2,2,2,1,2,2,2,2,1,2,2,2,2,2,2,2,2,
            2,2,2,2,2,2,1,2,2,2,1,2,1,1,1,2,1,2,4,4,2,2,2,1,2,1,1,1,1,1,
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
        EXIT("▽"),
        WALL("▩"),
        ROAD("▢"),
        PICKAXE("ψ"),
        BOMB("◉"),
        HEARTS("❤"),
        EMPTY(" "),
        PLAYER("P"),
        DESTROYED_WALL("▤");

        private String symbol;

        MapType(String symbol){
            this.symbol = symbol;
        }

        public String getSymbol(){
            return symbol;
        }

        public void setSymbol(String symbol){
            this.symbol = symbol;
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
        addPlayerPosition(new Vector2Di(0, 0));
    }

    /**
     * Retorna el mapa
     * @return
     */
    public int[] getMap(){
        return map;
    }

    public void destroyWall(Vector2Di position){
        if(position.getX() >= WIDTH || position.getX() < 0
                || position.getY() >= HEIGHT || position.getY() < 0){
            return;
        }
        this.map[(position.getY() * WIDTH) + position.getX()] = MapType.DESTROYED_WALL.ordinal();
    }

    public void setPosition(Vector2Di position, MapType type){
        if(position.getX() >= WIDTH || position.getX() < 0
                || position.getY() >= HEIGHT || position.getY() < 0){
            return;
        }
        this.map[(position.getY() * WIDTH) + position.getX()] = type.ordinal();
    }

    public MapType getPrintablePosition(Vector2Di position){
        if(getPlayer().getPosition().equals(position)){
            MapType playerMapType = MapType.PLAYER;
            playerMapType.setSymbol(getPlayer().getSymbol());
            return playerMapType;
        }
        MapType type = getPosition(position);
        if(!canPlayerSeePosition(position) && (type.equals(MapType.WALL) || type.equals(MapType.ROAD))){
            return MapType.EMPTY;
        }
        return MapType.values()[this.map[(position.getY() * WIDTH) + position.getX()]];
    }

    public MapType getPosition(Vector2Di position){
        if(position.getX() >= WIDTH || position.getX() < 0
                || position.getY() >= HEIGHT || position.getY() < 0){
            return MapType.WALL;
        }
        if(getPlayer().getPosition().equals(position)){
            MapType playerMapType = MapType.PLAYER;
            playerMapType.setSymbol(getPlayer().getSymbol());
            return playerMapType;
        }
        return MapType.values()[this.map[(position.getY() * WIDTH) + position.getX()]];
    }

    private boolean getTrail(Vector2Di position){
        return trail[(position.getY() * WIDTH) + position.getX()];
    }

    public void addTrail(Vector2Di position) {
        if(position.getX() >= WIDTH || position.getX() < 0
                || position.getY() >= HEIGHT || position.getY() < 0){
            return;
        }
        trail[(position.getY() * WIDTH) + position.getX()] = true;
    }

    public void addPlayerPosition(Vector2Di position){
        getPlayer().addPosition(position);
        addTrail(getPlayer().getPosition());
    }

    public Player getPlayer(){
        return player;
    }

    public boolean canPlayerSeePosition(Vector2Di position){
        return getTrail(position);
    }

}

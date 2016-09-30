package es.pagoru.HiddenWalls2D;

/**
 * Created by pablo on 20/9/16.
 */
public class Map {

    /**
     * Altura i amplada del mapa
     */
    public static final int WIDTH = 30;
    public static final int HEIGHT = 10;

    /**
     * Posició de la sortida
     */
    public static final Vector2Di EXIT = new Vector2Di(28, 9);

    /**
     * Mapa per defecte del joc
     */
    public static final int[] DEFAULT_MAP ={
            2,0,2,2,1,2,1,1,1,2,2,2,5,2,2,2,1,2,2,2,2,1,2,2,2,2,2,2,2,2,
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

    /**
     * Objectes posibles en el mapa
     */
    public enum MapType {
        EXIT("▽"),
        WALL("▩"),
        ROAD("▢"),
        PICKAXE("ψ"),
        BOMB("◉"),
        HEARTS("❤"),
        EMPTY(" "),
        PLAYER("P"),
        DESTROYED_WALL("▤"),
        DEAD("☠");

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
        setPosition(EXIT, MapType.EXIT);
        addPlayerPosition(new Vector2Di(0, 0));
    }

    /**
     * Destrueix la pared en la posició especificada
     * @param position
     */
    public void destroyWall(Vector2Di position){
        if(position.getX() >= WIDTH || position.getX() < 0
                || position.getY() >= HEIGHT || position.getY() < 0){
            return;
        }
        this.map[(position.getY() * WIDTH) + position.getX()] = MapType.DESTROYED_WALL.ordinal();
    }

    /**
     * Estableix un objecte en la posició especificada del mapa
     * @param position
     * @param type
     */
    public void setPosition(Vector2Di position, MapType type){
        if(position.getX() >= WIDTH || position.getX() < 0
                || position.getY() >= HEIGHT || position.getY() < 0){
            return;
        }
        this.map[(position.getY() * WIDTH) + position.getX()] = type.ordinal();
    }

    /**
     * Retorna l'objecte en la posició especificada que pot veure l'usuari
     * @param position
     * @return
     */
    public MapType getPrintablePosition(Vector2Di position){
        if(getPlayer().getPosition().equals(position)){
            MapType playerMapType = MapType.PLAYER;
            playerMapType.setSymbol(getPlayer().getSymbol());
            return playerMapType;
        }
        if(!canPlayerSeePosition(position)){
            return MapType.EMPTY;
        }
        return MapType.values()[this.map[(position.getY() * WIDTH) + position.getX()]];
    }

    /**
     * Retorna l'objecte en la posició especificada
     * @param position
     * @return
     */
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

    /**
     * Retorna si l'usuari pot veure la posició especificada
     * @param position
     * @return
     */
    private boolean getTrail(Vector2Di position){
        return trail[(position.getY() * WIDTH) + position.getX()];
    }

    /**
     * L'usuari podrá veure la posició especificada
     * @param position
     */
    public void addTrail(Vector2Di position) {
        if(position.getX() >= WIDTH || position.getX() < 0
                || position.getY() >= HEIGHT || position.getY() < 0){
            return;
        }
        trail[(position.getY() * WIDTH) + position.getX()] = true;
    }

    /**
     * L'usuari deixará el rastre de la posició i avançará a la posició especificada
     * @param position
     */
    public void addPlayerPosition(Vector2Di position){
        getPlayer().addPosition(position);
        addTrail(getPlayer().getPosition());
    }

    /**
     * Retorna l'usuari
     * @return
     */
    public Player getPlayer(){
        return player;
    }

    /**
     * Segons la posició especificada, es podrá saber si l'usuari pot veurela
     * @param position
     * @return
     */
    public boolean canPlayerSeePosition(Vector2Di position){
        return getTrail(position);
    }

}

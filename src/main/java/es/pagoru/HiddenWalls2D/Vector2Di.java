package es.pagoru.HiddenWalls2D;

/**
 * Created by pablo on 20/9/16.
 */
public class Vector2Di {

    private int x;
    private int y;

    public Vector2Di(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public Vector2Di add(Vector2Di vector){
        this.x += vector.x;
        this.y += vector.y;
        return this;
    }

    public Vector2Di copy(){
        return new Vector2Di(x, y);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Vector2Di){
            Vector2Di vector = (Vector2Di) obj;
            return vector.getX() == getX() && vector.getY() == getY();
        }
        return (this == obj);
    }

}

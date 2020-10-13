package pl.edu.agh.cs;

public class Vector2D {
    final public int x;
    final public int y;

    public Vector2D(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Vector2D(){
        this.x = 0;
        this.y = 0;
    }
    @Override
    public String toString(){
        return String.format("(%d,%d)", this.x, this.y);
    }

    @Override
    public boolean equals(Object other){
        if (other == null)
            return false;
        if (other == this)
            return true;
        if (!(other instanceof Vector2D))
            return false;
        Vector2D p = (Vector2D)other;
        return  ((this.x == p.x) && (this.y == p.y));
    }
    public boolean precedes(Vector2D other){
        return (this.x <= other.x && this.y <= other.y);
    }   //poprzedza

    public boolean follows(Vector2D other){
        return (this.x >= other.x && this.y >= other.y);
    }    //następuje po

    public Vector2D upperRight(Vector2D other){
        return new Vector2D(Math.max(this.x, other.x), Math.max(this.y, other.y));
    }

    public Vector2D lowerLeft(Vector2D other){
        return new Vector2D(Math.min(this.x, other.x), Math.min(this.y, other.y));
    }

    public Vector2D add(Vector2D other){
        return new Vector2D(this.x + other.x, this.y + other.y);
    }

    public Vector2D subtract(Vector2D other){
        return new Vector2D(this.x - other.x, this.y - other.y);
    }

    public Vector2D opposite(){
        return new Vector2D((-1) * this.x, (-1) * this.y);
    }

    @Override
    public int hashCode() {
        int hash = 2137;
        hash += this.x * 31;
        hash += this.y * 17;
        return hash;
    }



}

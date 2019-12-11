package pl.edu.agh.cs;

public class Grass {
    private Vector2D position;

    public Grass(Vector2D position){
        this.position = position;
    }

    public Vector2D getPosition(){
        return this.position;
    }

    public String toString(){
        return "*";
    }

}

package pl.edu.agh.cs;

public class World {
    public static void main(String[] args) {
        WorldMap map = new WorldMap(new Vector2D(5,5));
        map.place(new Animal(map, new Vector2D(2,2)));
        System.out.println(map.drawMap());
    }

}

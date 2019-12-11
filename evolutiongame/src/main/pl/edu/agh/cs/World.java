package pl.edu.agh.cs;

public class World {
    public static void main(String[] args) {

        WorldMap map = new WorldMap(new Vector2D(5,5));
        map.place(new Animal(map, new Vector2D(2,2)));
        map.place(new Animal(map, new Vector2D(1,3)));
        Animal fox = new Animal(map, new Vector2D(4,1));
        map.place(fox);
        System.out.println(map.drawMap());
        fox.turnAround(1);
        fox.move();
        System.out.println(map.drawMap());
    }

}

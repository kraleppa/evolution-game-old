package pl.edu.agh.cs;

public class Simulation {
    WorldMap map;
    int day;

    public Simulation(Vector2D upperRight){
        this.map = new WorldMap(upperRight);
        this.day = 0;
    }

    public Simulation(WorldMap map){
        this.map = map;
        this.day = 0;
    }

    public void nextDay(){
        for (Animal animal : map.animalsList){
            animal.turnAroundAuto();
        }

        for (Animal animal : map.animalsList){
            animal.move();
        }
        day++;
    }


    public void startSimulation(int numberOfDays){
        System.out.println("Day: " + day);
        System.out.println(map.drawMap());
        for (int i = 1; i <= numberOfDays; i++){
            this.nextDay();
            System.out.println("Day: " + day);
            System.out.println(map.drawMap());
        }
    }


}

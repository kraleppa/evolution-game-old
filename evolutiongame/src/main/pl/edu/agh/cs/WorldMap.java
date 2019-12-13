package pl.edu.agh.cs;

import javax.net.ssl.SSLContext;
import javax.sound.midi.SysexMessage;
import java.util.*;

public class WorldMap implements IWorldMap, IPositionChangeObserver{
    private List<Animal> animalsList = new ArrayList<>();
    private Map<Vector2D, HashSet<Animal>> animalsMap = new HashMap<>();        //skonsultować SortedSet
    private Map<Vector2D, Grass> grassMap = new HashMap<>();
    private final Vector2D lowerLeft;
    private final Vector2D upperRight;
    public final Integer width;
    public final Integer height;
    public final Integer startEnergy;
    public final Integer moveEnergy = 2;
    public final Integer plantEnergy = 100;
    public final Double jungleRatio = 0.5;
    public MapVisualizer visualizer;

    public WorldMap(Vector2D upperRight){
        this(upperRight, 10);
    }

    public WorldMap(Vector2D upperRight,Integer startEnergy){
        this.upperRight = upperRight;
        this.lowerLeft = new Vector2D(0,0);
        this.visualizer = new MapVisualizer(this);
        this.height = upperRight.y + 1;
        this.width = upperRight.x + 1;
        this.startEnergy = startEnergy;

    }



    @Override
    public boolean canMoveTo(Vector2D position){
        return true;
    }

    public Vector2D betterPosition(Vector2D position){
        int nx;
        int ny;

        if (position.x < lowerLeft.x){
            nx = (width - Math.abs(position.x % width)) % width;
        }
        else{
            nx = Math.abs(position.x % width);
        }

        if (position.y < lowerLeft.y){
            ny = (height - Math.abs(position.y % height)) % height;
        }
        else{
            ny = Math.abs(position.y % height);
        }
        return new Vector2D(nx, ny);
    }

    @Override
    public boolean place(Animal animal){
        Vector2D animalPosition = animal.getPosition();
        HashSet<Animal> tmp = animalsMap.get(animalPosition);
        if (tmp == null){
            tmp = new HashSet<>();
            tmp.add(animal);
            animalsMap.put(animalPosition, tmp);
        }
        else{
            tmp.add(animal);
        }
        animalsList.add(animal);
        animal.addObserver(this);
        return true;
    }

    @Override
    public void run(Integer directions[]){
        int i = 0;
        for (Integer direction : directions){
            Animal tmp = animalsList.get(i % animalsList.size());
            tmp.turnAround(direction);
            tmp.move();
            i++;
        }

    }

    @Override
    public boolean isOccupied(Vector2D position) {
        if (objectAt(position) != null)
            return true;
        return false;
    }

    @Override
    public Object objectAt(Vector2D position){
        HashSet<Animal> set = animalsMap.get(position);
        if (set == null || set.isEmpty())
            return this.grassMap.get(position);
        if (set.size() == 1){
            for (Animal animal : set){
                return animal;
            }
        }
        return set;
    }

    public String drawMap(){
        return visualizer.draw(this.lowerLeft, this.upperRight);
    }

    @Override
    public void positionChanged(Vector2D oldPosition, Vector2D newPosition, Animal animal) {
        animalsMap.get(oldPosition).remove(animal);
        HashSet<Animal> tmp = animalsMap.get(newPosition);
        if (tmp == null){
            tmp = new HashSet<>();
            tmp.add(animal);
            animalsMap.put(newPosition, tmp);
        }
        else{
            tmp.add(animal);
        }
    }

    public void generateGrass(){
        Random r = new Random();
        Vector2D position = new Vector2D(r.nextInt(upperRight.x + 1), r.nextInt(upperRight.y + 1));
        if (!grassMap.containsKey(position)){
            Grass grass = new Grass(position);
            grassMap.put(position, grass);
        }
    }

    public void turnAllAnimals(){
        for (Animal animal : this.animalsList){
            animal.turnAroundAuto();
        }
    }

    public void moveAllAnimals(){
        for (Animal animal : this.animalsList){
            animal.move();
        }
    }

    public void clearDeadAnimals(){
        List<Animal> animalsListIterate = new ArrayList<>();
        animalsListIterate.addAll(animalsList);
        int i = 0;
        for (Animal animal : animalsListIterate){
            if(animal.isDead()){
                HashSet<Animal> tmp = this.animalsMap.get(animal.getPosition());
                tmp.remove(animal);
                animalsList.remove(i);
            }else{
                i++;
            }

        }


    }

    public void eatAll(){
        for (Animal animal : animalsList){
            Vector2D currentPosition = animal.getPosition();
            if (!grassMap.containsKey(currentPosition)) {
                continue;
            }
            if (animalsMap.get(currentPosition).size() == 1){
                animal.eat(this.plantEnergy);
                grassMap.remove(currentPosition);
                continue;
            }
            else {
                List<Animal> list = new ArrayList<>();
                double max = 0;
                for (Animal animal1 : animalsMap.get(currentPosition)){
                    System.out.println(animal1 + " " + animal1.getEnergy());
                    if (animal1.getEnergy() > max){
                        list.clear();
                        list.add(animal1);
                        max = animal1.getEnergy();
                    }else if (animal1.getEnergy() == max){
                        list.add(animal1);
                    }
                }

                int listSize = list.size();
                for (Animal animal2 : list){
                    animal2.eat(this.plantEnergy / listSize);
                    System.out.println(animal2 + " " + animal2.getEnergy());
                }
                grassMap.remove(currentPosition);
            }
        }
    }
}

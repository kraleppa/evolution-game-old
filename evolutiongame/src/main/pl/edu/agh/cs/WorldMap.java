package pl.edu.agh.cs;

import javax.net.ssl.SSLContext;
import javax.sound.midi.SysexMessage;
import java.util.*;

public class WorldMap implements IWorldMap, IPositionChangeObserver{
    public List<Animal> animalsList = new ArrayList<>();
    private Map<Vector2D, List<Animal>> animalsMap = new HashMap<>();
    private Map<Vector2D, Grass> grassMap = new HashMap<>();
    private final Vector2D lowerLeft;
    private final Vector2D upperRight;
    public final Integer width;
    public final Integer height;
    public final Double startEnergy;
    public final Double moveEnergy = 2.0;
    public final Double plantEnergy = 100.0;
    public final Double jungleRatio = 0.5;
    public MapVisualizer visualizer;

    public WorldMap(Vector2D upperRight){
        this(upperRight, 10.0);
    }

    public WorldMap(Vector2D upperRight,Double startEnergy){
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
        List<Animal> tmp = animalsMap.get(animalPosition);
        if (tmp == null){
            tmp = new ArrayList<>();
            tmp.add(animal);
            animalsMap.put(animalPosition, tmp);
        }
        else{
            tmp.add(animal);
            tmp.sort(Comparator.comparing((Animal::getEnergy)));
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
        List<Animal> list = animalsMap.get(position);
        if (list == null || list.isEmpty())
            return this.grassMap.get(position);
        if (list.size() == 1){
            for (Animal animal : list){
                return animal;
            }
        }
        return list;
    }

    public String drawMap(){
        return visualizer.draw(this.lowerLeft, this.upperRight);
    }

    @Override
    public void positionChanged(Vector2D oldPosition, Vector2D newPosition, Animal animal) {
        animalsMap.get(oldPosition).remove(animal);
        List<Animal> tmp = animalsMap.get(newPosition);
        if (tmp == null){
            tmp = new ArrayList<>();
            tmp.add(animal);
            animalsMap.put(newPosition, tmp);
        }
        else{
            tmp.add(animal);
            tmp.sort(Comparator.comparing((Animal::getEnergy)));
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

    public void putGrass(Vector2D position){
        grassMap.put(position, new Grass(position));
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
                List<Animal> tmp = this.animalsMap.get(animal.getPosition());
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
                List<Animal> sameEnergy = new ArrayList<>();
                sameEnergy.add(animalsMap.get(currentPosition).get(animalsMap.get(currentPosition).size() - 1));
                for (int i = animalsMap.get(currentPosition).size() - 2; i >= 0; i--){
                    if (animalsMap.get(currentPosition).get(i).getEnergy() == animalsMap.get(currentPosition).get(i + 1).getEnergy()){
                        sameEnergy.add(animalsMap.get(currentPosition).get(i));
                    }
                    else{
                        break;
                    }
                }
                for (Animal animal1 : sameEnergy){
                    animal1.eat(this.plantEnergy / sameEnergy.size());
                }
                grassMap.remove(currentPosition);
            }
        }
    }

    public void procreateAll(){
        List<Animal> childList = new ArrayList<>();
        Set<Vector2D> hasPositionProcreated = new HashSet<>();
        for (Animal animal : animalsList){
            if (this.animalsMap.get(animal.getPosition()).size() == 1){
                continue;
            }
            if (!hasPositionProcreated.contains(animal.getPosition())){
                List<Animal> animalsOnPosition = this.animalsMap.get(animal.getPosition());
                Animal father = animalsOnPosition.get(animalsOnPosition.size() - 1);
                Animal mother = animalsOnPosition.get(animalsOnPosition.size() - 2);
                hasPositionProcreated.add(animal.getPosition());
                if (father.canProcreate() && mother.canProcreate()){
                    System.out.print(animal.getPosition() + " born: ");
                    childList.add(father.procreate(mother));
                }
            }
        }
        for (Animal animal : childList){
            this.place(animal);
            System.out.println(animal.getPosition() + " has been born");
        }

    }
}

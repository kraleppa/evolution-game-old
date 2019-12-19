package pl.edu.agh.cs;

import java.util.*;

public class WorldMap implements IWorldMap, IPositionChangeObserver{
    public List<Animal> animalsList = new ArrayList<>();
    public Set<Grass> grassSet = new HashSet<>();
    private Map<Vector2D, List<Animal>> animalsMap = new HashMap<>();
    private Map<Vector2D, Grass> grassMap = new HashMap<>();

    //Constructor
    private final Vector2D upperRight;
    public final Double startEnergy;
    public final Double moveEnergy;
    public final Double plantEnergy;
    public final Double jungleRatio;

    private final Vector2D lowerLeft;
    public final Integer width;
    public final Integer height;
    private final Vector2D jungleLowerLeft;
    private final Vector2D jungleUpperRight;
    public MapVisualizer visualizer;

    public WorldMap(Vector2D upperRight){
        this(upperRight, 50.0);
    }

    public WorldMap(Vector2D upperRight,Double startEnergy){
        this(upperRight, startEnergy, 2.0, 10.0);

    }

    public WorldMap(Vector2D upperRight, Double startEnergy, Double moveEnergy, Double plantEnergy){
        this(upperRight, startEnergy, moveEnergy, plantEnergy, 0.333333);
    }

    public WorldMap(Vector2D upperRight, Double startEnergy, Double moveEnergy, Double plantEnergy, Double jungleRatio){
        this.upperRight = upperRight;
        this.startEnergy = startEnergy;
        this.moveEnergy = moveEnergy;
        this.plantEnergy = plantEnergy;
        this.jungleRatio = jungleRatio;

        this.lowerLeft = new Vector2D(0,0);
        this.visualizer = new MapVisualizer(this);
        this.height = upperRight.y + 1;
        this.width = upperRight.x + 1;

        int jungleWidth = (int) Math.round(width * jungleRatio);
        int jungleHeight = (int) Math.round(height * jungleRatio);
        this.jungleLowerLeft = new Vector2D((this.width - jungleWidth) / 2, (this.height - jungleHeight) / 2);
        this.jungleUpperRight = new Vector2D(jungleLowerLeft.x + jungleWidth - 1, jungleLowerLeft.y + jungleWidth - 1);
    }



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
        int iterations = 0;
        int tooManyTimes = (jungleUpperRight.x - jungleLowerLeft.x) * (jungleUpperRight.y - jungleLowerLeft.y);
        while (iterations < tooManyTimes ){
            int jungleGrassX = r.nextInt(jungleUpperRight.x - jungleLowerLeft.x + 1) + jungleLowerLeft.x;
            int jungleGrassY = r.nextInt(jungleUpperRight.y - jungleLowerLeft.y + 1) + jungleLowerLeft.y;
            Vector2D jungleGrassPosition = new Vector2D(jungleGrassX, jungleGrassY);
            if (this.objectAt(jungleGrassPosition) == null){
                putGrass(new Vector2D(jungleGrassX, jungleGrassY));
                break;
            }
            iterations++;
        }
        iterations = 0;
        tooManyTimes = (upperRight.x * upperRight.y) - tooManyTimes;
        int maxX = 0;
        int maxY = 0;
        int minY = 0;
        int minX = 0;

        while (iterations < tooManyTimes){
            //steppe has 4 segments and following switch chooses one of them
            switch(r.nextInt(4)){
                case 0:
                    minX = 0;
                    maxX = upperRight.x;
                    minY = 0;
                    maxY = jungleLowerLeft.y;
                    break;
                case 1:
                    minX = 0;
                    maxX = jungleLowerLeft.x;
                    minY = jungleLowerLeft.y;
                    maxY = jungleUpperRight.y;
                    break;
                case 2:
                    minX = jungleUpperRight.x;
                    maxX = upperRight.x;
                    minY = jungleLowerLeft.y;
                    maxY = jungleUpperRight.y;
                    break;
                case 3:
                    minX = 0;
                    maxX = upperRight.x;
                    minY = jungleUpperRight.y;
                    maxY = upperRight.y;
                    break;
            }
            int jungleGrassX = r.nextInt(maxX - minX + 1) + minX;
            int jungleGrassY = r.nextInt(maxY - minY + 1) + minY;
            Vector2D jungleGrassPosition = new Vector2D(jungleGrassX, jungleGrassY);
            if (this.objectAt(jungleGrassPosition) == null){
                putGrass(new Vector2D(jungleGrassX, jungleGrassY));
                break;
            }
            iterations++;
        }



    }

    public void putGrass(Vector2D position){
        Grass grass = new Grass(position);
        grassMap.put(position, grass);
        grassSet.add(grass);
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

    public int clearDeadAnimals(){
        int killCount = 0;
        List<Animal> animalsListIterate = new ArrayList<>();
        animalsListIterate.addAll(animalsList);
        int i = 0;
        for (Animal animal : animalsListIterate){
            if(animal.isDead()){
                List<Animal> tmp = this.animalsMap.get(animal.getPosition());
                tmp.remove(animal);
                animalsList.remove(i);
                killCount++;
            }else{
                i++;
            }

        }

        return killCount;
    }

    public void eatAll(){
        for (Animal animal : animalsList){
            Vector2D currentPosition = animal.getPosition();
            if (!grassMap.containsKey(currentPosition)) {
                continue;
            }
            if (animalsMap.get(currentPosition).size() == 1){
                animal.eat(this.plantEnergy);
                grassSet.remove(grassMap.get(currentPosition));
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
                grassSet.remove(grassMap.get(currentPosition));
                grassMap.remove(currentPosition);
            }
        }
    }

    public int procreateAll(){
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
                    Animal child = father.procreate(mother);
                    if (child != null){
                        childList.add(child);
                    }

                }
            }
        }
        for (Animal animal : childList){
            this.place(animal);
            System.out.println(animal.getPosition() + " has been born");
        }
        return childList.size();
    }

    public boolean isPositionJungle(Vector2D position){
        return (position.follows(jungleLowerLeft) && position.precedes(jungleUpperRight));

    }
}

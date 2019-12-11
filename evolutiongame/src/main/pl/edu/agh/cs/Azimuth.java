package pl.edu.agh.cs;

public enum Azimuth {
    N,
    NE,
    E,
    SE,
    S,
    SW,
    W,
    NW;
    //for test purpose only!
    public static Azimuth[] convert(String [] args) throws IllegalArgumentException{
        Azimuth[] ret = new Azimuth[args.length];
        for (int i = 0; i < args.length; i++){
            switch (args[i]){
                case "n": ret[i] =  Azimuth.N; break;
                case "ne": ret[i] = Azimuth.NE; break;
                case "e": ret[i] = Azimuth.E; break;
                case "se": ret[i] = Azimuth.SE; break;
                case "s": ret[i] = Azimuth.S; break;
                case "sw": ret[i] = Azimuth.SW; break;
                case "w": ret[i] = Azimuth.W; break;
                case "nw": ret[i] = Azimuth.NW; break;
                default: throw new IllegalArgumentException (args[i] + "  is not legal Azimuth");
            }
        }
        return ret;
    }

    public Vector2D toUnitVector(){
        switch(this){
            case N: return new Vector2D(0,1);
            case NE: return new Vector2D(1,1);
            case E: return new Vector2D(1,0);
            case SE: return new Vector2D(1,-1);
            case S: return new Vector2D(0,-1);
            case SW: return new Vector2D(-1,-1);
            case W: return new Vector2D(-1,0);
            case NW: return new Vector2D(-1,1);
        }
        return null;
    }

    public Azimuth next(){
        switch (this){
            case N: return Azimuth.NE;
            case NE: return Azimuth.E;
            case E: return Azimuth.SE;
            case SE: return Azimuth.S;
            case S: return Azimuth.SW;
            case SW: return Azimuth.W;
            case W: return Azimuth.NW;
            case NW: return Azimuth.N;
            default: return null;
        }
    }



}

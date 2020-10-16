package MI_hazi_1;

public class Pallet {
    private Dimension dimension;
    private int id;

    public Pallet(Dimension d, int _id){
        dimension = d;
        id = _id;
    }

    public Dimension getDimension() {
        return dimension;
    }


    public int getId() {
        return id;
    }
}

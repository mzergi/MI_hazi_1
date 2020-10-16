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

    public void rotate (){
        int tmp = dimension.getHeight();
        dimension.setHeight(dimension.getWidth());
        dimension.setWidth(tmp);
    }
    public int getArea(){
        return dimension.getHeight() * dimension.getWidth();
    }
    public static boolean comparePallet(Pallet x, Pallet y){
        if(x.getArea() > y.getArea()) return true;
        else return false;
    }
}

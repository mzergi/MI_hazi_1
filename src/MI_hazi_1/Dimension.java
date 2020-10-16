package MI_hazi_1;

public class Dimension {
    private int width;
    private int height;

    public Dimension(int _width, int _height){
        width = _width;
        height = _height;
    }
    public void setWidth(int _width){
        width = _width;
    }
    public void setHeight(int _height){
        height = _height;
    }
    public int getWidth() {return width;}
    public int getHeight() {return height;}
}

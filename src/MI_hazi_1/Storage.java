package MI_hazi_1;

import java.io.Console;

public class Storage {
    private int matrix [][];
    private Dimension dimension;
    private Pillar pillars[];


    public Storage(int _width, int _height, Pillar _pillars[]){
        dimension = new Dimension(_width, _height);
        matrix = new int[_height][_width];
        for(int i = 0; i < _height; i++){
            for (int j = 0; j < _width; j++){
                matrix[i][j] = 0;
            }
        }
        pillars = _pillars;
    }

    public Storage(Dimension d, Pillar _pillars[]){
        dimension = d;
        matrix = new int[d.getWidth()][d.getHeight()];
        for(int i = 0; i < d.getHeight(); i++){
            for (int j = 0; j < d.getWidth(); j++){
                matrix[i][j] = 0;
            }
        }
        pillars = _pillars;
    }

    public void writeStorage(){
        int w = dimension.getWidth();
        int h = dimension.getHeight();
        for(int i = 0; i < h; i++){
            for (int j = 0; j < w; j++){
                if(j < w-1){
                    System.out.print(matrix [i][j]+ "\t");
                }
                else if (j == w-1){
                    System.out.print(matrix [i][j]+ "\n");
                }
            }
        }
    }
    public void fill(Pallet[] pallets){
        for(Pallet p : pallets){
           findPlace(p);
        }
    }
    private void findPlace(Pallet p){
        int pwidth = p.getDimension().getWidth();
        int pheight = p.getDimension().getHeight();
        Point startpos = new Point(0,0);

        boolean found = false;
        boolean rotated = false;
        while(!found){
            boolean free = true;
            if(!rotated) {
                for (int i = 0; i < pheight; i++) {
                    for (int j = 0; j < pwidth; j++) {
                        int checkY = i + startpos.getY();
                        int checkX = j + startpos.getX();
                        if(checkX > (dimension.getWidth() - 1) || (checkY > dimension.getHeight())) {
                            free = false;
                        }
                        else {
                            if (matrix[checkY][checkX] != 0) free = false;
                            if (!(checkY == 0 || checkX == 0 || checkY == pheight - 1 || checkX == pwidth - 1)) {
                                for (Pillar pillar : pillars) {
                                    if (pillar.getPos().getX() == checkX && pillar.getPos().getY() == checkY) {
                                        free = false;
                                    }
                                }
                            }
                        }
                    }
                }
                if (free) {
                    for (int i = 0; i < pheight; i++) {
                        for (int j = 0; j < pwidth; j++) {
                            matrix[i+startpos.getY()][j+startpos.getX()] = p.getId();
                            found = true;
                        }
                    }
                }
                else { //move left top
                    boolean atTop = (startpos.getY() == 0);
                    boolean atBottom = (startpos.getY() == dimension.getHeight() - 1);
                    boolean atLeft = (startpos.getX() == 0);
                    boolean atRight = (startpos.getX() == dimension.getWidth() - 1);
                    boolean wasAtTop = false;
                    boolean wasAtBottom = false;
                    boolean wasAtLeft = false;
                    boolean wasAtRight = false;
                    if(atTop) {
                        startpos.setY(startpos.getY() + 1);
                        wasAtTop = true;
                    }
                    if (atBottom) {
                        startpos.setY(startpos.getY() - 1);
                        wasAtBottom = true;
                    }
                    if (atLeft){
                        startpos.setX(startpos.getX() + 1);
                        wasAtLeft = true;
                    }
                    if(atRight){
                        startpos.setX(startpos.getX() - 1);
                        wasAtRight = true;
                    }
                    else {
                        startpos.setX(startpos.getX() + 1);
                        startpos.setY(startpos.getY() + 1);
                    }
                    if(wasAtTop && wasAtBottom && wasAtLeft && wasAtRight){
                        rotated = true;
                    }
                }
            } //siman mehet kulon fvbe width-height cserevel mert az basically egy elforgatas
            else if (rotated){
                int tmp = pwidth;
                pwidth = pheight;
                pheight = tmp;
                rotated = false;
            }
        }

    }


}

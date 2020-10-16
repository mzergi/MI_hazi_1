package MI_hazi_1;

import java.io.Console;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

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
        ArrayList<Pallet> palletList = new ArrayList<Pallet>();
        ArrayList<Boolean> doneList = new ArrayList<Boolean>();
        for(Pallet p : pallets){
            palletList.add(p);
        }
        palletList.sort(new PalletComparator());
        Collections.reverse(palletList);
        int tryNumber = 0;
        //ORDER BY AREA
        palletList = palletList;
        //for each package FIND A PLACE starting from BIGGEST
        while(!checkDoneList(doneList)) {
            doneList = placingAlgorithm(palletList);
            if (!checkDoneList(doneList)) {
                ////////////////////////////////////
                //follow up placement algorithm here
                //doneList = followUp(doneList, palletList)
                //change done list to true if we could place something on the second go
                //return changed list
                ////////////////////////////////////
                writeStorage();
                System.out.println("//////////////////////////////////////////////////////////////////////////");
                System.out.println("//////////////////////////////////////////////////////////////////////////");
                if (tryNumber % 5 == 0 && tryNumber > 0){
                    int firstFalse = getFirstFalse(doneList);
                    Pallet tmp = palletList.get(firstFalse);
                    tmp.rotate();
                    palletList.set(firstFalse,palletList.get(0));
                    palletList.set(0,tmp);
                }
                doneList.clear();
                clearMatrix();
                tryNumber++;
            }
        }
    }
    private ArrayList<Boolean> placingAlgorithm(ArrayList pallets){
        ArrayList<Pallet> palletList = pallets;
        ArrayList<Boolean> doneList = new ArrayList<Boolean>();
        for (Pallet p : palletList) {
            boolean found = false;
            for (int i = 0; i < dimension.getHeight(); i++) {
                for (int j = 0; j < dimension.getWidth(); j++) {
                    found = findPlace(p, new Point(j, i));
                    if (found) {
                        doneList.add(true);
                        break;
                    }
                }
                if (found) break;
            }
            if (!found) {
                p.rotate();
                for (int i = 0; i < dimension.getHeight(); i++) {
                    for (int j = 0; j < dimension.getWidth(); j++) {
                        found = findPlace(p, new Point(j, i));
                        if (found) {
                            doneList.add(true);
                            break;
                        }
                    }
                    if (found) break;
                }
            }
            if(!found){
                doneList.add(false);
            }
        }
        return doneList;
    }
    private boolean checkDoneList(ArrayList list){
        ArrayList<Boolean> tmp = new ArrayList<Boolean>();
        tmp.addAll(list);
        for(Boolean b : tmp){
            if(b == false){
                return false;
            }
        }
        if(tmp.size() == 0){
            return false;
        }
        return true;
    }
    private int getFirstFalse(ArrayList list){
        ArrayList<Boolean> tmp = new ArrayList<Boolean>();
        tmp.addAll(list);
        for(int i = 0; i < tmp.size(); i++){
            if(tmp.get(i) == false){
                return i;
            }
        }
        if(tmp.size() == 0){
            return 0;
        }
        return -1;
    }

    private void clearMatrix(){
        for(int i = 0; i < dimension.getHeight(); i++){
            for (int j = 0; j < dimension.getWidth(); j++){
                matrix[i][j] = 0;
            }
        }
    }
    private boolean findPlace(Pallet p, Point startpos){
        boolean success = false;
        success = checkIfFits(startpos, p);
        if(success) {
            placePallet(startpos, p);
        }
        return success;
    }
    private boolean checkIfFits(Point topLeft, Pallet p){
        int checkX = topLeft.getX();
        int checkY = topLeft.getY();
        int pheight = p.getDimension().getHeight();
        int pwidth = p.getDimension().getWidth();
        boolean free = true;
        if(((checkX + pwidth) > (dimension.getWidth())) || ((checkY + pheight) > (dimension.getHeight()))) {
            free = false;
        }
        else {
            //if (matrix[checkY][checkX] != 0) free = false;
            for(int i = 0; i < p.getDimension().getHeight(); i++){
                for(int j = 0; j < p.getDimension().getWidth(); j++){
                    if (matrix [checkY+i][checkX+j] != 0) free = false;
                }
            }
            if (!(checkY == 0 || checkX == 0 || checkY == dimension.getHeight() || checkX == dimension.getWidth())) {
                for (Pillar pillar : pillars) {
                    if (pillar.getPos().getX() == checkX && pillar.getPos().getY() == checkY) {
                        free = false;
                    }
                }
            }
        }
        return free;
    }
    private void placePallet(Point point, Pallet p){
        for (int i = 0; i < p.getDimension().getHeight(); i++) {
            for (int j = 0; j < p.getDimension().getWidth(); j++) {
                matrix[i+point.getY()][j+point.getX()] = p.getId();
            }
        }
    }


}

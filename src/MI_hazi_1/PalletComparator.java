package MI_hazi_1;

import java.util.Comparator;

public class PalletComparator implements Comparator {
    public PalletComparator(){
        super();
    }
    @Override
    public int compare(Object o1, Object o2) {
        o1 = (Pallet) o1;
        o2 = (Pallet) o2;

        if(((Pallet) o1).getArea() < ((Pallet) o2).getArea()) return -1;
        else if(((Pallet) o1).getArea() == ((Pallet) o2).getArea()) return 0;
        else return 1;
    }
}

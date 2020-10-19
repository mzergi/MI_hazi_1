package MI_hazi_1;
import java.util.Scanner;

public class Main {
    public static void main(String args[]){
        int storageWidth;
        int storageHeight;
        int numberOfPillars;
        int numberOfPallets;
        Pallet pallets[];
        Pillar pillars[];
        Storage storage;

        Scanner in = new Scanner(System.in);

        String s = in.nextLine();
        String comps[] = s.split("\\s+");

        storageHeight = Integer.parseInt(comps[0]);
        storageWidth = Integer.parseInt(comps[1]);


        s = in.nextLine();
        comps = s.split("\\s+");

        numberOfPillars = Integer.parseInt(comps[0]);

        s = in.nextLine();
        comps = s.split("\\s+");

        numberOfPallets = Integer.parseInt(comps[0]);
        pallets = new Pallet[numberOfPallets];
        pillars = new Pillar[numberOfPillars];

        for(int i = 0; i < numberOfPillars; i++){
            s = in.nextLine();
            comps = s.split("\\s+");
            pillars[i] = new Pillar(new Point(Integer.parseInt(comps[1])-1, Integer.parseInt(comps[0])-1));
        }

        storage = new Storage(storageWidth, storageHeight, pillars);

        for(int i = 0; i < numberOfPallets; i++){
            s = in.nextLine();
            comps = s.split("\\s+");
            pallets[i] = new Pallet(new Dimension(Integer.parseInt(comps[0]), Integer.parseInt(comps[1])), i+1);
        }

        storage.fill(pallets);
        storage.writeStorage();

    }


}

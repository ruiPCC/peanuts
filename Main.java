

/**
 * Created by codecadet on 13/12/2018.
 */
public class Main {

    public static void main(String[] args) {


        TourGenerator tourGenerator = new TourGenerator();

        //tourGenerator.constructGridGraph();


        tourGenerator.generateMaze();



        tourGenerator.printPictureURL();


        /*
        List<PositionVect> list = new LinkedList<PositionVect>();
        PositionVect p1 = new PositionVect(0,0);
        PositionVect p2 = new PositionVect(0,1);
        PositionVect p3 = new PositionVect(1,0);
        PositionVect p4 = new PositionVect(1,1);
        list.add(p1);
        System.out.println(list.size());
        list.add(p2);
        System.out.println(list.size());
        list.remove(p3);
        System.out.println(list.size());
        list.remove(p2);
        System.out.println(list.size());
        list.remove(p1);
        System.out.println(list.size());
        */






    }




}

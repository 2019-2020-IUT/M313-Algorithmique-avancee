package TP4;

public class DemoABin {

    public static void main(String[] args) {
    	Integer[] tab = {5,3,9,4,8,6,4,3,1,1,9,2};
        ABin<Integer> a1 = new ABin<>(197,new ABin<>(94,new ABin<>(8, new ABin<>(), new ABin<>()),new ABin<>()), new ABin<>(24,new ABin<>(),new ABin<>()));
        ABin<Integer> a2 = new ABin<Integer>(tab);
        ABin<Integer> a3 = ABin.createRandom(5);
        
        boolean checkDeptha1 = a1.getDepht() == 3;
        boolean checkSizea1 = a1.size() == 4;
        System.out.println("getDeth a1 : " + checkDeptha1);
        System.out.println("getSize a1 : " + checkSizea1);
        System.out.println(a1.toString());
        
        boolean checkDeptha2 = a2.getDepht() == 4;
        boolean checkSizea2 = a2.size() == 12;
        System.out.println("getDeth a2 : " + checkDeptha2);
        System.out.println("getSize a2 : " + checkSizea2);System.out.println(a2.toString());
        
        //a1.showInFrame();
        //a2.showInFrame();
        a3.showInFrame();
    }

}
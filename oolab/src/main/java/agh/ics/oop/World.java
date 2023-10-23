package agh.ics.oop;

public class World {
    public static void run(String[] args) {

        for (String arg : args) {
            switch (arg) {
                case "f":
                    System.out.println("Zwierzak idzie do przodu");
                    break;
                case "b":
                    System.out.println("Zwierzak idzie do tylu");
                    break;
                case "l":
                    System.out.println("Zwierzak skreca w lewo");
                    break;
                case "r":
                    System.out.println("Zwierzak skreca w prawo");
                    break;
                default:
            }
        }
    }


    public static void main(String[] args) {
        System.out.println("Start");
        run(args);
        System.out.println("Stop");
    }
}

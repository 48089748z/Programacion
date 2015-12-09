package Apuntes;

enum Transport {CAR, TRUCK, AIRPLANE, TRAIN, BOAT} //Values that this enum can take.
public class EnumExample
{
    public static void main(String[] args) {
        Transport transport;
        transport = Transport.CAR;
        System.out.println(transport); //Prints 'CAR'

        transport = Transport.TRUCK;
        System.out.println(transport); //Prints 'TRUCK'

        transport = Transport.AIRPLANE;
        System.out.println(transport); //Prints 'AIRPLANE'

        transport = Transport.TRAIN;
        System.out.println(transport); //Prints 'TRAIN'

        transport = Transport.BOAT;
        System.out.println(transport); //Prints 'BOAT'

    }
}

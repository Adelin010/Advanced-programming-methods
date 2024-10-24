package Problem3;

/**
 * Driver
 */
public class Driver {

    public static void main(String[] args) {
        Arithmetic arithmetic = new Arithmetic();
        Number n1 = new Number();
        Number n2 = new Number();

        System.out.println(n1.toString());
        System.out.println(n2.toString());
        System.out.println(arithmetic.sumOf2(n1, n2).toString());
        Number diff = arithmetic.diffOf2(n1, n2);
        try{
            System.out.println(diff.toString());
        }catch(NullPointerException exp){
            System.out.println("The difference is imposible");
        }

        System.out.println("Multiplication: \n" + arithmetic.multiplication(n1, 5).toString());
        System.out.println("DIVISION: ");
        for(int i = 1; i < 10; ++i){
            System.out.println(arithmetic.division(n1, i));
        }
    }
}
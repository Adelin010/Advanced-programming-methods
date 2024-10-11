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
    }
}
package Problem1;

public class Uni {

    public static void main(String[] args) {
        Prof prof = new Prof();
        Grade[] grades = new Grade[20];
        for(int i = 0; i <= 19; ++i){
            int gradeValue = (int)(10 + Math.random()*(100 - 10));
            grades[i] = new Grade(gradeValue, "Math");
        }

        /*
         * Test first point
         */
        for(Grade g: prof.getWhatDoesnotPass(grades)){
            if(g == null)
                break;
            else System.out.println(g.toString());
        }
    }
    
}

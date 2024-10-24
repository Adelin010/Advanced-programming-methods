package Problem1;

public class Uni {

    public static void main(String[] args) {
        Prof prof = new Prof();
        Grade[] grades = new Grade[20];
        for(int i = 0; i <= 19; ++i){
            int gradeValue = (int)(10 + Math.random()*(100 - 10));
            grades[i] = new Grade(gradeValue, "Math");
            System.out.println("~~~" + grades[i].getGrade());
        }

        /*
         * Test first point
         */
        for(Grade g: prof.getWhatDoesnotPass(grades)){
            if(g == null)
                break;
            else System.out.println(g.toString());
        }

        /*
         * Testing the second point
         */
        System.out.println(prof.getMeanGrade(grades));

        /*
         * Testing the third point
         */
        Grade[] rounds = prof.getRoundedWithoutAltering(grades);
        for(Grade round: rounds){
            System.out.println(round.toString() );
        }
        System.out.println("****************************");
        for(Grade grade: grades){
            System.out.println(grade.toString() );
        }


        /*
         * Testing the fourth point
         */
        Grade maxG = prof.getMaximum(grades);
        System.out.println("\nMaxim\n" + maxG.toString());


        int[] vect = {29, 37, 38, 41, 84, 67};
        Grade[] gradesVect = new Grade[6];
        for(int i = 0; i < 6; ++i){
            gradesVect[i] = new Grade(vect[i], "saesrt");
        }
        System.out.println("\n\n\n");
        for(Grade g1: prof.getWhatDoesnotPass(prof.getRoundedGrades(gradesVect))){
            System.out.println(g1.toString());
        }
        
    }

    
    
}

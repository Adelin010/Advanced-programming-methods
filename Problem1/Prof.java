package Problem1;

public class Prof {

    public Grade[] getWhatDoesnotPass(Grade[] grades){
        Grade[] res = new Grade[grades.length];
        int idx = 0;
        for(Grade g: grades){
            if(g.getGrade() < 40)
                res[idx++] = g;
        }
        return res;
    }

    public double getMeanGrade(Grade[] grades){
        long amount = 0;
        for(Grade g: grades){
            amount += g.getGrade();
        }
        double mean = (double)amount/grades.length;
        return mean;
    }

    private int generateMultipleOf5PerGrade(Grade grade){
        return (grade.getGrade()/5 + 1 ) * 5;
    }

    private int generateDiffForRounding(Grade grade){
        System.out.println("-------Multiple of 5: ");
        System.out.println(this.generateMultipleOf5PerGrade(grade));
        return this.generateMultipleOf5PerGrade(grade) - grade.getGrade();
    }


    /**
     * 
     * @param grades
     * @return Vector of Objects of type Grade 
     * !!! because we do not have a alternative to copy our objects only by value(shallow copy)
     * !!! the method will change the values of the argument vector
     */
    public Grade[] getRoundedGrades(Grade[] grades){
        Grade[] res = new Grade[grades.length];
        int idx = 0;
        for(Grade g: grades){
            res[idx] = g;
            if(res[idx].getGrade() < 38){
                idx++;
                continue;
            }
            int diff = this.generateDiffForRounding(g);
            if(diff < 3){
                res[idx].setGrade(res[idx].getGrade()+diff);
                System.out.println("Difference found of " + diff + " And I changed into " + res[idx].getGrade() );
            }
            idx++;
        }
        return res;
    }

    /*
     * Better alternative for keeping the integrity of the values 
     */
    public Grade[] getRoundedWithoutAltering(Grade[] grades){
        Grade[] res = new Grade[grades.length];

        //Init the res vector
        for(int i = 0; i < grades.length; ++i){
            res[i] = new Grade(grades[i].getGrade(), grades[i].getSubject());
        }
        //Use the already made function to convert the values
        return this.getRoundedGrades(res);


    }

    public Grade getMaximum(Grade[] grades){
        Grade[] rounds = this.getRoundedWithoutAltering(grades);
        Grade maxGrade = rounds[0];
        for(Grade g: rounds){
            if(maxGrade.getGrade() < g.getGrade())
                maxGrade = g;
        }
        return maxGrade;
    }

    
}
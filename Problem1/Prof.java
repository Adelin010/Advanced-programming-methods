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
}
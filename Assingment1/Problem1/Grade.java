package Problem1;

public class Grade {
    
    private int grade;
    private String subject;

    Grade(){
        this.grade = 0;
        this.subject = null;
    }
    Grade(int grade, String subject){

        this.setGrade(grade);
        this.subject = subject;
    }

    public void setGrade(int grade){
        if(grade < 0 || grade > 100)
            throw new Error("Grade can not be negative nor can it be over 100");
        else 
            this.grade = grade;
    }

    public int getGrade(){
        return this.grade;
    }

    public String getSubject(){
        return this.subject;
    }

    public void setSubject(String subject){
        if(subject == "")
            throw new Error("Subject can not be empty");
    }

    @Override
    public String toString(){
        return "Grade of the subject " + this.subject + " has " + this.grade;
    }

}

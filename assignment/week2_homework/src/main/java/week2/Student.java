package week2;

import week2.InitMethod;

public class Student {
    private int score;

    @InitMethod
    public void setScore(int s)
    {
        if (s > 0) {
            this.score = s;
            System.out.println(score);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void getScore(){
    }
}

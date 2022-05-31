package org.and.quizsamsung;

public class LevelList {
   private String lvl1a1,lvl1a2,lvl1a3,lvl1h, textView1,answer,hint;
   private String userSelectedAnswer;

    public LevelList(String textView1,String lvl1a1, String lvl1a2, String lvl1a3, String lvl1h,String answer, String userSelectedAnswer, String hint) {
        this.lvl1a1 = lvl1a1;
        this.lvl1a2 = lvl1a2;
        this.lvl1a3 = lvl1a3;
        this.lvl1h = lvl1h;
        this.textView1 = textView1;
        this.answer = answer;
        this.userSelectedAnswer = userSelectedAnswer;
        this.hint = hint;


    }

    public String getLvl1a1() {
        return lvl1a1;
    }

    public String getLvl1a3() {
        return lvl1a3;
    }

    public String getLvl1a2() {
        return lvl1a2;
    }

    public String getTextView1() {
        return textView1;
    }

    public String getAnswer() {
        return answer;
    }

    public String getUserSelectedAnswer() {
        return userSelectedAnswer;
    }

    public String getLvl1h() {
        return lvl1h;
    }

    public void setUserSelectedAnswer(String userSelectedAnswer) {
        this.userSelectedAnswer = userSelectedAnswer;
    }
    public String getHint() {
        return hint;
    }

}

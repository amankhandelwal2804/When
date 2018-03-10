package com.when.threemb.when;

/**
 * Created by User on 1/18/2017.
 */
public class EachVote {
    private String motion;
    private int motionNo;
    private int yescount;
    private int nocount;
    private int answer;

    public EachVote(int answer, String motion, int motionNo, int nocount, int yescount) {
        this.answer = answer;
        this.motion = motion;
        this.motionNo = motionNo;
        this.nocount = nocount;
        this.yescount = yescount;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public String getMotion() {
        return motion;
    }

    public void setMotion(String motion) {
        this.motion = motion;
    }

    public int getMotionNo() {
        return motionNo;
    }

    public void setMotionNo(int motionNo) {
        this.motionNo = motionNo;
    }

    public int getNocount() {
        return nocount;
    }

    public void setNocount(int nocount) {
        this.nocount = nocount;
    }

    public int getYescount() {
        return yescount;
    }

    public void setYescount(int yescount) {
        this.yescount = yescount;
    }
}

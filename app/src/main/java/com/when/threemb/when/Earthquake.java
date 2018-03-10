package com.when.threemb.when;

import android.content.Context;
import android.content.SharedPreferences;

public class Earthquake{
    private String mAttendance;
    private String mStatus;
    private String mSubject;
    private String mTeacher;
    private String mStart;
    private String mEnd;

    public String getmAttendance(){
        return mAttendance;
    }

    public String getmStatus() {
        return mStatus;
    }

    public String getmSubject() {
        return mSubject;
    }

    public String getmTeacher() {
        return mTeacher;
    }

    public String getmStart() {
        return mStart;
    }

    public String getmEnd() {
        return mEnd;
    }

    public Earthquake(String mAttendance, String mSubject, String mTeacher, String mStart, String mEnd,String mStatus) {
        this.mAttendance = mAttendance;
        this.mStatus = mStatus;
        this.mSubject = mSubject;
        this.mTeacher = mTeacher;
        this.mStart = mStart;
        this.mEnd = mEnd;
    }

    public void setmStatus(String mStatus) {
        this.mStatus = mStatus;
    }
}

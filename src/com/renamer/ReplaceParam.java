package com.renamer;

public class ReplaceParam {
    public String old;
    public String newS;
    boolean isStart;
    public ReplaceParam(String old, String newS, boolean isStart){
        this.newS = newS;
        this.old = old;
        this.isStart = isStart;
    }
}

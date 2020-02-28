package com.princeakash.learnheterorecyclerview;

public class Model {
    public static final int TEXT_TYPE=10;
    public static final int IMAGE_TYPE=20;
    public static final int AUDIO_TYPE=30;

    public int modelType;
    public int modelData;
    public String modelText;

    public Model(int type, int data, String text){
        this.modelType = type;
        this.modelData = data;
        this.modelText = text;
    }
}

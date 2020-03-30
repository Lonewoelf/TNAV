package com.example.tnav;

public class FoodItem {
    private int image;
    private String text1, text2;

    public FoodItem(int imageSrc, String mtext1, String mtext2){
        image = imageSrc;
        text1 = mtext1;
        text2 = mtext2;
    }

    public int getImage() {
        return image;
    }

    public String getText1(){
        return text1;
    }

    public String getText2(){
        return text2;
    }
}

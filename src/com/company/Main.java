package com.company;


public class Main {
    public static void main(String[] args) {
        try {
            Window window = new Window();
            window.frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}

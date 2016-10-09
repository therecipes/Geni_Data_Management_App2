package com.company;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        // write your code here
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GeniFrame();
            }
        });

    }
}

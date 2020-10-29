/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GOLD.COLLECTION.GAME;

import java.util.*;
import javax.swing.*;
import java.awt.*;


public class CreateChart{
    JFrame frame;
    Random rand;
    JPanel squares [][] = new JPanel[20][20];
    
    public CreateChart(){
        frame = new JFrame("GOLD GAME");
        frame.setSize(800,800);
        frame.setLayout(new GridLayout(20,20));
        int boardx = 20;
        int boardy = 20;
        for(int x = 0; x < boardx ; x++){
            for(int y = 0 ; y < boardy ; y++ ){
                squares[x][y] = new JPanel();
                if( (x + y) % 2 == 0){
                    squares[x][y].setBackground(Color.YELLOW);
                }
                else{
                    squares[x][y].setBackground(Color.WHITE);
                }
                frame.add(squares[x][y]);
            }
        }
        CreateGoldInTheChart(boardx,boardy);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
    public void CreateGoldInTheChart(int boardx,int boardy){
        int goldnumber = 20*((boardx*boardy)/100);
        for(int temp = 0; temp < goldnumber ; temp++){
            int randIntx = ((int)(Math.random()*boardx));
            int randInty = ((int)(Math.random()*boardy));
       
            if( randIntx == boardx ||randInty == boardy ){
                randIntx--;
                randInty--;
            }
             else{
                System.out.println("RandIntx: "+randIntx+"\tRandInty: "+randInty);
                // RandIntx and RandInty gold array must be kept!!!
             squares[randIntx][randInty].setBackground(Color.BLUE);
             }   
        }
    }
    
    public static void main(String []args){ // Written to try
        new CreateChart();
    }
}

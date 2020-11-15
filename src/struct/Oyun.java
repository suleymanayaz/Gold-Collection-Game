/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package struct;

import java.awt.Point;

/**
 *
 * @author AYAZ
 */

    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;


public class Oyun {

   
    public static boolean gameover = false;
    private int lines,cols,altinoran,galtinoran;
    private Node[][] grid;
    private final Point start = new Point(0,0);
    public ArrayList<Node> altinlar = new ArrayList();
    public ArrayList<Node> galtinlar = new ArrayList();
    public Oyun(int lines,int cols,int altinoran,int galtinoran){
        this.lines = lines;
        this.cols=cols;
        this.grid = new Node[lines][cols];
        
        // buraya player a  b c d eklersin
        this.altinoran=altinoran;
        this.galtinoran=galtinoran;
      
        // hepsi için yapması 
        initGrid();
        // oyuncuların altına altin ve gizli altin geliyor onu ayalra.
        goldinitGrid();
        
    }
    
 
    
    private void initGrid(){
        for(int i = 0;i<lines;i++){
                for(int j =0;j<cols;j++ ){
                    Node n = new Node(i,j);
                    grid[i][j]= n;
                }
        }
    }
    private void goldinitGrid(){
        int goldNumber = (lines*cols*altinoran)/100;
        int ggoldNumber = (goldNumber*galtinoran)/100;
        int randIntx,randInty,golddeger,randx,randy;
        int countAltin = 0;
        int countGAltin = 0;
        
        while(countAltin != goldNumber){
            randIntx=((int)(Math.random()*lines));
            randInty=((int)(Math.random()*cols));
                 if(randIntx== lines)
                 randIntx = randIntx - 1;

            if(randInty== cols)
                randInty = randInty - 1;
            Node n = new Node(randIntx,randInty);
            if(altinlar.contains(n) == false && (n.p.equals(getStart())) == false ){
              
                altinlar.add(n);
                golddeger = random(20,5);  
                // if bloguna player a b c d eklersin
                n.setAltin(true);
                n.setAltinMiktari(golddeger);
                countAltin++;
                grid[randIntx][randInty] = n;   
            }
        }
            
             while(countGAltin != ggoldNumber){
            randx=((int)(Math.random()*lines));
            randy=((int)(Math.random()*cols));
                 if(randx== lines)
                 randx = randx - 1;

            if(randy== cols)
                randy = randy - 1;
            Node m = new Node(randx,randy);
            if(galtinlar.contains(m) == false && (m.p.equals(getStart())) == false){
                galtinlar.add(m);
                golddeger = random(20,5);
                 if(m.p.equals(start))
                     m.setGAltin(false);
                 // if bloguna player a b c d eklersin
                  m.setGAltin(true);
                  m.setGAltinMiktari(golddeger);
                  countGAltin++;
                  grid[randx][randy] = m;        
            }
            
        }
        System.out.println("ALtın Sayısı  : "+countAltin+" Gizli Altin Sayisi : "+countGAltin);
    
    }
    
    
    
     public int mesafe(Point a,Point b){
        return  Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
    }
    
    public int random (int n,int m){
        Random rand = new Random();
        int value = rand.nextInt(n)+1;
        return value%m == 0 ? value:random(n,m);
    }        
    
    
    
    
    public Node getNode(Point p) {
        return grid[p.x][p.y];
    }
    public int getLines(){
        return lines;
    }
    
    public int getCols(){
        return cols;
    }
    
    public Node[][] getGrid(){
        return grid;
    }
    
    public Point getStart(){
        return start;
    }
    public Point getStartA(){
        return start;
    }
    public Point getStartB(){
        return new Point(0,getCols()-1);
    }
    public Point getStartC(){
        return new Point(getLines()-1,getCols()-1);
    }
    public Point getStartD(){
        return new Point(getLines()-1,0);
    }
    
  
}

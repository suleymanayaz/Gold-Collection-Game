/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Oyuncular;

import altintoplamaoyunu.*;
import java.awt.Point;
import java.util.ArrayList;
import javafx.scene.paint.Color;
import struct.*;


/**
 *
 * @author AYAZ
 */
public class Player{
     private int hamleMaliyet,hedefBelirlemeMaliyet,altinmiktari,harcananAltinMiktari,toplamAdim,toplananAltinMiktari,adimSayisi ;
     private Point start,end;
    public boolean altinbitti = false;
     private ArrayList<Point> playeragittigiyollar ;
     Oyun oyun;
     ArrayList<Node> yollar;
     public Point getStartPoint(){
        return this.start;
    }
    public Point getEndPoint(){
        return this.end;
    }
    public void setPoint(Point point){
        this.start = point;
    }
    public int mesafe(Point a,Point b){
        return  Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
    }
    public boolean getAltinBitti(){
        return altinbitti;
    }
      public int getAltinMiktari(){
        return this.altinmiktari;
    }
          public int getHarcananAltinMiktari(){
        return this.harcananAltinMiktari;
    }
            public int getToplamAdim(){
        return this.toplamAdim;
    }
        public int getToplananAltinMiktari(){
            return this.toplananAltinMiktari;
        }    
             public ArrayList<Point> getGidilenYollar(){
        return this.playeragittigiyollar;
    }
 
 
 }

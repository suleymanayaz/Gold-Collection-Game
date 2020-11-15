/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Oyuncular;


import java.awt.Point;
import java.util.ArrayList;

import struct.*;


/**
 *
 * @author AYAZ
 */
public class Player{
     private int hamleMaliyet,hedefBelirlemeMaliyet,altinmiktari,harcananAltinMiktari,toplamAdim,toplananAltinMiktari,adimSayisi ;
     private Point start,end;
     private  boolean altinbitti = false,hedefvar;
     private ArrayList<Point> playeragittigiyollar ;
     Oyun oyun;
     ArrayList<Node> yollar;
    public Point getStartPoint(){
        return this.start;
    }
     
    public Point getEndPoint(){
        return this.end;
    }
    public void setAdimSayisi(int adim){
        this.adimSayisi = adim;
    }
    public int getAdimSayisi (){
        return this.adimSayisi;
    }
    public void setStartPoint(Point start){
        this.start = start;
    }
    
    public int getHamleMaliyet(){
        return this.hamleMaliyet;
    }
    public void setHamleMaliyet(int maliyet){
        this.hamleMaliyet = maliyet;
    }
     public int getHedefBerlirlemeMaliyet(){
        return this.hedefBelirlemeMaliyet;
    }
     public void setHedefBelirlemeMaliyet(int maliyet){
        this.hedefBelirlemeMaliyet = maliyet;
    }
     public void setHedefAldi(boolean aldi){
        this.hedefvar = aldi;
     }
    public boolean getHedef(){
       return this.hedefvar;
    }
    public void setToplamAdim(int adim){
        this.toplamAdim = adim;
    }
    public int getToplamAdim(){
        return this.toplamAdim;
    }
     public void setHarcananAltinMiktari(int miktar){
        this.harcananAltinMiktari = miktar;
    }
    public int getHarcananAltinMiktari(){
        return this.harcananAltinMiktari;
    }
    public void setAltinMiktari(int altinmiktari){
        this.altinmiktari = altinmiktari;
    }
    public int getAltinMiktari(){
        return this.altinmiktari;
    }
    public int getToplananAltinMiktari(){
        return this.toplananAltinMiktari;
    }
    public void setToplananAltinMiktari(int toplananAltinMiktari){
        this.toplananAltinMiktari = toplananAltinMiktari;
    }
    public void setGidilenYollar(Point n){
        this.playeragittigiyollar.add(n);
    }
    public ArrayList<Point> getGidilenYollar(){
        return this.playeragittigiyollar;
    }
    
    public int mesafe(Point a,Point b){
        return  Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
    }

 
 }

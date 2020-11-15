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


public class Node {
    public Point p;

 
    private int altinmiktari;
    private int galtinmiktari;
    private boolean altin,galtin,galtingorunurluk,cgaltingorunurluk;


    public Node(int x, int y) {
        this.p = new Point(x,y);
    }

    public boolean isAltin() {
        return altin;
    }
    public boolean isGAltin(){
        return galtin;
    }
    public int isAltinMiktari(){
        return altinmiktari;
    }
    public int isGAltinMiktari(){
        return galtinmiktari;
    }
    public boolean isGAltinGorunurluk(){
        return galtingorunurluk;
    }
    public void setAltin(boolean altin) {
        this.altin = altin;
    }
    public void setGAltin(boolean galtin){
        this.galtin = galtin;
    }
    public void setGAltinGorunurluk(boolean galtin){
        this.galtingorunurluk = galtin;
    }
    public void setCGAltinGorunurluk(boolean galtin){
        this.cgaltingorunurluk = galtin;
    }
    public void setAltinMiktari(int miktar){
        this.altinmiktari = miktar;
    }
    public void setGAltinMiktari(int miktar){
        this.galtinmiktari = miktar;
    }
    public int getAltinMiktari(){
        return this.altinmiktari;
    }
    public int getGAltinMiktari(){
        return this.galtinmiktari;
    }
    public boolean getGAltinGorunurluk(){
        return this.galtingorunurluk;
    }
     public boolean getCGAltinGorunurluk(){
        return this.cgaltingorunurluk;
    }
    
}
  


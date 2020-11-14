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

    //Distance to start
    private int G;
    //Distance to end
    private int H;

    private boolean visited;
    private int altinmiktari;
    private int galtinmiktari;
    private boolean altin,galtin,galtingorunurluk,cgaltingorunurluk;

    private Node parent;

    public Node(int x, int y) {
        this.p = new Point(x,y);
        this.parent = null;
        this.visited = false;
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
    
    
    public int F(){
        return H+G;
    }

    public int getG() {
        return G;
    }

    public void setG(int g) {
        G = g;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public int getH() {
        return H;
    }

    public void setH(int h) {
        H = h;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
}


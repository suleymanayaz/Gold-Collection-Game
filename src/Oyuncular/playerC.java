/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Oyuncular;

import altintoplamaoyunu.OyunUI;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JLabel;
import struct.Oyun;

/**
 *
 * @author AYAZ
 */
public class playerC extends Player{
    
     private int hamleMaliyet = 5,hedefBelirlemeMaliyet = 15,altinmiktari = 200,toplamAdim,harcananAltinMiktari,toplananAltinMiktari,adimSayisi = 3;
     public boolean hedefvar,gameoverC;
     private Point start,end,current,last;

     ArrayList<Point> playeragittigiyollar = new ArrayList<>();
     ArrayList<Point> yollar,neig,neighbors;
     JLabel altintext;
     public playerC(Point start){
        this.start = start;
        this.hedefvar = true;
        this.gameoverC = false;
        this.toplamAdim = 0;
        this.harcananAltinMiktari = 0;
        this.toplananAltinMiktari = 0;
    }
    @Override
    public Point getStartPoint(){
        return this.start;
    }
    @Override
    public Point getEndPoint(){
        return this.end;
    }
    @Override
    public void setAdimSayisi(int adim){
        this.adimSayisi = adim;
    }
    @Override
    public int getAdimSayisi (){
        return this.adimSayisi;
    }
    @Override
    public void setStartPoint(Point start){
        this.start = start;
    }
    public ArrayList<Point> getListe(){
        return yollar;
    }
    @Override
    public void setHedefAldi(boolean aldi){
        this.hedefvar = aldi;
    }
    @Override
    public boolean getHedef(){
       return this.hedefvar;
    }
    @Override
    public int getHamleMaliyet(){
        return this.hamleMaliyet;
    }
    @Override
    public void setHamleMaliyet(int maliyet){
        this.hamleMaliyet = maliyet;
    }
    @Override
    public int getHedefBerlirlemeMaliyet(){
        return this.hedefBelirlemeMaliyet;
    }
    @Override
    public void setHedefBelirlemeMaliyet(int maliyet){
        this.hedefBelirlemeMaliyet = maliyet;
    }
    @Override
    public void setAltinMiktari(int altinmiktari){
        this.altinmiktari = altinmiktari;
    }
    @Override
    public int getAltinMiktari(){
        return this.altinmiktari;
    }
    @Override
    public void setToplamAdim(int adim){
        this.toplamAdim = adim;
    }
    @Override
    public int getToplamAdim(){
        return this.toplamAdim;
    }
    @Override
    public void setHarcananAltinMiktari(int miktar){
        this.harcananAltinMiktari = miktar;
    }
    @Override
    public int getHarcananAltinMiktari(){
        return this.harcananAltinMiktari;
    }
    @Override
    public int getToplananAltinMiktari(){
       return this.toplananAltinMiktari;
    }
    @Override
    public void setToplananAltinMiktari(int toplananAltinMiktari){
        this.toplananAltinMiktari = toplananAltinMiktari;
    }
    @Override
    public void setGidilenYollar(Point n){
        this.playeragittigiyollar.add(n);
    }
    @Override
    public ArrayList<Point> getGidilenYollar(){
        return this.playeragittigiyollar;
    }
    
   public void altinac(Oyun oyun,OyunUI oyunui){
       Point start = new Point(getStartPoint());
       Point galtin = new Point();
       int newMesafe = 0;
       int lastMesafe=0;
       Point aaltin1 = new Point();
       Point aaltin2 = new Point();

       for(int i = 0;i<oyun.getLines();i++){
            for(int j=0;j<oyun.getCols();j++){
                if(oyun.getGrid()[i][j].isHiddenGolden() && !(oyun.getGrid()[i][j].isHiddenGoldenVisible())){
                    galtin = oyun.getGrid()[i][j].p;
                    newMesafe = mesafe(start,galtin);
                    aaltin1 = galtin;
                    if(lastMesafe == 0){
                          lastMesafe = newMesafe;
                          aaltin2 = galtin;
                    }
                    else{
                          if(lastMesafe >= newMesafe){
                             lastMesafe = newMesafe;
                             aaltin2 = galtin;
                            }
                    }
                }
            }
        }
       

       if(aaltin2.x!=0&&aaltin2.y!=0){
           oyun.getGrid()[aaltin2.x][aaltin2.y].setHiddenGoldenVisible(true);
           altintext = new JLabel();
           altintext.setText(Integer.toString(oyun.getGrid()[aaltin2.x][aaltin2.y].getHiddenGoldenAmount()));
           oyunui.grid[aaltin2.x][aaltin2.y].add(altintext);
       }     
       
       
       
      /*
       oyun.getGrid()[aaltin2.x][aaltin2.y].setCGAltinGorunurluk(true);
      
       altintext.setText(Integer.toString(oyun.getGrid()[aaltin2.x][aaltin2.y].getGAltinMiktari()));
       oyunui.grid[aaltin2.x][aaltin2.y].add(altintext);
       */
       
   }
   

   
   
    public void hedefBelirle(Oyun oyun,OyunUI oyunui){
       Point altin = new Point();
       Random rn = new Random();
       ArrayList<Point> enyakinaltinlar = new ArrayList<>();
       end = new Point();
       int lastMesafe = 0,newMesafe = 0,hesapaltinmiktari = 0;
       float kar1 = 0.f,kar = 0.f,hesap=0.f;
       int counter = 2;
       while(counter !=2){
           altinac(oyun,oyunui);
           counter++;
       }
       if(hedefvar && ((altinmiktari-hedefBelirlemeMaliyet) >= 0) ){
         
           for(int i = 0;i<oyun.getLines();i++){
                for(int j=0;j<oyun.getCols();j++){
                    if(oyun.getGrid()[i][j].isGold() || (oyun.getGrid()[i][j].getHiddenGoldenVisible() && oyun.getGrid()[i][j].isHiddenGolden())){
                        altin = oyun.getGrid()[i][j].p;
                        hesapaltinmiktari = oyun.getGrid()[i][j].getGoldAmount();
                        newMesafe = mesafe(start,altin);
                        // üce kadar 5 maliyet  ucten sonra mesafe bolu uc carpı 5
                        if(newMesafe <= getAdimSayisi() ){
                               hesap = hamleMaliyet;
                        }else{
                              hesap = (newMesafe/(float)getAdimSayisi())*hamleMaliyet;
                        }
                        kar  = hesapaltinmiktari-hesap;
                        if(kar1==0.f){
                             kar1 = kar;
                             //end.x= altin.x;end.y=altin.y;
                        }
                        else{
                            if(kar1 < kar){
                                kar1 = kar;
                                end.x= altin.x;end.y=altin.y;
                            }
                        }
                    }
                }
            }
            if(end.x==0&&end.y==0){
                end = getStartPoint();
                gameoverC = true;
            }
            if(!gameoverC){
                setHarcananAltinMiktari(getHarcananAltinMiktari()+hedefBelirlemeMaliyet);
                altinmiktari = altinmiktari-hedefBelirlemeMaliyet;
            }
        }
        else{
            if(altinmiktari-hedefBelirlemeMaliyet < 0){
               end = getStartPoint();
               System.out.println("Parası bitti C hedef belirleyemiyor .");
               gameoverC =true;
            }
        }

   }
     


   public void yollarıbul(Point start,Point end,Oyun oyun){
        Random rn = new Random();
        yollar = new ArrayList<>();
        current = start;last=end;
        yollar.add(current);
        int hareket = getAdimSayisi();
        neig = new ArrayList<>();
        if(mesafe(current,last)<=getAdimSayisi()){
            while(!current.equals(end)){
                neig = komsular3(current,hareket,oyun);
                for(Point n : neig){
                    if(rn.nextBoolean()){
                       if(!yollar.contains(n)){
                           yollar.add(n);
                           current = n;
                           hareket--;
                           break;
                       }
                    }
               }
            }
            setHedefAldi(true);
        }
        else{
            hareket = getAdimSayisi();
            while(hareket != 0){
                neig = komsular(current,hareket,oyun);
                for(Point n : neig){
                    if(rn.nextBoolean()){
                       if(!yollar.contains(n) ){
                           yollar.add(n);
                           current = n;
                           hareket--;
                           break;
                        }
                    }
                }
            }
            setHedefAldi(false);
        } 
    }
    
    
    public ArrayList<Point> komsular(Point current,int hareket,Oyun oyun){
        Point up = null,down = null,left = null,right = null;
        neighbors = new ArrayList<>();
        if(current.x-1 >=0)
            up = new Point(current.x-1,current.y);
        if (current.x + 1 < oyun.getLines())
            down = new Point(current.x + 1, current.y);
        if (current.y - 1 >= 0)
            left = new Point(current.x, current.y - 1);
        if (current.y + 1 < oyun.getCols())
            right = new Point(current.x, current.y + 1);

        // asagıdaki kodda ust alt sag sol dan gidince varacagı hedef 
        if (up!=null){
                if(mesafe(up,end) < mesafe(current,end) ){
                     neighbors.add(up);
           }
        }
        if (down !=null){
               if(mesafe(down,end) < mesafe(current,end) ){
                    neighbors.add(down);
               }
        }
        if (left !=null){
                if(mesafe(left,end) < mesafe(current,end) ){
                   neighbors.add(left);
             }
        }
        if (right != null){
                if(mesafe(right,end) < mesafe(current,end) ){
                    neighbors.add(right);
                }
        }
        return neighbors;
    }
   
     public ArrayList<Point> komsular3(Point current,int hareket,Oyun oyun){
         Point up = null,down = null,left = null,right = null;
        neighbors = new ArrayList<>();
        if(current.x-1 >=0)
            up = new Point(current.x-1,current.y);
        if (current.x + 1 < oyun.getLines())
            down = new Point(current.x + 1, current.y);

        if (current.y - 1 >= 0)
            left = new Point(current.x, current.y - 1);
        if (current.y + 1 < oyun.getCols())
            right = new Point(current.x, current.y + 1); 
        // asagıdaki kodda ust alt sag sol dan gidince varacagı hedef 
        if (up!=null){
           if(mesafe(up,end) < hareket )
            neighbors.add(up);
        }
        if (down !=null){
           if(mesafe(down,end) < hareket )
            neighbors.add(down);
        }
        if (left !=null){
         if(mesafe(left,end) < hareket )
            neighbors.add(left);
        }
            
        if (right != null){
            if(mesafe(right,end) < hareket )
                  neighbors.add(right);
        }
        if(neighbors.size()>=2){
            if(neighbors.contains(right)){
                 if(mesafe(current,end)<mesafe(right,end))
                 neighbors.remove(right);
            }
            if(neighbors.contains(left)){
                 if(mesafe(current,end)<mesafe(left,end))
                 neighbors.remove(left);
            }

            if(neighbors.contains(up)){
                 if(mesafe(current,end)<mesafe(up,end))
                 neighbors.remove(up);
            }
              if(neighbors.contains(down)){
                 if(mesafe(current,end)<mesafe(down,end))
                 neighbors.remove(down);
            }

        }
        return neighbors;
    }

   


   
}

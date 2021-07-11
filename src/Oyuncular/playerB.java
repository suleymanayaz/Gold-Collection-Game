/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Oyuncular;

import java.awt.Point;
import java.util.ArrayList;
import struct.*;
import java.util.Random;

/**
 *
 * @author AYAZ
 */
public class playerB extends Player {
     private int hamleMaliyet = 5,hedefBelirlemeMaliyet = 10,altinmiktari = 200,toplamAdim,harcananAltinMiktari,toplananAltinMiktari,adimSayisi = 3;
     public boolean hedefvar,gameoverB;
     private Point start,end,current,last;
     ArrayList<Point> playeragittigiyollar = new ArrayList<>();
     ArrayList<Point> yollar,neig,neighbors;

    public playerB(Point start){
         this.start = start;
         this.hedefvar = true;
         this.gameoverB = false;
         this.toplamAdim = 0;
         this.harcananAltinMiktari  = 0;
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
    public void setToplamAdim(int adim){
        this.toplamAdim = adim;
    }
    @Override
    public int getToplamAdim(){
        return this.toplamAdim;
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
    
   public void hedefBelirle(Oyun oyun){
       Point altin = new Point();
       ArrayList<Point> enyakinaltinlar = new ArrayList<>();
       end = new Point();
      
       int lastMesafe = 0,newMesafe = 0,hesapaltinmiktari=0,hamle = 5;
       float kar1 = 0.f,kar=0.f,hesap = 0.f;

       if(hedefvar && ((altinmiktari-hedefBelirlemeMaliyet) >= 0) ){
           for(int i = 0;i<oyun.getLines();i++){
                for(int j=0;j<oyun.getCols();j++){
                    if(oyun.getGrid()[i][j].isGold() || (oyun.getGrid()[i][j].getHiddenGoldenVisible() && oyun.getGrid()[i][j].isHiddenGolden())){
                         altin = oyun.getGrid()[i][j].p;
                         hesapaltinmiktari = oyun.getGrid()[i][j].getGoldAmount();
                         newMesafe = mesafe(start,altin);
                         // üce kadar 5 maliyet  ucten sonra mesafe bolu uc carpı 5
                         if(newMesafe <= getAdimSayisi() ){
                               hesap = hamle;
                         }else{
                              hesap = (newMesafe/(float)getAdimSayisi())*hamle;
                         }
                         kar  = hesapaltinmiktari-hesap;
                         if(kar1==0.f){
                             kar1 = kar;
                             end.x= altin.x;end.y=altin.y;
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
                gameoverB = true;
            }
            if(!gameoverB){
                setHarcananAltinMiktari(getHarcananAltinMiktari()+hedefBelirlemeMaliyet);
                altinmiktari = altinmiktari-hedefBelirlemeMaliyet;
            }
        }else{
           if(altinmiktari-hedefBelirlemeMaliyet < 0){
                end = getStartPoint();
                System.out.println("Parası bitti B hedef belirleyemiyor .");
                gameoverB =true;
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

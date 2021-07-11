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
import struct.*;


/**
 *
 * @author AYAZ
 */
public class playerA extends Player {

     private int moveCost = 5,targetCost = 5,goldAmount = 200 ,goldAmountSpent,totalNumberSteps,amountGoldCollected,stepsNumber=3;
     private  boolean haveGoldBool ,haveTarget;
     private Point start,end;
     private ArrayList<Point> playerPassedRotaArrayList = new ArrayList<>();
     ArrayList<Point> ways,closeNeighbors,farNeighbors;
     Point current,current2,last;
   
    public playerA(Point start ){
         this.start = start;
         this.haveTarget = true;
         this.haveGoldBool = false;
         this.totalNumberSteps = 0;
         this.goldAmountSpent= 0;
         this.amountGoldCollected = 0;
     }
 
    public ArrayList<Point> getListe(){
        return ways;
    }
    
     @Override
    public Point getStartPoint(){
        return this.start;
    }
     
    public Point getEndPoint(){
        return this.end;
    }
     @Override
    public void setStepsNumber (int stepsNumber ){
        this.stepsNumber  = stepsNumber ;
    }
     @Override
    public int getStepsNumber  (){
        return this.stepsNumber;
    }
     @Override
    public void setStartPoint(Point start){
        this.start = start;
    }
    
     @Override
    public int getMoveCost(){
        return this.moveCost;
    }
    public void setMoveCost(int moveCost){
        this.moveCost = moveCost;
    }
     @Override
     public int getTargetCost(){
        return this.targetCost;
    }
     @Override
     public void setTargetCost(int targetCost){
        this.targetCost = targetCost;
    }
     @Override
     public void setHaveTarget(boolean haveTarget){
        this.haveTarget = haveTarget;
     }
     @Override
    public boolean getHaveTarget(){
       return this.haveTarget;
    }
     @Override
    public void setTotalNumberSteps(int totalNumberSteps){
        this.totalNumberSteps = totalNumberSteps;
    }
    public int getTotalNumberSteps(){
        return this.totalNumberSteps;
    }
     public void setGoldAmountSpent(int goldAmountSpent){
        this.goldAmountSpent = goldAmountSpent;
    }
    public int getGoldAmountSpent(){
        return this.goldAmountSpent;
    }
    public void setGoldAmount(int goldAmount){
        this.goldAmount = goldAmount;
    }
    public int getGoldAmount(){
        return this.goldAmount;
    }
    
    public int getAmountGoldCollected(){
        return this.amountGoldCollected;
    }
    public void setAmountGoldCollected(int amountGoldCollected){
        this.amountGoldCollected = amountGoldCollected;
    }
    
    public void setPlayerPassedRotaArrayList(Point n){
        this.playerPassedRotaArrayList.add(n);
    }
    public ArrayList<Point> getPlayerPassedRotaArrayList(){
        return this.playerPassedRotaArrayList;
    }
    
  
   
  
    
    
    public void hedefBelirle(Oyun oyun){

        Point altin = new Point();
        ArrayList<Point> enyakinaltinlar = new ArrayList<>();
        end = new Point();
        int lastMesafe=0;
        int newMesafe = 0;

        if(haveTarget && ((goldAmount-moveCost) >= 0)){
            
            for(int i = 0;i<oyun.getLines();i++){
                for(int j=0;j<oyun.getCols();j++){
                    if(oyun.getGrid()[i][j].isGold()|| (oyun.getGrid()[i][j].getHiddenGoldenVisible() && oyun.getGrid()[i][j].isHiddenGolden())){
                        altin = oyun.getGrid()[i][j].p;
                        newMesafe = mesafe(start,altin);
                        if(lastMesafe == 0){
                            lastMesafe = newMesafe;
                            end.x=altin.x;end.y=altin.y;
                        }
                         else{
                                if(lastMesafe >= newMesafe){
                                 lastMesafe = newMesafe;
                                 end.x = altin.x;end.y=altin.y;
                                }
                        }

                    }
                }
            }
            if(end.x==0&& end.y == 0){
                 end = getStartPoint();
                 setHaveGoldBool(true);
                
             }
            if(!isHaveGoldBool()){
                setGoldAmountSpent(getGoldAmountSpent()+targetCost);
                goldAmount = goldAmount - targetCost;
            }
          
        }
        else{
            if(goldAmount-targetCost < 0){
                end = getStartPoint();
                System.out.println("Player A out of gold ");
                setHaveGoldBool(true);
            }    
        }
      
    }
 
    public void yollarıbul(Point start,Point end,Oyun oyun){
        Random rn = new Random();
        ways = new ArrayList<>();
        current = start;last=end;
        ways.add(current);
        int hareket = getStepsNumber();
        closeNeighbors = new ArrayList<>();
        
        if(mesafe(current,last)<=getStepsNumber() ){
             while(!current.equals(end)){
                closeNeighbors = komsular3(current,hareket,oyun);
                for(Point n : closeNeighbors){
                    if(rn.nextBoolean()){
                       if(!ways.contains(n)){
                           ways.add(n);
                           current = n;
                           hareket--;
                           break;
                       }
                    }
               }
             }
            setHaveTarget(true);
            
        }
        else{
            hareket = getStepsNumber();
            while(hareket != 0){
                closeNeighbors = komsular(current,hareket,oyun);
                for(Point n : closeNeighbors){
                    if(rn.nextBoolean()){
                       if(!ways.contains(n) ){
                           ways.add(n);
                           current = n;
                           hareket--;
                           break;
                       }
                    }
                }
            }
            setHaveTarget(false);        
        }   
         
    }
    
   
    
    
    public ArrayList<Point> komsular(Point current,int hareket,Oyun oyun){
        Point up = null,down = null,left = null,right = null;
        farNeighbors = new ArrayList<>();
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
                     farNeighbors.add(up);
           }
        }
        if (down !=null){
               if(mesafe(down,end) < mesafe(current,end) ){
                    farNeighbors.add(down);
               }
        }
        if (left !=null){
             if(mesafe(left,end) < mesafe(current,end) ){
                   farNeighbors.add(left);
             }
        }
            
        if (right != null){
                if(mesafe(right,end) < mesafe(current,end) ){
                   farNeighbors.add(right);
                }
        }
        return farNeighbors;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public ArrayList<Point> komsular3(Point current,int hareket,Oyun oyun){
        Point up = null,down = null,left = null,right = null;
        farNeighbors = new ArrayList<>();
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
            farNeighbors.add(up);
        }
        if (down !=null){
           if(mesafe(down,end) < hareket )
            farNeighbors.add(down);
        }
        if (left !=null){
         if(mesafe(left,end) < hareket )
            farNeighbors.add(left);
        }
            
        if (right != null){
            if(mesafe(right,end) < hareket )
                  farNeighbors.add(right);
        }
        if(farNeighbors.size()>=2){
            if(farNeighbors.contains(right)){
                 if(mesafe(current,end)<mesafe(right,end))
                 farNeighbors.remove(right);
            }
           
              if(farNeighbors.contains(left)){
                 if(mesafe(current,end)<mesafe(left,end))
                 farNeighbors.remove(left);
            }

            if(farNeighbors.contains(up)){
                 if(mesafe(current,end)<mesafe(up,end))
                 farNeighbors.remove(up);
            }
            
              if(farNeighbors.contains(down)){
                 if(mesafe(current,end)<mesafe(down,end))
                 farNeighbors.remove(down);
            }

        }
        return farNeighbors;
    }

}

  

package Oyuncular;


import java.awt.Point;
import java.util.ArrayList;

import struct.*;



    // StepsNumber   TargetCost moveCost InitGoldMount
public class Player{
     private int moveCost,targetCost,goldAmount,goldAmountSpent,totalNumberSteps,amountGoldCollected,stepsNumber ;
     private Point start,end;
     private  boolean haveGoldBool = false,haveTarget;
     private ArrayList<Point> playerPassedRotaArrayList ;
     Oyun game;
     ArrayList<Node> ways;
    public Point getStartPoint(){
        return this.start;
    }
     
    public Point getEndPoint(){
        return this.end;
    }
    public void setStepsNumber (int stepsNumber ){
        this.stepsNumber  = stepsNumber ;
    }
    public int getStepsNumber  (){
        return this.stepsNumber ;
    }
    public void setStartPoint(Point start){
        this.start = start;
    }
    
    public int getMoveCost(){
        return this.moveCost;
    }
    public void setMoveCost(int moveCost){
        this.moveCost = moveCost;
    }
     public int getTargetCost(){
        return this.targetCost;
    }
     public void setTargetCost(int targetCost){
        this.targetCost = targetCost;
    }
     public void setHaveTarget(boolean haveTarget){
        this.haveTarget = haveTarget;
     }
    public boolean getHaveTarget(){
       return this.haveTarget;
    }
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
    
    public int mesafe(Point a,Point b){
        return  Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
    }

    /**
     * @return the haveGoldBool
     */
    public boolean isHaveGoldBool() {
        return haveGoldBool;
    }

    /**
     * @param haveGoldBool the haveGoldBool to set
     */
    public void setHaveGoldBool(boolean haveGoldBool) {
        this.haveGoldBool = haveGoldBool;
    }

 
 }

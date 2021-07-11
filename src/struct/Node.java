
package struct;

import java.awt.Point;



public class Node {
    public Point p;

    private int GoldAmount;
    private int HiddenGoldenAmount;
    private boolean goldBool,hiddenGoldenBool,hiddenGoldenVisible;


    public Node(int x, int y) {
        this.p = new Point(x,y);
    }

    public boolean isGold() {
        return goldBool;
    }
    
    public boolean isHiddenGolden(){
        return hiddenGoldenBool;
    }

    public boolean isHiddenGoldenVisible(){
        return hiddenGoldenVisible;
    }
    
    public void setGoldBool(boolean goldBool) {
        this.goldBool = goldBool;
    }

    public void setHiddenGoldenVisible(boolean hiddenGoldenVisible){
        this.hiddenGoldenVisible = hiddenGoldenVisible;
    }
  
    public void setGoldAmount(int miktar){
        this.GoldAmount = miktar;
    }
    public void setHiddenGoldenAmount(int miktar){
        this.HiddenGoldenAmount = miktar;
    }
    public int getGoldAmount(){
        return this.GoldAmount;
    }
    public int getHiddenGoldenAmount(){
        return this.HiddenGoldenAmount;
    }
    public boolean getHiddenGoldenVisible(){
        return this.hiddenGoldenVisible;
    }
    
}
  


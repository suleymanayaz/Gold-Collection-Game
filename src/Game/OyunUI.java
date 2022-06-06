
package Game;
import Oyuncular.*;
import struct.*;



import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.util.ArrayList;


public class OyunUI extends JPanel  {
    public NodeUI[][] grid;
    private Game oyun;
    private GridBagConstraints gbc;
    private GridBagLayout gbl;
    private Node currentNode;
    playerA oyuncuA;
    Color dbg;
    public JLabel goldLabel,playerALabel,playerBLabel,playerCLabel,playerDLabel;
 
    
    public OyunUI(Game oyun){
        this.oyun = oyun;
        gbl = new GridBagLayout();
        setLayout(gbl);
        this.grid= new NodeUI[oyun.getLines()][oyun.getCols()];
        gbc = new GridBagConstraints();
        dbg = getBackground();
        reset();
        paint(getGraphics());
        
        
    }
    
    public NodeUI[][] getGridUI(){
        return this.grid;
    }
    
    public void reset(){
        NodeUI n;
        for(int i = 0;i < oyun.getLines(); i++){
            for(int j = 0;j < oyun.getCols(); j++){
                if(grid[i][j] == null){
                    n = new NodeUI(oyun.getGrid()[i][j].getP());
                    grid[i][j] = n;
                }
                gbc.gridx = j;
                gbc.gridy = i; 
                setBackground(dbg);
               
                if(oyun.getGrid()[i][j].getP().equals(oyun.getStart())){
                    playerALabel = new JLabel();
                    playerALabel.setText("A");
                    grid[i][j].add(playerALabel);
                    
                 
                }
                else if (oyun.getGrid()[i][j].getP().equals(oyun.getStartB())){
                    playerBLabel = new JLabel();
                    playerBLabel.setText("B");
                    grid[i][j].add(playerBLabel);
                }
                else if (oyun.getGrid()[i][j].getP().equals(oyun.getStartC())){
                    playerCLabel = new JLabel();
                    playerCLabel.setText("C");
                    grid[i][j].add(playerCLabel);
                }
                else if (oyun.getGrid()[i][j].getP().equals(oyun.getStartD())){
                    playerDLabel = new JLabel();
                    playerDLabel.setText("D");
                    grid[i][j].add(playerDLabel);
                }
               
                else if (oyun.getGrid()[i][j].isGold()&& (oyun.getGrid()[i][j].getP().equals(oyun.getStart()) == false)){
                    goldLabel = new JLabel();
                    goldLabel.setText(Integer.toString(oyun.getGrid()[i][j].getGoldAmount()));
                    grid[i][j].add(goldLabel);
                  
                }
                else if (oyun.getGrid()[i][j].isHiddenGolden()){
                    //grid[i][j].setBackground(Color.YELLOW);
                    oyun.getGrid()[i][j].setHidden_Golden_Visible(false);
                }
                else
                    grid[i][j].setBackground(dbg);
                
                Border border = null;
                if( i < oyun.getLines() - 1){
                    if(j < oyun.getCols() - 1){
                        border = new MatteBorder(1, 1, 0, 0, Color.GRAY);
                    } else{
                        border = new MatteBorder(1, 1, 0, 1, Color.GRAY);
                    }         
                }
                else{
                    if(j < oyun.getCols() -1){
                        border = new MatteBorder(1, 1, 1, 0, Color.GRAY);
                    } else{
                        border = new MatteBorder(1, 1, 1, 1, Color.GRAY);
                    }
                }
                grid[i][j].setBorder(border);
                add(grid[i][j],gbc);            
            }
        }
    }
    
    public void hareketA (playerA player){
     
       if((player.getGoldAmount()-player.getMoveCost())>=0 && (player.isHaveGoldBool()==false)){
         
           player.setGoldAmountSpent(player.getGoldAmountSpent()+player.getMoveCost());
       
        ArrayList <Point> list ;
        
        list= player.getListe();
     
        Point start = new Point();
        start = player.getStartPoint();
        Point end  = new Point();
        grid[start.x][start.y].remove(playerALabel);
        for(Point n : list){
            
            player.setPlayerPassedRotaArrayList(n);
            player.setTotalNumberSteps(player.getStepsNumber()+1);
            grid[n.x][n.y].add(playerALabel); 
            grid[n.x][n.y].setBackground(Color.BLUE);
            this.paintAll(getGraphics());
            try {
                Thread.sleep(200); // delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            }   
            if(oyun.getGrid()[n.x][n.y].isHiddenGolden()){
                if(oyun.getGrid()[n.x][n.y].getHiddenGoldenVisible()){
                    player.setGoldAmount(player.getGoldAmount()+ oyun.getGrid()[n.x][n.y].getHiddenGoldenAmount()) ;
                    player.setAmountGoldCollected(player.getAmountGoldCollected()+oyun.getGrid()[n.x][n.y].getHiddenGoldenAmount());
                    oyun.getGrid()[n.x][n.y].setHidden_Golden_Visible(false);
                    grid[n.x][n.y].removeAll();
                }
                else{
                    oyun.getGrid()[n.x][n.y].setHidden_Golden_Visible(true);
                    goldLabel = new JLabel();
                    goldLabel.setText(Integer.toString(oyun.getGrid()[n.x][n.y].getHiddenGoldenAmount()));
                    grid[n.x][n.y].add(goldLabel);
                }
    
            }  
            if(oyun.getGrid()[n.x][n.y].isGold()){
                player.setGoldAmount(player.getGoldAmount()+ oyun.getGrid()[n.x][n.y].getGoldAmount()) ;
                player.setAmountGoldCollected(player.getAmountGoldCollected()+oyun.getGrid()[n.x][n.y].getGoldAmount());
                oyun.getGrid()[n.x][n.y].setGoldBool(false); 
                
                grid[n.x][n.y].removeAll(); 
            }    
            grid[n.x][n.y].setBackground(dbg);
           
            end = n;
        }
        player.setTotalNumberSteps(player.getStepsNumber()-1);
        player.setGoldAmount(player.getGoldAmount()-player.getMoveCost()); 
      
        grid[end.x][end.y].add(playerALabel);
        player.setStartPoint(end);
        player.getPlayerPassedRotaArrayList().remove(player.getPlayerPassedRotaArrayList().size()-1);

       }
       else{
           if(!player.isHaveGoldBool()){
                System.out.println("Game over Player A  : "+player.getGoldAmount());
                player.setHaveGoldBool(true);
           }
           
        }         
    }

   

public void hareketB (playerB player){
     
      
    if((player.getGoldAmount()-player.getMoveCost())>=0  && (player.isHaveGoldBool()==false)){
           player.setGoldAmountSpent(player.getGoldAmountSpent()+player.getMoveCost());
        ArrayList <Point> list ;
     
        list= player.getListe();
        Point start = new Point();
        start = player.getStartPoint();
        Point end  = new Point();
        grid[start.x][start.y].remove(playerBLabel);
        for(Point n : list){
            player.setPlayerPassedRotaArrayList(n);
            player.setTotalNumberSteps(player.getStepsNumber()+1);
            grid[n.x][n.y].add(playerBLabel);
            grid[n.x][n.y].setBackground(Color.RED); 
            this.paintAll(getGraphics());
            try {
                Thread.sleep(200); // delay
            }catch (InterruptedException e) {
                e.printStackTrace();
            }   
            if(oyun.getGrid()[n.x][n.y].isHiddenGolden()){
                if(oyun.getGrid()[n.x][n.y].getHiddenGoldenVisible()){
                player.setGoldAmount(player.getGoldAmount()+ oyun.getGrid()[n.x][n.y].getHiddenGoldenAmount()) ;
                player.setAmountGoldCollected(player.getAmountGoldCollected()+oyun.getGrid()[n.x][n.y].getHiddenGoldenAmount());
                oyun.getGrid()[n.x][n.y].setHidden_Golden_Visible(false);
                grid[n.x][n.y].removeAll();
                }
                else{
                    oyun.getGrid()[n.x][n.y].setHidden_Golden_Visible(true);
                    goldLabel = new JLabel();
                    goldLabel.setText(Integer.toString(oyun.getGrid()[n.x][n.y].getHiddenGoldenAmount()));
                    grid[n.x][n.y].add(goldLabel);
                }

            } 
            if(oyun.getGrid()[n.x][n.y].isGold()){  
                player.setGoldAmount(player.getGoldAmount()+ oyun.getGrid()[n.x][n.y].getGoldAmount()) ;
                player.setAmountGoldCollected(player.getAmountGoldCollected()+oyun.getGrid()[n.x][n.y].getGoldAmount());
                oyun.getGrid()[n.x][n.y].setGoldBool(false); 
              
                grid[n.x][n.y].removeAll();
            }    
            grid[n.x][n.y].setBackground(dbg);
            grid[n.x][n.y].remove(playerBLabel);
            end = n;
        }
        player.setTotalNumberSteps(player.getStepsNumber()-1);
        player.setGoldAmount(player.getGoldAmount()-player.getMoveCost()); 
        grid[end.x][end.y].add(playerBLabel);
        player.setStartPoint(end);
        player.getPlayerPassedRotaArrayList().remove(player.getPlayerPassedRotaArrayList().size()-1);
    
    }
    else{
        if(!player.isHaveGoldBool()){
        System.out.println("Game Over Player B : "+player.getGoldAmount());
        player.setHaveGoldBool(true);      
        }
        
    }

}
 
public void hareketC (playerC player){
    if((player.getGoldAmount()-player.getMoveCost())>=0  && (player.isHaveGoldBool()==false)){
        player.setGoldAmountSpent(player.getGoldAmountSpent()+player.getMoveCost());
        ArrayList <Point> list ;
        list= player.getListe();
        Point start = new Point();
        start = player.getStartPoint();
        Point end  = new Point();
        grid[start.x][start.y].remove(playerCLabel);
        for(Point n : list){
            player.setPlayerPassedRotaArrayList(n);
            player.setTotalNumberSteps(player.getStepsNumber()+1);
            grid[n.x][n.y].add(playerCLabel);
            grid[n.x][n.y].setBackground(Color.ORANGE); 
            paintAll(getGraphics());
            try {
                Thread.sleep(200); // delay
            } catch (InterruptedException e) {
                System.out.println("e.printStackTrace()");
            }   
            if(oyun.getGrid()[n.x][n.y].isHiddenGolden()){
                if(oyun.getGrid()[n.x][n.y].getHiddenGoldenVisible()){
                player.setGoldAmount(player.getGoldAmount()+ oyun.getGrid()[n.x][n.y].getHiddenGoldenAmount()) ;
                 player.setAmountGoldCollected(player.getAmountGoldCollected()+oyun.getGrid()[n.x][n.y].getHiddenGoldenAmount());
                oyun.getGrid()[n.x][n.y].setHidden_Golden_Visible(false);
                grid[n.x][n.y].removeAll();
                }
                else{
                    oyun.getGrid()[n.x][n.y].setHidden_Golden_Visible(true);
                    goldLabel = new JLabel();
                    goldLabel.setText(Integer.toString(oyun.getGrid()[n.x][n.y].getHiddenGoldenAmount()));
                    grid[n.x][n.y].add(goldLabel);
                }
            }
                   
            if(oyun.getGrid()[n.x][n.y].isGold()){  
                player.setGoldAmount(player.getGoldAmount()+ oyun.getGrid()[n.x][n.y].getGoldAmount()) ;
                player.setAmountGoldCollected(player.getAmountGoldCollected()+oyun.getGrid()[n.x][n.y].getGoldAmount());
                oyun.getGrid()[n.x][n.y].setGoldBool(false); 
               
                grid[n.x][n.y].removeAll(); 
            }    
            grid[n.x][n.y].setBackground(dbg); 
            grid[n.x][n.y].remove(playerCLabel); 
            end = n;
                   
        }
       
        player.setTotalNumberSteps(player.getStepsNumber()-1);
        player.setGoldAmount(player.getGoldAmount()-player.getMoveCost()); 
        grid[end.x][end.y].add(playerCLabel);
        player.setStartPoint(end); 
        player.getPlayerPassedRotaArrayList().remove(player.getPlayerPassedRotaArrayList().size()-1);
    }
    else{
        if(!player.isHaveGoldBool()){
            System.out.println("Game Over Player C: "+player.getGoldAmount());
            player.setHaveGoldBool(true);
        }
        
    }
 }
    
public void hareketD (playerD player){
    
    if((player.getGoldAmount()-player.getMoveCost())>=0  && (player.isHaveGoldBool()==false)){
       player.setGoldAmountSpent(player.getGoldAmountSpent()+player.getMoveCost());
       ArrayList <Point> list ;
       list= player.getListe();
       Point start = new Point();
       start = player.getStartPoint();
       Point end  = new Point();
       grid[start.x][start.y].remove(playerDLabel);
        for(Point n : list){
            player.setPlayerPassedRotaArrayList(n);
            player.setTotalNumberSteps(player.getStepsNumber()+1);
            grid[n.x][n.y].add(playerDLabel);
            grid[n.x][n.y].setBackground(Color.YELLOW); 
            this.paintAll(getGraphics());
            try {
                Thread.sleep(200); // delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            }   
            if(oyun.getGrid()[n.x][n.y].isHiddenGolden()){
                if(oyun.getGrid()[n.x][n.y].getHiddenGoldenVisible()){
                    player.setGoldAmount(player.getGoldAmount()+ oyun.getGrid()[n.x][n.y].getHiddenGoldenAmount()) ;
                    player.setAmountGoldCollected(player.getAmountGoldCollected()+oyun.getGrid()[n.x][n.y].getHiddenGoldenAmount());
                    oyun.getGrid()[n.x][n.y].setHidden_Golden_Visible(false);
                    grid[n.x][n.y].removeAll();
                }
                else{
                    oyun.getGrid()[n.x][n.y].setHidden_Golden_Visible(true);
                    goldLabel = new JLabel();
                    goldLabel.setText(Integer.toString(oyun.getGrid()[n.x][n.y].getHiddenGoldenAmount()));
                    grid[n.x][n.y].add(goldLabel);
                }
            }
            if(oyun.getGrid()[n.x][n.y].isGold()){ 
                player.setGoldAmount(player.getGoldAmount()+ oyun.getGrid()[n.x][n.y].getGoldAmount()) ;
                player.setAmountGoldCollected(player.getAmountGoldCollected()+oyun.getGrid()[n.x][n.y].getGoldAmount());
                oyun.getGrid()[n.x][n.y].setGoldBool(false); 
                
                grid[n.x][n.y].removeAll();
            }    
            grid[n.x][n.y].setBackground(dbg);
            grid[n.x][n.y].remove(playerDLabel);
            end = n;
        }
        player.setTotalNumberSteps(player.getStepsNumber()-1);
        player.setGoldAmount(player.getGoldAmount()-player.getMoveCost()); 
        grid[end.x][end.y].add(playerDLabel); 
        player.setStartPoint(end); 
        player.getPlayerPassedRotaArrayList().remove(player.getPlayerPassedRotaArrayList().size()-1);
    }
    else{
        if(!player.isHaveGoldBool()){
            System.out.println("Game Over Player D: "+player.getGoldAmount());
            player.setHaveGoldBool(true);
        }
        

    }

}

   
}

package Game;

import Oyuncular.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.NumberFormat;
import struct.*;

public class Board extends JFrame implements ActionListener {
    int boardx = 20,boardy=20,goldenRatio=20,hiddenGoldenRatio=10;
    JPanel game,settings,generate,result,results,players;
    JFormattedTextField boardRow, boardCol,
    playerStepsNumber,playerAtargetCost,playerAmoveCost, playerAinitGoldMount,
    playerBtargetCost,playerBmoveCost,playerBinitGoldMount, 
    playerCtargetCost,playerCmoveCost,playerCinitGoldMount,
    playerDinitGoldMount,playerDtargetCost,playerDmoveCost,
    goldenRationText,hiddenGoldenRationText;
   
    JButton boardSettingsButton,playerSettingsButton, gameStartButton;
    
    JLabel gameResultLabel,winnerLabel, boardColLabel, boardRowLabel,
           playerNameLabel,playerTargetCostLabel,playerMoveCostLabel,playerinitGoldMountLabel,playerStepsNumberLabel,
           goldenRationLabel,hiddenGoldenRationLabel;
    
    Point startA,startB,startC,startD;
    Player Player = new Player();
    playerA PlayerA;
    playerB PlayerB;
    playerC PlayerC;
    playerD PlayerD;
    Game gameBoard;
    OyunUI gameUI;
    File file;
   
  
    public static void main(String []args){
        new Board();
    }
    
    public Board(){
        setLayout(new FlowLayout(0,1,0));        
        setTitle("Gold Collection Game");
        game = new JPanel();
     
        gameBoard = new Game(boardx,boardy,goldenRatio,hiddenGoldenRatio);
        startA = new Point(0,0);
        PlayerA = new playerA(startA);
        PlayerA.hedefBelirle(gameBoard);
        startB = new Point(0,boardy-1);
        PlayerB = new playerB(startB);
        PlayerB.hedefBelirle(gameBoard);
        startC = new Point(boardx-1,boardy-1);
        PlayerC = new playerC(startC);
        startD = new Point(boardx-1,0);
        PlayerD = new playerD(startD);
        gameUI = new OyunUI(gameBoard);
        PlayerC.hedefBelirle(gameBoard,gameUI);
        game.add(gameUI);
        add(game);
        createSettings();
        add(settings);
        setMinimumSize(new Dimension(1366,768));
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    public void fileWrite(Player player){
        try{
            FileWriter fw = new FileWriter(file);
            fw.write("\n:"+player.getStepsNumber()+"\r\n");
            fw.write("Player passes : { ");
            for(Point n : player.getPlayerPassedRotaArrayList()){
                fw.write("("+n.x+","+n.y+") ");
            }
            fw.write("}"+"\r\n");
            fw.write("Total number of steps:"+player.getAmountGoldCollected()+"\r\n");
            fw.write("Amount of gold  spent:"+player.getGoldAmountSpent()+"\r\n");
            fw.write("amount of gold in the case"+(player.getGoldAmount()+"\r\n"));
            fw.close();
        }catch(IOException e){
            System.out.println("While File writing , Uppss File IOexception ");
        }
    }
     public void createSettings(){
        settings = new JPanel();
        settings.setLayout(new BoxLayout(settings, BoxLayout.Y_AXIS));
        settings.setBackground(Color.YELLOW);

        //creating settings
        generate = new JPanel();
        generate.setLayout(new FlowLayout());
        boardSettingsButton = new JButton("Set");
        boardSettingsButton.addActionListener(this);
        boardRow = new JFormattedTextField(NumberFormat.getNumberInstance());
        boardRow.setValue(20L);
        boardRow.setPreferredSize(boardSettingsButton.getPreferredSize());
        boardRowLabel = new JLabel("Line : ");
        boardCol = new JFormattedTextField(NumberFormat.getNumberInstance());
        boardCol.setValue(20L);
        boardCol.setPreferredSize(boardSettingsButton.getPreferredSize());
        boardColLabel = new JLabel("Column : ");

        JPanel dimensions= new JPanel();
        dimensions.setLayout(new FlowLayout());
        JPanel goldAmountPanel = new JPanel();
        goldAmountPanel.setLayout(new FlowLayout());
        JPanel hiddenGoldAmountPanel = new JPanel();
        hiddenGoldAmountPanel.setLayout(new FlowLayout());

        dimensions.add(boardRowLabel);
        dimensions.add(boardRow);
        dimensions.add(boardColLabel);
        dimensions.add(boardCol);
        goldenRationLabel = new JLabel("Golden Ration Number :");
        goldenRationText = new JFormattedTextField(NumberFormat.getNumberInstance());
        goldenRationText.setValue(20L);
        goldenRationText.setPreferredSize(boardSettingsButton.getPreferredSize());
        goldAmountPanel.add(goldenRationLabel);
        goldAmountPanel.add(goldenRationText);
        
        hiddenGoldenRationLabel = new JLabel("Golden Ration Number :");
        hiddenGoldenRationText= new JFormattedTextField(NumberFormat.getNumberInstance());
        hiddenGoldenRationText.setPreferredSize(boardSettingsButton.getPreferredSize());
        hiddenGoldenRationText.setValue(10L);
        hiddenGoldAmountPanel.add(hiddenGoldenRationLabel);
        hiddenGoldAmountPanel.add(hiddenGoldenRationText);
        
        JPanel settingPanel = new JPanel();
        settingPanel.setLayout(new BoxLayout(settingPanel, BoxLayout.Y_AXIS));
        settingPanel.add(dimensions);
        settingPanel.add(goldAmountPanel);
        settingPanel.add(hiddenGoldAmountPanel);
        settingPanel.add(boardSettingsButton);

        generate.add(settingPanel);
        generate.setBorder(BorderFactory.createTitledBorder("Settings"));
        
        players = new JPanel();
        players .setLayout(new FlowLayout());
        playerSettingsButton =  new JButton("Set");
        playerSettingsButton.addActionListener(this);
        
        playerAtargetCost =  new JFormattedTextField(NumberFormat.getNumberInstance());
        playerAtargetCost.setValue(5L);
        playerAtargetCost.setPreferredSize(playerSettingsButton.getPreferredSize());
        playerNameLabel = new JLabel("A :");
        playerTargetCostLabel = new JLabel("Target Cost");
        playerAmoveCost =  new JFormattedTextField(NumberFormat.getNumberInstance());
        playerAmoveCost.setValue(5L);
        playerAmoveCost.setPreferredSize(playerSettingsButton.getPreferredSize());
        playerMoveCostLabel= new JLabel("Move Cost");
        playerAinitGoldMount =new JFormattedTextField(NumberFormat.getNumberInstance());
        playerAinitGoldMount.setValue(200L);
        playerAinitGoldMount.setPreferredSize(playerSettingsButton.getPreferredSize());
        playerinitGoldMountLabel = new JLabel("Gold Amount :");
        JPanel playerApanel= new JPanel();
        playerApanel.setLayout(new FlowLayout());
        playerApanel.add(playerNameLabel);
        playerApanel.add(playerTargetCostLabel);
        playerApanel.add(playerAtargetCost);
        playerApanel.add(playerMoveCostLabel);
        playerApanel.add(playerAmoveCost);
        playerApanel.add(playerinitGoldMountLabel);
        playerApanel.add(playerAinitGoldMount);
          
        playerBtargetCost =  new JFormattedTextField(NumberFormat.getNumberInstance());
        playerBtargetCost.setValue(10L);
        playerBtargetCost.setPreferredSize(playerSettingsButton.getPreferredSize());
        playerNameLabel = new JLabel("B :");
        playerTargetCostLabel = new JLabel("Target Cost");
        playerBmoveCost =  new JFormattedTextField(NumberFormat.getNumberInstance());
        playerBmoveCost.setValue(5L);
        playerBmoveCost.setPreferredSize(playerSettingsButton.getPreferredSize());
        playerMoveCostLabel = new JLabel("Move Cost");
        playerBinitGoldMount =new JFormattedTextField(NumberFormat.getNumberInstance());
        playerBinitGoldMount.setValue(200L);
        playerBinitGoldMount.setPreferredSize(playerSettingsButton.getPreferredSize());
         playerinitGoldMountLabel = new JLabel("Gold Amount:");
        JPanel playerBpanel = new JPanel();
        playerBpanel.setLayout(new FlowLayout());
        playerBpanel.add(playerNameLabel);
        playerBpanel.add(playerTargetCostLabel);
        playerBpanel.add(playerBtargetCost);
        playerBpanel.add(playerMoveCostLabel);
        playerBpanel.add(playerBmoveCost);
        playerBpanel.add(playerinitGoldMountLabel);
        playerBpanel.add(playerBinitGoldMount);
        
        
        playerCtargetCost=  new JFormattedTextField(NumberFormat.getNumberInstance());
        playerCtargetCost.setValue(15L);
        playerCtargetCost.setPreferredSize(playerSettingsButton.getPreferredSize());
        playerNameLabel = new JLabel("C :");
        playerTargetCostLabel = new JLabel("Target Cost");
        playerCmoveCost =  new JFormattedTextField(NumberFormat.getNumberInstance());
        playerCmoveCost.setValue(5L);
        playerCmoveCost.setPreferredSize(playerSettingsButton.getPreferredSize());
        playerMoveCostLabel = new JLabel("Move Cost");
        playerCinitGoldMount =new JFormattedTextField(NumberFormat.getNumberInstance());
        playerCinitGoldMount.setValue(200L);
        playerCinitGoldMount.setPreferredSize(playerSettingsButton.getPreferredSize());
        playerinitGoldMountLabel = new JLabel("Gold Amount :");
        
        JPanel playerCpanel= new JPanel();
        playerCpanel.setLayout(new FlowLayout());
        playerCpanel.add(playerNameLabel);
        playerCpanel.add(playerTargetCostLabel);
        playerCpanel.add(playerCtargetCost);
        playerCpanel.add(playerMoveCostLabel);
        playerCpanel.add(playerCmoveCost);
        playerCpanel.add(playerinitGoldMountLabel);
        playerCpanel.add(playerCinitGoldMount);
        
        playerDtargetCost =  new JFormattedTextField(NumberFormat.getNumberInstance());
        playerDtargetCost.setValue(20L);
        playerDtargetCost.setPreferredSize(playerSettingsButton.getPreferredSize());
        playerNameLabel = new JLabel("D :");
        playerTargetCostLabel = new JLabel("Target Cost");
        playerDmoveCost =  new JFormattedTextField(NumberFormat.getNumberInstance());
        playerDmoveCost.setValue(5L);
        playerDmoveCost.setPreferredSize(playerSettingsButton.getPreferredSize());
        playerMoveCostLabel = new JLabel("Move Cost");
        playerDinitGoldMount =new JFormattedTextField(NumberFormat.getNumberInstance());
        playerDinitGoldMount.setValue(200L);
        playerDinitGoldMount.setPreferredSize(playerSettingsButton.getPreferredSize());
        playerinitGoldMountLabel = new JLabel("Gold Amount :");
        
         JPanel playerDpnel = new JPanel();
        playerDpnel.setLayout(new FlowLayout());
        playerDpnel.add(playerNameLabel);
        playerDpnel.add(playerTargetCostLabel);
        playerDpnel.add(playerDtargetCost);
        playerDpnel.add(playerMoveCostLabel);
        playerDpnel.add(playerDmoveCost);
        playerDpnel.add(playerinitGoldMountLabel);
        playerDpnel.add(playerDinitGoldMount);
        
        playerStepsNumber  = new JFormattedTextField(NumberFormat.getNumberInstance());
        playerStepsNumber.setValue(3L);
        playerStepsNumber.setPreferredSize(playerSettingsButton.getPreferredSize());
        playerStepsNumberLabel = new JLabel("Steps Number");
        JPanel playerMoveNumberPanel = new JPanel();
        playerMoveNumberPanel.setLayout(new FlowLayout());
        playerMoveNumberPanel.add(playerStepsNumberLabel);
        playerMoveNumberPanel.add(playerStepsNumber );
        
        JPanel playerSettingsPanel = new JPanel();
        playerSettingsPanel.setLayout(new BoxLayout(playerSettingsPanel, BoxLayout.Y_AXIS));
        playerSettingsPanel.add(playerApanel);
        playerSettingsPanel.add(playerBpanel);
        playerSettingsPanel.add(playerCpanel);
        playerSettingsPanel.add(playerDpnel);
        playerSettingsPanel.add(playerMoveNumberPanel);
        playerSettingsPanel.add(playerSettingsButton);
        players.add(playerSettingsPanel);
        players.setBorder(BorderFactory.createTitledBorder("Player Settings"));
        
        result = new JPanel();
        result.setLayout(new FlowLayout());
        result.setBorder(BorderFactory.createTitledBorder("Game "));
        gameStartButton = new JButton("Start");
        gameStartButton.addActionListener(this);
       
        result.add(gameStartButton);
        results = new JPanel();
        results.setBorder(BorderFactory.createTitledBorder("Results"));
        gameResultLabel = new JLabel();
        winnerLabel = new JLabel();
        results.add(gameResultLabel);
        results.add(winnerLabel);
        settings.add(generate);
        settings.add(players);
        settings.add(result);
        settings.add(results);
        
    }
    
    
    public String getWinnterString (){
        if(PlayerA.getGoldAmount()>PlayerB.getGoldAmount() && PlayerA.getGoldAmount()>PlayerC.getGoldAmount() && PlayerA.getGoldAmount()> PlayerD.getGoldAmount() )
            return "Player A  of Game Winner";
        else if (PlayerB.getGoldAmount()>PlayerA.getGoldAmount() && PlayerB.getGoldAmount()>PlayerC.getGoldAmount() && PlayerB.getGoldAmount() > PlayerD.getGoldAmount())
             return "Player B  of Game Winner";
        else if (PlayerC.getGoldAmount()>PlayerA.getGoldAmount() && PlayerC.getGoldAmount()>PlayerB.getGoldAmount() && PlayerC.getGoldAmount() > PlayerD.getGoldAmount())
             return "Player C  of Game Winner";
        else if  (PlayerD.getGoldAmount() > PlayerA.getGoldAmount() && PlayerD.getGoldAmount() > PlayerB.getGoldAmount() && PlayerD.getGoldAmount() > PlayerC.getGoldAmount())
            return "Player D  of Game Winner";
        else if(PlayerA.getGoldAmount()== PlayerB.getGoldAmount() && PlayerA.getGoldAmount()>PlayerC.getGoldAmount() && PlayerA.getGoldAmount()>PlayerD.getGoldAmount())
            return "Player A and Player B of Game Winner";
        else if(PlayerB.getGoldAmount()== PlayerC.getGoldAmount() && PlayerB.getGoldAmount()>PlayerA.getGoldAmount() && PlayerB.getGoldAmount()> PlayerD.getGoldAmount())
            return "Player B and Player C of Game Winner";
        else if(PlayerC.getGoldAmount()== PlayerA.getGoldAmount() && PlayerC.getGoldAmount()>PlayerB.getGoldAmount()&& PlayerC.getGoldAmount()> PlayerD.getGoldAmount())
            return "Player A and Player C of Game Winner";
        else if (PlayerD.getGoldAmount() == PlayerA.getGoldAmount() && PlayerD.getGoldAmount()>PlayerC.getGoldAmount() && PlayerD.getGoldAmount()> PlayerB.getGoldAmount())
            return "Player A and Player D of Game Winner";
        else if (PlayerD.getGoldAmount() == PlayerB.getGoldAmount() && PlayerD.getGoldAmount()>PlayerC.getGoldAmount() && PlayerD.getGoldAmount()> PlayerA.getGoldAmount())
            return "Player B and Player D of Game Winner";
        else if (PlayerD.getGoldAmount() == PlayerC.getGoldAmount() && PlayerD.getGoldAmount()>PlayerA.getGoldAmount() && PlayerD.getGoldAmount()> PlayerB.getGoldAmount())
            return "Player C and Player D of Game Winner";
        else
            return "There is no winner, They all have the same amount of gold .";
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        JButton source = (JButton) e.getSource();
        if(source == boardSettingsButton){
            Long brx = (Long) boardRow.getValue();
            Long bry = (Long) boardCol.getValue();
            Long ao = (Long)goldenRationText.getValue();
            Long gao = (Long)hiddenGoldenRationText.getValue();
          
            boardx = brx.intValue();
            boardy = bry.intValue();
            goldenRatio = ao.intValue();
            hiddenGoldenRatio = gao.intValue();
             //setSize(boardx*80,boardy*50);
            gameBoard = new Game(boardx,boardy,goldenRatio,hiddenGoldenRatio); 
            
            playerAtargetCost.setValue(5L);
            playerAmoveCost.setValue(5L);
            playerAinitGoldMount.setValue(200L);
            playerBtargetCost.setValue(10L);
            playerBmoveCost.setValue(5L);
            playerBinitGoldMount.setValue(200L);
            playerCtargetCost.setValue(15L);
            playerCmoveCost.setValue(5L);
            playerCinitGoldMount.setValue(200L);
            playerDtargetCost.setValue(20L);
            playerDmoveCost.setValue(5L);
            playerDinitGoldMount.setValue(200L);
            playerStepsNumber.setValue(3L);
             
            startA = new Point(0,0);
            startB = new Point(0,boardy-1);
            PlayerA = new playerA(startA);
            PlayerA.hedefBelirle(gameBoard);
            PlayerB = new playerB(startB);
            PlayerB.hedefBelirle(gameBoard);
            startC = new Point(boardx-1,boardy-1);
            PlayerC = new playerC(startC);
            startD = new Point(boardx-1,0);
            PlayerD = new playerD(startD);
            gameUI = new OyunUI(gameBoard);
            PlayerC.hedefBelirle(gameBoard,gameUI);
            game.removeAll();
            game.repaint();
            game.revalidate();
            gameResultLabel.setText("");
            winnerLabel.setText("");
            game.add(gameUI);
        }
        if(source == playerSettingsButton){
            
            Long ahedefmaliyet = (Long) playerAtargetCost.getValue();
            Long ahamlemaliyet = (Long) playerAmoveCost.getValue();
            Long aAltinMiktari = (Long) playerAinitGoldMount.getValue();
            Long bhedefmaliyet = (Long) playerBtargetCost.getValue();
            Long bhamlemaliyet = (Long) playerBmoveCost.getValue();
            Long bAltinMiktari = (Long) playerBinitGoldMount.getValue();
            Long chedefmaliyet = (Long) playerCtargetCost.getValue();
            Long chamlemaliyet = (Long) playerCmoveCost.getValue();
            Long cAltinMiktari = (Long) playerCinitGoldMount.getValue();
            Long dhedefmaliyet = (Long) playerDtargetCost.getValue();
            Long dhamlemaliyet = (Long) playerDmoveCost.getValue();
            Long dAltinMiktari = (Long) playerDinitGoldMount.getValue();
            Long stepsNumber = (Long) playerStepsNumber.getValue();
            
            PlayerA.setTargetCost(ahedefmaliyet.intValue());
            PlayerA.setMoveCost(ahamlemaliyet.intValue());
            PlayerA.setGoldAmount(aAltinMiktari.intValue());
            PlayerB.setGoldAmountSpent(bhedefmaliyet.intValue());
            PlayerB.setMoveCost(bhamlemaliyet.intValue());
            PlayerB.setGoldAmount(bAltinMiktari.intValue());
            PlayerC.setTargetCost(chedefmaliyet.intValue());
            PlayerC.setMoveCost(chamlemaliyet.intValue());
            PlayerC.setGoldAmount(cAltinMiktari.intValue());
            PlayerD.setTargetCost(dhedefmaliyet.intValue());
            PlayerD.setMoveCost(dhamlemaliyet.intValue());
            PlayerD.setGoldAmount(dAltinMiktari.intValue());
            
            PlayerA.setStepsNumber(stepsNumber.intValue());
            
    
            
            PlayerB.setStepsNumber(stepsNumber.intValue());
            PlayerC.setStepsNumber(stepsNumber.intValue());
            PlayerD.setStepsNumber(stepsNumber.intValue());
            
        }
        if(source == gameStartButton){
            while(!gameBoard._game_Over){
               game.revalidate();
               game.repaint();
               if(!(PlayerA.isHaveGoldBool())){
                  
                  PlayerA.yollarıbul(PlayerA.getStartPoint(), PlayerA.getEndPoint(),gameBoard);
               if(gameBoard.getGrid()[PlayerA.getEndPoint().x][PlayerA.getEndPoint().y].isGold()){
                    gameUI.hareketA(PlayerA);
               }else{
                    PlayerA.setHaveTarget(true);
                    PlayerA.hedefBelirle(gameBoard);
                    PlayerA.yollarıbul(PlayerA.getStartPoint(), PlayerA.getEndPoint(),gameBoard);
                    gameUI.hareketA(PlayerA);  
                }
            
               if(PlayerA.getHaveTarget()){
                    PlayerA.hedefBelirle(gameBoard);
                }
               else
                    System.out.println("A oyuncu Hedef Alınmadı Devam Ediyor!!");
                }else{
                   System.out.println("düştüü.");
               }
               
               
               if(!(PlayerB.isHaveGoldBool())  ){
                    PlayerB.yollarıbul(PlayerB.getStartPoint(), PlayerB.getEndPoint(),gameBoard);
               if(gameBoard.getGrid()[PlayerB.getEndPoint().x][PlayerB.getEndPoint().y].isGold())
                    gameUI.hareketB(PlayerB);
              else{
                    PlayerB.setHaveTarget(true);
                    PlayerB.hedefBelirle(gameBoard);
                    PlayerB.yollarıbul(PlayerB.getStartPoint(), PlayerB.getEndPoint(),gameBoard);
                    gameUI.hareketB(PlayerB);
                }
               if(PlayerB.getHaveTarget())
                    PlayerB.hedefBelirle(gameBoard);
               else
                    System.out.println("B oyuncu Hedef Alınmadı Devam Ediyor!!");
                
                }
             
               if(!(PlayerC.isHaveGoldBool()) ){
                     PlayerC.yollarıbul(PlayerC.getStartPoint(),PlayerC.getEndPoint(),gameBoard);
               if(gameBoard.getGrid()[PlayerC.getEndPoint().x][PlayerC.getEndPoint().y].isGold())
                    gameUI.hareketC(PlayerC);
              else{
                    PlayerC.setHaveTarget(true);
                    PlayerC.hedefBelirle(gameBoard,gameUI);
                    PlayerC.yollarıbul(PlayerC.getStartPoint(),PlayerC.getEndPoint(),gameBoard);
                    gameUI.hareketC(PlayerC);
                }
               if(PlayerC.getHaveTarget())
                    PlayerC.hedefBelirle(gameBoard,gameUI);
              else
                   System.out.println("C oyuncu Hedef Alınmadı Devam Ediyor !!");
               
                }
                   
                Point endA = new Point(PlayerA.getEndPoint());
                Point endB = new Point(PlayerB.getEndPoint());
                Point endC = new Point(PlayerC.getEndPoint());
                    
               if(!(PlayerD.isHaveGoldBool()) ){
                   if(PlayerD.getHaveTarget())
                        PlayerD.hedefBelirle(gameBoard,endA,PlayerA.getStartPoint(),endB,PlayerB.getStartPoint(),endC,PlayerC.getStartPoint());
                   else
                        System.out.println("D oyuncu Hedef Alınmadı Devam Ediyor !!");
                  
                   PlayerD.yollarıbul(PlayerD.getStartPoint(),PlayerD.getEndPoint(),gameBoard);
                   Point endD = new Point(PlayerD.getEndPoint());
                   if(gameBoard.getGrid()[endD.x][endD.y].isGold() && checkFinish(endA,endB, endC, endD))
                        gameUI.hareketD(PlayerD);
                   else{
                       PlayerD.setHaveTarget(true);
                       PlayerD.hedefBelirle(gameBoard,endA,PlayerA.getStartPoint(),endB,PlayerB.getStartPoint(),endC,PlayerC.getStartPoint());
                       PlayerD.yollarıbul(PlayerD.getStartPoint(),PlayerD.getEndPoint(),gameBoard);
                       gameUI.hareketD(PlayerD);
                    }
               }
               
               if((PlayerB.isHaveGoldBool() && PlayerA.isHaveGoldBool()&& PlayerC.isHaveGoldBool() && PlayerD.isHaveGoldBool())){
                  break;
                }
           
          
            }
        if((PlayerB.isHaveGoldBool() && PlayerA.isHaveGoldBool() && PlayerC.isHaveGoldBool() && PlayerD.isHaveGoldBool())){
            gameBoard._game_Over = true;
            gameResultLabel.setText(" Game Over !!");
            winnerLabel.setText(getWinnterString ());
        }
        
        PlayerA.setPlayerPassedRotaArrayList(PlayerA.getEndPoint());
        PlayerB.setPlayerPassedRotaArrayList(PlayerB.getEndPoint());
        PlayerC.setPlayerPassedRotaArrayList(PlayerC.getEndPoint());
        PlayerD.setPlayerPassedRotaArrayList(PlayerD.getEndPoint());
        file = new File("C:\\Users\\AYAZ\\Documents\\NetBeansProjects\\AltinToplamaOyunu\\ResultA.txt");
        fileWrite(PlayerA);
        file = new File("C:\\Users\\AYAZ\\Documents\\NetBeansProjects\\AltinToplamaOyunu\\ResultB.txt");
        fileWrite(PlayerB);
        file = new File("C:\\Users\\AYAZ\\Documents\\NetBeansProjects\\AltinToplamaOyunu\\ResultC.txt");
        fileWrite(PlayerC);
        file = new File("C:\\Users\\AYAZ\\Documents\\NetBeansProjects\\AltinToplamaOyunu\\ResultD.txt");
        fileWrite(PlayerD);
        
        System.out.println("A'NIN ALTIN SAYISI : "+PlayerA.getGoldAmount()+"\nB'NIN ALTIN SAYISI : "+PlayerB.getGoldAmount()+"\nC'NIN ALTIN SAYISI : "+PlayerC.getGoldAmount()+"\nD'NIN ALTIN SAYISI : "+PlayerD.getGoldAmount());
        
        }
        
    }
    
    
    public boolean checkFinish(Point endA,Point endB,Point endC,Point endD){
        boolean result = true;
            if(distance(PlayerD.getStartPoint(),endA) > distance(PlayerA.getStartPoint(),endA))
                result = false;
            if(distance(PlayerD.getStartPoint(),endB) > distance(PlayerB.getStartPoint(),endB))
                result = false;
            if(distance(PlayerD.getStartPoint(),endC) > distance(PlayerC.getStartPoint(),endC))
                result= false;
        return result;
     }
            
     public int distance(Point a,Point b){
        return  Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
    }
     
     
}

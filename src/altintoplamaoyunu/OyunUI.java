/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package altintoplamaoyunu;
import Oyuncular.*;
import struct.*;



import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.util.ArrayList;


public class OyunUI extends JPanel  {
    public NodeUI[][] grid;
    private Oyun oyun;
    private GridBagConstraints gbc;
    private GridBagLayout gbl;
    private Node currentNode;
    playerA oyuncuA;
    Color dbg;
    public JLabel goldLabel,playerALabel,playerBLabel,playerCLabel,playerDLabel;
 
    
    public OyunUI(Oyun oyun){
        this.oyun = oyun;
        gbl = new GridBagLayout();
        setLayout(gbl);
        this.grid= new NodeUI[oyun.getLines()][oyun.getCols()];
        gbc = new GridBagConstraints();
        dbg = getBackground();
        reset();
        paint(getGraphics());
        System.out.println("TABLO OLUSTURULDU!!!");
        
    }
    
    public NodeUI[][] getGridUI(){
        return this.grid;
    }
    
    public void reset(){
        NodeUI n;
        for(int i = 0;i < oyun.getLines(); i++){
            for(int j = 0;j < oyun.getCols(); j++){
                if(grid[i][j] == null){
                    n = new NodeUI(oyun.getGrid()[i][j].p);
                    grid[i][j] = n;
                }
                gbc.gridx = j; // bunları anlamadım
                gbc.gridy = i; // anlamadım
                setBackground(dbg);
                if(oyun.getGrid()[i][j].p.equals(oyun.getStart())){
                    playerALabel = new JLabel();
                    playerALabel.setText("A");
                    grid[i][j].add(playerALabel);
                 
                }
                else if (oyun.getGrid()[i][j].p.equals(oyun.getStartB())){
                    playerBLabel = new JLabel();
                    playerBLabel.setText("B");
                    grid[i][j].add(playerBLabel);
                }
                else if (oyun.getGrid()[i][j].p.equals(oyun.getStartC())){
                    playerCLabel = new JLabel();
                    playerCLabel.setText("C");
                    grid[i][j].add(playerCLabel);
                }
                else if (oyun.getGrid()[i][j].p.equals(oyun.getStartD())){
                    playerDLabel = new JLabel();
                    playerDLabel.setText("D");
                    grid[i][j].add(playerDLabel);
                }
                
                else if (oyun.getGrid()[i][j].isAltin() && (oyun.getGrid()[i][j].p.equals(oyun.getStart()) == false)){
                    goldLabel = new JLabel();
                    goldLabel.setText(Integer.toString(oyun.getGrid()[i][j].isAltinMiktari()));
                    grid[i][j].add(goldLabel);
                }
                else if (oyun.getGrid()[i][j].isGAltin()){
                    //grid[i][j].setBackground(Color.YELLOW);
                    oyun.getGrid()[i][j].setGAltinGorunurluk(false);
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
       
       if((player.getAltinMiktari()-player.getHamleMaliyet())>=0 && (player.gameoverA==false)){
           player.setHarcananAltinMiktari(player.getHarcananAltinMiktari()+player.getHamleMaliyet());
       
        ArrayList <Point> list ;
         // player.yollarıbul3(player.getStartPoint(), player.getEndPoint());
        list= player.getListe();
        Point start = new Point();
        start = player.getStartPoint();
        Point end  = new Point();
        grid[start.x][start.y].remove(playerALabel);
        for(Point n : list){
            player.setGidilenYollar(n);
            player.setToplamAdim(player.getToplamAdim()+1);
            grid[n.x][n.y].add(playerALabel); // ilk hareketi yaparken kendini gösteriyor
            grid[n.x][n.y].setBackground(Color.BLUE); // araksını mavi yapıyor
            this.paintAll(getGraphics());
            try {
                Thread.sleep(0); // delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            }   
            if(oyun.getGrid()[n.x][n.y].isGAltin()){
                if(oyun.getGrid()[n.x][n.y].getGAltinGorunurluk()){
                    player.setAltinMiktari(player.getAltinMiktari()+ oyun.getGrid()[n.x][n.y].getGAltinMiktari()) ;
                    player.setToplananAltinMiktari(player.getToplananAltinMiktari()+oyun.getGrid()[n.x][n.y].getGAltinMiktari());
                    oyun.getGrid()[n.x][n.y].setGAltin(false);
                    grid[n.x][n.y].removeAll();
                }
                else{
                    oyun.getGrid()[n.x][n.y].setGAltinGorunurluk(true);
                    goldLabel = new JLabel();
                    goldLabel.setText(Integer.toString(oyun.getGrid()[n.x][n.y].getGAltinMiktari()));
                    grid[n.x][n.y].add(goldLabel);
                }
    
            }  
            if(oyun.getGrid()[n.x][n.y].isAltin()){  // eger gectiği yerde altın varsa
                player.setAltinMiktari(player.getAltinMiktari()+ oyun.getGrid()[n.x][n.y].getAltinMiktari()) ;
                player.setToplananAltinMiktari(player.getToplananAltinMiktari()+oyun.getGrid()[n.x][n.y].getAltinMiktari());
                oyun.getGrid()[n.x][n.y].setAltin(false); // o yerin altın kısmını false yap
                // altını al ekle ganimete
                grid[n.x][n.y].removeAll(); // panelden sil
            }    
            grid[n.x][n.y].setBackground(dbg); // arkasını eski haline getir
            //  grid[n.x][n.y].remove(playerA); // kendini sil 
            end = n;
        }
        player.setToplamAdim(player.getToplamAdim()-1);
        player.setAltinMiktari(player.getAltinMiktari()-player.getHamleMaliyet()); 
        // grid[end.x][end.y].add(playerA); // en son kaldıgı yere kendini yazdir
        grid[end.x][end.y].add(playerALabel);
        player.setStartPoint(end); // en son kaldiğin yeri kendi startına yolla
        player.getGidilenYollar().remove(player.getGidilenYollar().size()-1);

       }
       else{
           if(!player.gameoverA){
                System.out.println("PARASI BITTI A NIN : "+player.getAltinMiktari());
                player.gameoverA = true;
           }
           
        }         
    }

   

public void hareketB (playerB player){
     
        // bunları playerb sınıfına yaz
    if((player.getAltinMiktari()-player.getHamleMaliyet())>=0  && (player.gameoverB==false)){
           player.setHarcananAltinMiktari(player.getHarcananAltinMiktari()+player.getHamleMaliyet());
        ArrayList <Point> list ;
         // player.yollarıbul3(player.getStartPoint(), player.getEndPoint());
        list= player.getListe();
        Point start = new Point();
        start = player.getStartPoint();
        Point end  = new Point();
        grid[start.x][start.y].remove(playerBLabel);
        for(Point n : list){
            player.setGidilenYollar(n);
            player.setToplamAdim(player.getToplamAdim()+1);
            grid[n.x][n.y].add(playerBLabel); // ilk hareketi yaparken kendini gösteriyor
            grid[n.x][n.y].setBackground(Color.RED); // araksını mavi yapıyor
            this.paintAll(getGraphics());
            try {
                Thread.sleep(0); // delay
            }catch (InterruptedException e) {
                e.printStackTrace();
            }   
            if(oyun.getGrid()[n.x][n.y].isGAltin()){
                if(oyun.getGrid()[n.x][n.y].getGAltinGorunurluk()){
                player.setAltinMiktari(player.getAltinMiktari()+ oyun.getGrid()[n.x][n.y].getGAltinMiktari()) ;
                player.setToplananAltinMiktari(player.getToplananAltinMiktari()+oyun.getGrid()[n.x][n.y].getGAltinMiktari());
                oyun.getGrid()[n.x][n.y].setGAltin(false);
                grid[n.x][n.y].removeAll();
                }
                else{
                    oyun.getGrid()[n.x][n.y].setGAltinGorunurluk(true);
                    goldLabel = new JLabel();
                    goldLabel.setText(Integer.toString(oyun.getGrid()[n.x][n.y].getGAltinMiktari()));
                    grid[n.x][n.y].add(goldLabel);
                }

            } 
            if(oyun.getGrid()[n.x][n.y].isAltin()){  // eger gectiği yerde altın varsa
                player.setAltinMiktari(player.getAltinMiktari()+ oyun.getGrid()[n.x][n.y].getAltinMiktari()) ;
                player.setToplananAltinMiktari(player.getToplananAltinMiktari()+oyun.getGrid()[n.x][n.y].getAltinMiktari());
                oyun.getGrid()[n.x][n.y].setAltin(false); // o yerin altın kısmını false yap
                // altını al ekle ganimete
                grid[n.x][n.y].removeAll(); // panelden sil
            }    
            grid[n.x][n.y].setBackground(dbg); // arkasını eski haline getir
            grid[n.x][n.y].remove(playerBLabel); // kendini sil 
            end = n;
        }
        player.setToplamAdim(player.getToplamAdim()-1);
        player.setAltinMiktari(player.getAltinMiktari()-player.getHamleMaliyet()); 
        grid[end.x][end.y].add(playerBLabel); // en son kaldıgı yere kendini yazdir
        player.setStartPoint(end); // en son kaldiğin yeri kendi startına yolla
        player.getGidilenYollar().remove(player.getGidilenYollar().size()-1);
    
    }
    else{
        if(!player.gameoverB){
        System.out.println("PARASI BITTI B NIN  hareket edemez : "+player.getAltinMiktari());
        player.gameoverB = true;      
        }
        
    }

}
 
public void hareketC (playerC player){
    if((player.getAltinMiktari()-player.getHamleMaliyet())>=0  && (player.gameoverC==false)){
        player.setHarcananAltinMiktari(player.getHarcananAltinMiktari()+player.getHamleMaliyet());
        ArrayList <Point> list ;
        list= player.getListe();
        Point start = new Point();
        start = player.getStartPoint();
        Point end  = new Point();
        grid[start.x][start.y].remove(playerCLabel);
        for(Point n : list){
            player.setGidilenYollar(n);
            player.setToplamAdim(player.getToplamAdim()+1);
            grid[n.x][n.y].add(playerCLabel); // ilk hareketi yaparken kendini gösteriyor
            grid[n.x][n.y].setBackground(Color.ORANGE); // araksını mavi yapıyor
            this.paintAll(getGraphics());
            try {
                Thread.sleep(0); // delay
            } catch (InterruptedException e) {
                System.out.println("e.printStackTrace()");
            }   
            if(oyun.getGrid()[n.x][n.y].isGAltin()){
                if(oyun.getGrid()[n.x][n.y].getGAltinGorunurluk()){
                player.setAltinMiktari(player.getAltinMiktari()+ oyun.getGrid()[n.x][n.y].getGAltinMiktari()) ;
                 player.setToplananAltinMiktari(player.getToplananAltinMiktari()+oyun.getGrid()[n.x][n.y].getGAltinMiktari());
                oyun.getGrid()[n.x][n.y].setGAltin(false);
                grid[n.x][n.y].removeAll();
                }
                else{
                    oyun.getGrid()[n.x][n.y].setGAltinGorunurluk(true);
                    goldLabel = new JLabel();
                    goldLabel.setText(Integer.toString(oyun.getGrid()[n.x][n.y].getGAltinMiktari()));
                    grid[n.x][n.y].add(goldLabel);
                }
            }
                   
            if(oyun.getGrid()[n.x][n.y].isAltin()){  // eger gectiği yerde altın varsa
                player.setAltinMiktari(player.getAltinMiktari()+ oyun.getGrid()[n.x][n.y].getAltinMiktari()) ;
                player.setToplananAltinMiktari(player.getToplananAltinMiktari()+oyun.getGrid()[n.x][n.y].getAltinMiktari());
                oyun.getGrid()[n.x][n.y].setAltin(false); // o yerin altın kısmını false yap
                // altını al ekle ganimete
                grid[n.x][n.y].removeAll(); // panelden sil
            }    
            grid[n.x][n.y].setBackground(dbg); // arkasını eski haline getir
            grid[n.x][n.y].remove(playerCLabel); // kendini sil 
            end = n;
                   
        }
        player.setToplamAdim(player.getToplamAdim()-1);
        player.setAltinMiktari(player.getAltinMiktari()-player.getHamleMaliyet()); 
        grid[end.x][end.y].add(playerCLabel); // en son kaldıgı yere kendini yazdir
        player.setStartPoint(end); // en son kaldiğin yeri kendi startına yolla
        player.getGidilenYollar().remove(player.getGidilenYollar().size()-1);
    }
    else{
        if(!player.gameoverC){
            System.out.println("PARASI BITTI C NIN  hareket edemez : "+player.getAltinMiktari());
            player.gameoverC = true;
        }
        
    }
 }
    
public void hareketD (playerD player){
    
    if((player.getAltinMiktari()-player.getHamleMaliyet())>=0  && (player.gameoverD==false)){
       player.setHarcananAltinMiktari(player.getHarcananAltinMiktari()+player.getHamleMaliyet());
       ArrayList <Point> list ;
       list= player.getListe();
       Point start = new Point();
       start = player.getStartPoint();
       Point end  = new Point();
       grid[start.x][start.y].remove(playerDLabel);
        for(Point n : list){
            player.setGidilenYollar(n);
            player.setToplamAdim(player.getToplamAdim()+1);
            grid[n.x][n.y].add(playerDLabel); // ilk hareketi yaparken kendini gösteriyor
            grid[n.x][n.y].setBackground(Color.YELLOW); // araksını mavi yapıyor
            this.paintAll(getGraphics());
            try {
                Thread.sleep(0); // delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            }   
            if(oyun.getGrid()[n.x][n.y].isGAltin()){
                if(oyun.getGrid()[n.x][n.y].getGAltinGorunurluk()){
                    player.setAltinMiktari(player.getAltinMiktari()+ oyun.getGrid()[n.x][n.y].getGAltinMiktari()) ;
                    player.setToplananAltinMiktari(player.getToplananAltinMiktari()+oyun.getGrid()[n.x][n.y].getGAltinMiktari());
                    oyun.getGrid()[n.x][n.y].setGAltin(false);
                    grid[n.x][n.y].removeAll();
                }
                else{
                    oyun.getGrid()[n.x][n.y].setGAltinGorunurluk(true);
                    goldLabel = new JLabel();
                    goldLabel.setText(Integer.toString(oyun.getGrid()[n.x][n.y].getGAltinMiktari()));
                    grid[n.x][n.y].add(goldLabel);
                }
            }
            if(oyun.getGrid()[n.x][n.y].isAltin()){  // eger gectiği yerde altın varsa
                player.setAltinMiktari(player.getAltinMiktari()+ oyun.getGrid()[n.x][n.y].getAltinMiktari()) ;
                player.setToplananAltinMiktari(player.getToplananAltinMiktari()+oyun.getGrid()[n.x][n.y].getAltinMiktari());
                oyun.getGrid()[n.x][n.y].setAltin(false); // o yerin altın kısmını false yap
                // altını al ekle ganimete
                grid[n.x][n.y].removeAll(); // panelden sil
            }    
            grid[n.x][n.y].setBackground(dbg); // arkasını eski haline getir
            grid[n.x][n.y].remove(playerDLabel); // kendini sil 
            end = n;
        }
        player.setToplamAdim(player.getToplamAdim()-1);
        player.setAltinMiktari(player.getAltinMiktari()-player.getHamleMaliyet()); 
        grid[end.x][end.y].add(playerDLabel); // en son kaldıgı yere kendini yazdir
        player.setStartPoint(end); // en son kaldiğin yeri kendi startına yolla
        player.getGidilenYollar().remove(player.getGidilenYollar().size()-1);
    }
    else{
        if(!player.gameoverD){
            System.out.println("PARASI BITTI D NIN  hareket edemez : "+player.getAltinMiktari());
            player.gameoverD = true;
        }
        

    }

}

   
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package altintoplamaoyunu;

/**
 *
 * @author AYAZ
 */
import Oyuncular.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.NumberFormat;
import struct.*;


public class Board extends JFrame implements ActionListener {
     int boardx = 20,boardy=20,altinoran=20,galtinoran=10;
    JPanel oyun,ayarlar,generate,sonuc,sonuclar,oyuncular;
    JFormattedTextField oyunsatir, oyunsutun;
    JFormattedTextField playerAdimSayisi,playerAhedefmaliyet,playerAhamlemaliyet,playerAbaslangicAltinMiktari,playerBhedefmaliyet,playerBhamlemaliyet,playerBbaslangicAltinMiktari,playerChedefmaliyet,playerChamlemaliyet,playerCbaslangicAltinMiktari,playerDbaslangicAltinMiktari,playerDhedefmaliyet,playerDhamlemaliyet;
    JFormattedTextField altinotxt, startY, galtinotxt;
    JButton ayarlabuton1,ayarlabuton2, baslabuton;
    JLabel sonuclab,sonuclab2, oyunsutunlab, oyunsatirlab;
    JLabel oyuncuIsmi,oyuncuHedefMaliyet,oyuncuHamleMaliyet,baslangicAltin,adimSayisi;
    JLabel altinolab,galtinolab;
    Point startA,startB,startC,startD;
    Player Player = new Player();
    playerA PlayerA;
    playerB PlayerB;
    playerC PlayerC;
    playerD PlayerD;
    Oyun oyunBoard;
    OyunUI oyunUI;
    File file;
   
  
    public static void main(String []args){
        new Board();
    }
    
    public Board(){
        setLayout(new FlowLayout(0,1,0));        
        setTitle("Altın Toplama Oyunu");
        oyun = new JPanel();
        //oyun.setLayout(new FlowLayout(0,1,0));
        // oyuncuları nasıl eklenceği düşünülsün
        oyunBoard = new Oyun(boardx,boardy,altinoran,galtinoran);
        startA = new Point(0,0);
        PlayerA = new playerA(startA);
        PlayerA.hedefBelirle(oyunBoard);
        startB = new Point(0,boardy-1);
        PlayerB = new playerB(startB);
        PlayerB.hedefBelirle(oyunBoard);
        startC = new Point(boardx-1,boardy-1);
        PlayerC = new playerC(startC);
        startD = new Point(boardx-1,0);
        PlayerD = new playerD(startD);
        oyunUI = new OyunUI(oyunBoard);
        PlayerC.hedefBelirle(oyunBoard,oyunUI);
        oyun.add(oyunUI);
        add(oyun);
        createAyarlar();
        add(ayarlar);
        setMinimumSize(new Dimension(1366,768));
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    public void fileWrite(Player player){
        try{
            FileWriter fw = new FileWriter(file);
            fw.write("\nToplam Adım Sayısı:"+player.getToplamAdim()+"\r\n");
            fw.write("Gittiği Yerler : { ");
            for(Point n : player.getGidilenYollar()){
                fw.write("("+n.x+","+n.y+") ");
            }
            fw.write("}"+"\r\n");
            fw.write("Toplanan Altın Sayısı:"+player.getToplananAltinMiktari()+"\r\n");
            fw.write("Harcanan Altın Miktarı:"+player.getHarcananAltinMiktari()+"\r\n");
            fw.write("Kasadaki Altin Miktari:"+(player.getAltinMiktari()+"\r\n"));
            fw.close();
        }catch(IOException e){
            System.out.println("HATAA");
        }
    }
     public void createAyarlar(){
        ayarlar = new JPanel();
        ayarlar.setLayout(new BoxLayout(ayarlar, BoxLayout.Y_AXIS));
        ayarlar.setBackground(Color.YELLOW);

        //ayarları oluşturmak
        generate = new JPanel();
        generate.setLayout(new FlowLayout());
        ayarlabuton1 = new JButton("Ayarla");
        ayarlabuton1.addActionListener(this);
        oyunsatir = new JFormattedTextField(NumberFormat.getNumberInstance());
        oyunsatir.setValue(20L);
        oyunsatir.setPreferredSize(ayarlabuton1.getPreferredSize());
        oyunsatirlab = new JLabel("Satır");
        oyunsutun = new JFormattedTextField(NumberFormat.getNumberInstance());
        oyunsutun.setValue(20L);
        oyunsutun.setPreferredSize(ayarlabuton1.getPreferredSize());
        oyunsutunlab = new JLabel("Sütun");

        JPanel dimensions= new JPanel();
        dimensions.setLayout(new FlowLayout());
        JPanel altinmiktaripoz = new JPanel();
        altinmiktaripoz.setLayout(new FlowLayout());
        JPanel galtinmiktaripoz = new JPanel();
        galtinmiktaripoz.setLayout(new FlowLayout());

        dimensions.add(oyunsatirlab);
        dimensions.add(oyunsatir);
        dimensions.add(oyunsutunlab);
        dimensions.add(oyunsutun);
        altinolab = new JLabel("Altın Miktar Oranı :");
        altinotxt = new JFormattedTextField(NumberFormat.getNumberInstance());
        altinotxt.setValue(20L);
        altinotxt.setPreferredSize(ayarlabuton1.getPreferredSize());
        altinmiktaripoz.add(altinolab);
        altinmiktaripoz.add(altinotxt);
        
        galtinolab = new JLabel("Gizli Altın Miktar Oranı :");
        galtinotxt = new JFormattedTextField(NumberFormat.getNumberInstance());
        galtinotxt.setPreferredSize(ayarlabuton1.getPreferredSize());
        galtinotxt.setValue(10L);
        galtinmiktaripoz.add(galtinolab);
        galtinmiktaripoz.add(galtinotxt);
        
        JPanel a = new JPanel();
        a.setLayout(new BoxLayout(a, BoxLayout.Y_AXIS));
        a.add(dimensions);
        a.add(altinmiktaripoz);
        a.add(galtinmiktaripoz);
        a.add(ayarlabuton1);

        generate.add(a);
        generate.setBorder(BorderFactory.createTitledBorder("Ayarlar"));
        
        oyuncular = new JPanel();
        oyuncular.setLayout(new FlowLayout());
        ayarlabuton2 =  new JButton("Ayarla");
        ayarlabuton2.addActionListener(this);
        
        playerAhedefmaliyet =  new JFormattedTextField(NumberFormat.getNumberInstance());
        playerAhedefmaliyet.setValue(5L);
        playerAhedefmaliyet.setPreferredSize(ayarlabuton2.getPreferredSize());
        oyuncuIsmi = new JLabel("A :");
        oyuncuHedefMaliyet = new JLabel("Hedef Maliyet");
        playerAhamlemaliyet =  new JFormattedTextField(NumberFormat.getNumberInstance());
        playerAhamlemaliyet.setValue(5L);
        playerAhamlemaliyet.setPreferredSize(ayarlabuton2.getPreferredSize());
        oyuncuHamleMaliyet = new JLabel("Hamle Maliyet");
        playerAbaslangicAltinMiktari =new JFormattedTextField(NumberFormat.getNumberInstance());
        playerAbaslangicAltinMiktari.setValue(200L);
        playerAbaslangicAltinMiktari.setPreferredSize(ayarlabuton2.getPreferredSize());
        baslangicAltin = new JLabel("Altin Miktari :");
        JPanel oyuncuA= new JPanel();
        oyuncuA.setLayout(new FlowLayout());
        oyuncuA.add(oyuncuIsmi);
        oyuncuA.add(oyuncuHedefMaliyet);
        oyuncuA.add(playerAhedefmaliyet);
        oyuncuA.add(oyuncuHamleMaliyet);
        oyuncuA.add(playerAhamlemaliyet);
        oyuncuA.add(baslangicAltin);
        oyuncuA.add(playerAbaslangicAltinMiktari);
          
        playerBhedefmaliyet =  new JFormattedTextField(NumberFormat.getNumberInstance());
        playerBhedefmaliyet.setValue(10L);
        playerBhedefmaliyet.setPreferredSize(ayarlabuton2.getPreferredSize());
        oyuncuIsmi = new JLabel("B :");
        oyuncuHedefMaliyet = new JLabel("Hedef Maliyet");
        playerBhamlemaliyet =  new JFormattedTextField(NumberFormat.getNumberInstance());
        playerBhamlemaliyet.setValue(5L);
        playerBhamlemaliyet.setPreferredSize(ayarlabuton2.getPreferredSize());
        oyuncuHamleMaliyet = new JLabel("Hamle Maliyet");
        playerBbaslangicAltinMiktari =new JFormattedTextField(NumberFormat.getNumberInstance());
        playerBbaslangicAltinMiktari.setValue(200L);
        playerBbaslangicAltinMiktari.setPreferredSize(ayarlabuton2.getPreferredSize());
         baslangicAltin = new JLabel("Altin Miktari :");
        JPanel oyuncuB = new JPanel();
        oyuncuB.setLayout(new FlowLayout());
        oyuncuB.add(oyuncuIsmi);
        oyuncuB.add(oyuncuHedefMaliyet);
        oyuncuB.add(playerBhedefmaliyet);
        oyuncuB.add(oyuncuHamleMaliyet);
        oyuncuB.add(playerBhamlemaliyet);
        oyuncuB.add(baslangicAltin);
        oyuncuB.add(playerBbaslangicAltinMiktari);
        
        
        playerChedefmaliyet =  new JFormattedTextField(NumberFormat.getNumberInstance());
        playerChedefmaliyet.setValue(15L);
        playerChedefmaliyet.setPreferredSize(ayarlabuton2.getPreferredSize());
        oyuncuIsmi = new JLabel("C :");
        oyuncuHedefMaliyet = new JLabel("Hedef Maliyet");
        playerChamlemaliyet =  new JFormattedTextField(NumberFormat.getNumberInstance());
        playerChamlemaliyet.setValue(5L);
        playerChamlemaliyet.setPreferredSize(ayarlabuton2.getPreferredSize());
        oyuncuHamleMaliyet = new JLabel("Hamle Maliyet");
        playerCbaslangicAltinMiktari =new JFormattedTextField(NumberFormat.getNumberInstance());
        playerCbaslangicAltinMiktari.setValue(200L);
        playerCbaslangicAltinMiktari.setPreferredSize(ayarlabuton2.getPreferredSize());
        baslangicAltin = new JLabel("Altin Miktari :");
        
        JPanel oyuncuC = new JPanel();
        oyuncuC.setLayout(new FlowLayout());
        oyuncuC.add(oyuncuIsmi);
        oyuncuC.add(oyuncuHedefMaliyet);
        oyuncuC.add(playerChedefmaliyet);
        oyuncuC.add(oyuncuHamleMaliyet);
        oyuncuC.add(playerChamlemaliyet);
        oyuncuC.add(baslangicAltin);
        oyuncuC.add(playerCbaslangicAltinMiktari);
        
        playerDhedefmaliyet =  new JFormattedTextField(NumberFormat.getNumberInstance());
        playerDhedefmaliyet.setValue(20L);
        playerDhedefmaliyet.setPreferredSize(ayarlabuton2.getPreferredSize());
        oyuncuIsmi = new JLabel("D :");
        oyuncuHedefMaliyet = new JLabel("Hedef Maliyet");
        playerDhamlemaliyet =  new JFormattedTextField(NumberFormat.getNumberInstance());
        playerDhamlemaliyet.setValue(5L);
        playerDhamlemaliyet.setPreferredSize(ayarlabuton2.getPreferredSize());
        oyuncuHamleMaliyet = new JLabel("Hamle Maliyet");
        playerDbaslangicAltinMiktari =new JFormattedTextField(NumberFormat.getNumberInstance());
        playerDbaslangicAltinMiktari.setValue(200L);
        playerDbaslangicAltinMiktari.setPreferredSize(ayarlabuton2.getPreferredSize());
        baslangicAltin = new JLabel("Altin Miktari :");
        
         JPanel oyuncuD = new JPanel();
        oyuncuD.setLayout(new FlowLayout());
        oyuncuD.add(oyuncuIsmi);
        oyuncuD.add(oyuncuHedefMaliyet);
        oyuncuD.add(playerDhedefmaliyet);
        oyuncuD.add(oyuncuHamleMaliyet);
        oyuncuD.add(playerDhamlemaliyet);
        oyuncuD.add(baslangicAltin);
        oyuncuD.add(playerDbaslangicAltinMiktari);
        
        playerAdimSayisi = new JFormattedTextField(NumberFormat.getNumberInstance());
        playerAdimSayisi.setValue(3L);
        playerAdimSayisi.setPreferredSize(ayarlabuton2.getPreferredSize());
        adimSayisi = new JLabel("Adim Sayisi");
        JPanel hamleSayi = new JPanel();
        hamleSayi.setLayout(new FlowLayout());
        hamleSayi.add(adimSayisi);
        hamleSayi.add(playerAdimSayisi);
        
        JPanel b = new JPanel();
        b.setLayout(new BoxLayout(b, BoxLayout.Y_AXIS));
        b.add(oyuncuA);
        b.add(oyuncuB);
        b.add(oyuncuC);
        b.add(oyuncuD);
        b.add(hamleSayi);
        b.add(ayarlabuton2);
        oyuncular.add(b);
        oyuncular.setBorder(BorderFactory.createTitledBorder("Oyuncu Ayarlar"));
        
        sonuc = new JPanel();
        sonuc.setLayout(new FlowLayout());
        sonuc.setBorder(BorderFactory.createTitledBorder("Çalıştır"));
        baslabuton = new JButton("Çalıştır");
        baslabuton.addActionListener(this);
       
        sonuc.add(baslabuton);
        sonuclar = new JPanel();
        sonuclar.setBorder(BorderFactory.createTitledBorder("Sonuçlar"));
        sonuclab = new JLabel();
        sonuclab2 = new JLabel();
        sonuclar.add(sonuclab);
        sonuclar.add(sonuclab2);
        ayarlar.add(generate);
        ayarlar.add(oyuncular);
        ayarlar.add(sonuc);
        ayarlar.add(sonuclar);
        
    }
    
    
    public String kazanan (){
        if(PlayerA.getAltinMiktari()>PlayerB.getAltinMiktari() && PlayerA.getAltinMiktari()>PlayerC.getAltinMiktari() && PlayerA.getAltinMiktari()> PlayerD.getAltinMiktari() )
            return "Kazanan A Oyuncusu ";
        else if (PlayerB.getAltinMiktari()>PlayerA.getAltinMiktari() && PlayerB.getAltinMiktari()>PlayerC.getAltinMiktari() && PlayerB.getAltinMiktari() > PlayerD.getAltinMiktari())
             return "Kazanan B Oyuncusu";
        else if (PlayerC.getAltinMiktari()>PlayerA.getAltinMiktari() && PlayerC.getAltinMiktari()>PlayerB.getAltinMiktari() && PlayerC.getAltinMiktari() > PlayerD.getAltinMiktari())
             return "Kazanan C Oyuncusu";
        else if  (PlayerD.getAltinMiktari() > PlayerA.getAltinMiktari() && PlayerD.getAltinMiktari() > PlayerB.getAltinMiktari() && PlayerD.getAltinMiktari() > PlayerC.getAltinMiktari())
            return "Kazanan D Oyuncusu";
        else if(PlayerA.getAltinMiktari()== PlayerB.getAltinMiktari() && PlayerA.getAltinMiktari()>PlayerC.getAltinMiktari() && PlayerA.getAltinMiktari()>PlayerD.getAltinMiktari())
            return "Kazanan A ve B Oyuncusu";
        else if(PlayerB.getAltinMiktari()== PlayerC.getAltinMiktari() && PlayerB.getAltinMiktari()>PlayerA.getAltinMiktari() && PlayerB.getAltinMiktari()> PlayerD.getAltinMiktari())
            return "Kazanan B ve C Oyuncusu";
        else if(PlayerC.getAltinMiktari()== PlayerA.getAltinMiktari() && PlayerC.getAltinMiktari()>PlayerB.getAltinMiktari()&& PlayerC.getAltinMiktari()> PlayerD.getAltinMiktari())
            return "Kazanan A ve C Oyuncusu";
        else if (PlayerD.getAltinMiktari() == PlayerA.getAltinMiktari() && PlayerD.getAltinMiktari()>PlayerC.getAltinMiktari() && PlayerD.getAltinMiktari()> PlayerB.getAltinMiktari())
            return "Kazanan D ve A Oyuncusu";
        else if (PlayerD.getAltinMiktari() == PlayerB.getAltinMiktari() && PlayerD.getAltinMiktari()>PlayerC.getAltinMiktari() && PlayerD.getAltinMiktari()> PlayerA.getAltinMiktari())
            return "Kazanan D ve B Oyuncusu";
        else if (PlayerD.getAltinMiktari() == PlayerC.getAltinMiktari() && PlayerD.getAltinMiktari()>PlayerA.getAltinMiktari() && PlayerD.getAltinMiktari()> PlayerB.getAltinMiktari())
            return "Kazanan D ve C Oyuncusu";
        else
            return "Kazanan Yok Hepsinin ayni altin miktari var .";
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        JButton source = (JButton) e.getSource();
        if(source == ayarlabuton1){
            Long brx = (Long) oyunsatir.getValue();
            Long bry = (Long) oyunsutun.getValue();
            Long ao = (Long)altinotxt.getValue();
            Long gao = (Long)galtinotxt.getValue();
          
            boardx = brx.intValue();
            boardy = bry.intValue();
            altinoran = ao.intValue();
            galtinoran = gao.intValue();
             //setSize(boardx*80,boardy*50);
            oyunBoard = new Oyun(boardx,boardy,altinoran,galtinoran); 
            
            playerAhedefmaliyet.setValue(5L);
            playerAhamlemaliyet.setValue(5L);
            playerAbaslangicAltinMiktari.setValue(200L);
            playerBhedefmaliyet.setValue(10L);
            playerBhamlemaliyet.setValue(5L);
            playerBbaslangicAltinMiktari.setValue(200L);
            playerChedefmaliyet.setValue(15L);
            playerChamlemaliyet.setValue(5L);
            playerCbaslangicAltinMiktari.setValue(200L);
            playerDhedefmaliyet.setValue(20L);
            playerDhamlemaliyet.setValue(5L);
            playerDbaslangicAltinMiktari.setValue(200L);
            playerAdimSayisi.setValue(3L);
             
            startA = new Point(0,0);
            startB = new Point(0,boardy-1);
            PlayerA = new playerA(startA);
            PlayerA.hedefBelirle(oyunBoard);
            PlayerB = new playerB(startB);
            PlayerB.hedefBelirle(oyunBoard);
            startC = new Point(boardx-1,boardy-1);
            PlayerC = new playerC(startC);
            startD = new Point(boardx-1,0);
            PlayerD = new playerD(startD);
            oyunUI = new OyunUI(oyunBoard);
            PlayerC.hedefBelirle(oyunBoard,oyunUI);
            oyun.removeAll();
            oyun.repaint();
            oyun.revalidate();
            sonuclab.setText("");
            sonuclab2.setText("");
            oyun.add(oyunUI);
        }
        if(source == ayarlabuton2){
            
            Long ahedefmaliyet = (Long) playerAhedefmaliyet.getValue();
            Long ahamlemaliyet = (Long) playerAhamlemaliyet.getValue();
            Long aAltinMiktari = (Long) playerAbaslangicAltinMiktari.getValue();
            Long bhedefmaliyet = (Long) playerBhedefmaliyet.getValue();
            Long bhamlemaliyet = (Long) playerBhamlemaliyet.getValue();
            Long bAltinMiktari = (Long) playerBbaslangicAltinMiktari.getValue();
            Long chedefmaliyet = (Long) playerChedefmaliyet.getValue();
            Long chamlemaliyet = (Long) playerChamlemaliyet.getValue();
            Long cAltinMiktari = (Long) playerCbaslangicAltinMiktari.getValue();
            Long dhedefmaliyet = (Long) playerDhedefmaliyet.getValue();
            Long dhamlemaliyet = (Long) playerDhamlemaliyet.getValue();
            Long dAltinMiktari = (Long) playerDbaslangicAltinMiktari.getValue();
            Long AdimSayisi = (Long) playerAdimSayisi.getValue();
            
            PlayerA.setHedefBelirlemeMaliyet(ahedefmaliyet.intValue());
            PlayerA.setHamleMaliyet(ahamlemaliyet.intValue());
            PlayerA.setAltinMiktari(aAltinMiktari.intValue());
            PlayerB.setHedefBelirlemeMaliyet(bhedefmaliyet.intValue());
            PlayerB.setHamleMaliyet(bhamlemaliyet.intValue());
            PlayerB.setAltinMiktari(bAltinMiktari.intValue());
            PlayerC.setHedefBelirlemeMaliyet(chedefmaliyet.intValue());
            PlayerC.setHamleMaliyet(chamlemaliyet.intValue());
            PlayerC.setAltinMiktari(cAltinMiktari.intValue());
            PlayerD.setHedefBelirlemeMaliyet(dhedefmaliyet.intValue());
            PlayerD.setHamleMaliyet(dhamlemaliyet.intValue());
            PlayerD.setAltinMiktari(dAltinMiktari.intValue());
            
            PlayerA.setAdimSayisi(AdimSayisi.intValue());
            PlayerB.setAdimSayisi(AdimSayisi.intValue());
            PlayerC.setAdimSayisi(AdimSayisi.intValue());
            PlayerD.setAdimSayisi(AdimSayisi.intValue());
            
        }
        if(source == baslabuton){
            while(!oyunBoard.gameover){
           
               oyun.revalidate();
               oyun.repaint();
               if(!(PlayerA.gameoverA)){
                  PlayerA.yollarıbul(PlayerA.getStartPoint(), PlayerA.getEndPoint(),oyunBoard);
               if(oyunBoard.getGrid()[PlayerA.getEndPoint().x][PlayerA.getEndPoint().y].isAltin())
                    oyunUI.hareketA(PlayerA);
               else{
                    PlayerA.setHedefAldi(true);
                    PlayerA.hedefBelirle(oyunBoard);
                    PlayerA.yollarıbul(PlayerA.getStartPoint(), PlayerA.getEndPoint(),oyunBoard);
                    oyunUI.hareketA(PlayerA);  
                }
            
               if(PlayerA.getHedef()){
                    PlayerA.hedefBelirle(oyunBoard);
                }
               else
                    System.out.println("A oyuncu Hedef Alınmadı Devam Ediyor!!");
                }
               
               
               if(!(PlayerB.gameoverB)  ){
                    PlayerB.yollarıbul(PlayerB.getStartPoint(), PlayerB.getEndPoint(),oyunBoard);
               if(oyunBoard.getGrid()[PlayerB.getEndPoint().x][PlayerB.getEndPoint().y].isAltin())
                    oyunUI.hareketB(PlayerB);
              else{
                    PlayerB.setHedefAldi(true);
                    PlayerB.hedefBelirle(oyunBoard);
                    PlayerB.yollarıbul(PlayerB.getStartPoint(), PlayerB.getEndPoint(),oyunBoard);
                    oyunUI.hareketB(PlayerB);
                }
               if(PlayerB.getHedef())
                    PlayerB.hedefBelirle(oyunBoard);
               else
                    System.out.println("B oyuncu Hedef Alınmadı Devam Ediyor!!");
                
                }
             
               if(!(PlayerC.gameoverC) ){
                     PlayerC.yollarıbul(PlayerC.getStartPoint(),PlayerC.getEndPoint(),oyunBoard);
               if(oyunBoard.getGrid()[PlayerC.getEndPoint().x][PlayerC.getEndPoint().y].isAltin())
                    oyunUI.hareketC(PlayerC);
              else{
                    PlayerC.setHedefAldi(true);
                    PlayerC.hedefBelirle(oyunBoard,oyunUI);
                    PlayerC.yollarıbul(PlayerC.getStartPoint(),PlayerC.getEndPoint(),oyunBoard);
                    oyunUI.hareketC(PlayerC);
                }
               if(PlayerC.getHedef())
                    PlayerC.hedefBelirle(oyunBoard,oyunUI);
              else
                   System.out.println("C oyuncu Hedef Alınmadı Devam Ediyor !!");
               
                }
                   
                Point endA = new Point(PlayerA.getEndPoint());
                Point endB = new Point(PlayerB.getEndPoint());
                Point endC = new Point(PlayerC.getEndPoint());
                    
               if(!(PlayerD.gameoverD) ){
                   if(PlayerD.getHedef())
                        PlayerD.hedefBelirle(oyunBoard,endA,PlayerA.getStartPoint(),endB,PlayerB.getStartPoint(),endC,PlayerC.getStartPoint());
                   else
                        System.out.println("D oyuncu Hedef Alınmadı Devam Ediyor !!");
                   PlayerD.yollarıbul(PlayerD.getStartPoint(),PlayerD.getEndPoint(),oyunBoard);
                   Point endD = new Point(PlayerD.getEndPoint());
                   if(oyunBoard.getGrid()[endD.x][endD.y].isAltin() && kontrol(endA,endB, endC, endD))
                        oyunUI.hareketD(PlayerD);
                   else{
                       PlayerD.setHedefAldi(true);
                       PlayerD.hedefBelirle(oyunBoard,endA,PlayerA.getStartPoint(),endB,PlayerB.getStartPoint(),endC,PlayerC.getStartPoint());
                       PlayerD.yollarıbul(PlayerD.getStartPoint(),PlayerD.getEndPoint(),oyunBoard);
                       oyunUI.hareketD(PlayerD);
                    }
               }
               
               if((PlayerB.gameoverB && PlayerA.gameoverA && PlayerC.gameoverC && PlayerD.gameoverD)){
                  break;
                }
           
          
            }
        if((PlayerB.gameoverB && PlayerA.gameoverA && PlayerC.gameoverC && PlayerD.gameoverD)){
            sonuclab.setText(" Oyun Bitti !!");
            sonuclab2.setText(kazanan());
        }
        
        PlayerA.setGidilenYollar(PlayerA.getEndPoint());
        PlayerB.setGidilenYollar(PlayerB.getEndPoint());
        PlayerC.setGidilenYollar(PlayerC.getEndPoint());
        PlayerD.setGidilenYollar(PlayerD.getEndPoint());
        file = new File("C:\\Users\\AYAZ\\Documents\\NetBeansProjects\\AltinToplamaOyunu\\ResultA.txt");
        fileWrite(PlayerA);
        file = new File("C:\\Users\\AYAZ\\Documents\\NetBeansProjects\\AltinToplamaOyunu\\ResultB.txt");
        fileWrite(PlayerB);
        file = new File("C:\\Users\\AYAZ\\Documents\\NetBeansProjects\\AltinToplamaOyunu\\ResultC.txt");
        fileWrite(PlayerC);
        file = new File("C:\\Users\\AYAZ\\Documents\\NetBeansProjects\\AltinToplamaOyunu\\ResultD.txt");
        fileWrite(PlayerD);
        
        System.out.println("A'NIN ALTIN SAYISI : "+PlayerA.getAltinMiktari()+"\nB'NIN ALTIN SAYISI : "+PlayerB.getAltinMiktari()+"\nC'NIN ALTIN SAYISI : "+PlayerC.getAltinMiktari()+"\nD'NIN ALTIN SAYISI : "+PlayerD.getAltinMiktari());
        
        }
        
    }
    
    
    public boolean kontrol(Point endA,Point endB,Point endC,Point endD){
        boolean donus = true;
            if(mesafe(PlayerD.getStartPoint(),endA) > mesafe(PlayerA.getStartPoint(),endA))
                donus = false;
            if(mesafe(PlayerD.getStartPoint(),endB) > mesafe(PlayerB.getStartPoint(),endB))
                donus = false;
            if(mesafe(PlayerD.getStartPoint(),endC) > mesafe(PlayerC.getStartPoint(),endC))
                donus = false;
                
         
        return donus;
     }
            
     public int mesafe(Point a,Point b){
        return  Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
    }
     
     
}

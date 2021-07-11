
package struct;
import java.awt.Point;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Oyun {
    public static boolean gameover = false;
    private int lines,cols,goldRation,hiddenGoldenRation;
    private Node[][] grid;
    private final Point start = new Point(0,0);
    public ArrayList<Node> goldArrayList = new ArrayList();
    public ArrayList<Node> hiddenGoldenArrayList = new ArrayList();
    public Oyun(int lines,int cols,int altinoran,int galtinoran){
        this.lines = lines;
        this.cols=cols;
        this.grid = new Node[lines][cols];
        this.goldRation=altinoran;
        this.hiddenGoldenRation=galtinoran;
        initGrid();
        goldinitGrid(); 
    }
    
    private void initGrid(){
        for(int i = 0;i<lines;i++){
                for(int j =0;j<cols;j++ ){
                    Node n = new Node(i,j);
                    grid[i][j]= n;
                }
        }
    }
    private void goldinitGrid(){
        int goldNumber = (lines*cols*goldRation)/100, hiddenGoldenNumber = (goldNumber*hiddenGoldenRation)/100;
        int randIntx,randInty,golddeger,randx,randy,countAltin = 0, countGAltin = 0;
        
        while(countAltin != goldNumber){
            randIntx=((int)(Math.random()*lines));
            randInty=((int)(Math.random()*cols));
            if(randIntx== lines)
                 randIntx = randIntx - 1;
            if(randInty== cols)
                randInty = randInty - 1;
            Node n = new Node(randIntx,randInty);
            
            if(goldArrayList.contains(n) == false && (n.p.equals(getStart())) == false ){
                goldArrayList.add(n);
                golddeger = random(20,5);  
                n.setGoldBool(true);
                n.setGoldAmount(golddeger);
                countAltin++;
                grid[randIntx][randInty] = n;   
            }
        }
            
        while(countGAltin != hiddenGoldenNumber){
            randx=((int)(Math.random()*lines));
            randy=((int)(Math.random()*cols));
            if(randx== lines)
                 randx = randx - 1;
            if(randy== cols)
                randy = randy - 1;
            Node m = new Node(randx,randy);
            if(hiddenGoldenArrayList.contains(m) == false && (m.p.equals(getStart())) == false){
                hiddenGoldenArrayList.add(m);
                golddeger = random(20,5);
                if(m.p.equals(start))
                    m.setHiddenGoldenVisible(false);
                m.setHiddenGoldenVisible(true);
                m.setHiddenGoldenAmount(golddeger);
                countGAltin++;
                grid[randx][randy] = m;        
            }
            
        }
        System.out.println("Gold Number  : "+countAltin+" Hidden Golden Number : "+countGAltin);
    
    }

     public int distance(Point a,Point b){
        return  Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
    }
    
    public int random (int n,int m){
        Random rand = new Random();
        int value = rand.nextInt(n)+1;
        return value%m == 0 ? value:random(n,m);
    }        
    
    
    
    
    public Node getNode(Point p) {
        return grid[p.x][p.y];
    }
    public int getLines(){
        return lines;
    }
    
    public int getCols(){
        return cols;
    }
    
    public Node[][] getGrid(){
        return grid;
    }
    
    public Point getStart(){
        return start;
    }
    public Point getStartA(){
        return start;
    }
    public Point getStartB(){
        return new Point(0,getCols()-1);
    }
    public Point getStartC(){
        return new Point(getLines()-1,getCols()-1);
    }
    public Point getStartD(){
        return new Point(getLines()-1,0);
    }
    
  
}

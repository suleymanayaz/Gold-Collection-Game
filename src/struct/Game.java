
package struct;
import java.awt.Point;

import java.util.ArrayList;
import java.util.Random;

public class Game {
    public static boolean _game_Over = false;
    private int _lines,_cols,_gold_Ration,_hidden_Golden_Ration;
    private Node[][] _grid;
    private final Point start = new Point(0,0);
    public ArrayList<Node> _gold_Array_List = new ArrayList();
    public ArrayList<Node> _hidden_Golden_Array_List = new ArrayList();
    public Game(int _lines,int _cols,int _gold_Ration,int _hidden_Golden_Ration){
        this._lines = _lines;
        this._cols=_cols;
        this._grid = new Node[_lines][_cols];
        this._gold_Ration=_gold_Ration;
        this._hidden_Golden_Ration=_hidden_Golden_Ration;
        _initial_Grid();
        _gold_Initial_Grid(); 
    }
    
    private void _initial_Grid(){
        for(int i = 0;i<_lines;i++){
                for(int j =0;j<_cols;j++ ){
                    Node n = new Node(i,j);
                    _grid[i][j]= n;
                }
        }
    }
    private void _gold_Initial_Grid(){
        int goldNumber = (_lines*_cols*_gold_Ration)/100, hiddenGoldenNumber = (goldNumber*_hidden_Golden_Ration)/100;
        int randIntx,randInty,_gold_Value,randx,randy,_count_Gold = 0, _count_Hidden_Gold = 0;
        
        while(_count_Gold != goldNumber){
            randIntx=((int)(Math.random()*_lines));
            randInty=((int)(Math.random()*_cols));
            if(randIntx== _lines)
                 randIntx = randIntx - 1;
            if(randInty== _cols)
                randInty = randInty - 1;
            Node n = new Node(randIntx,randInty);
            
            if(_gold_Array_List.contains(n) == false && (n.getP().equals(getStart())) == false ){
                _gold_Array_List.add(n);
                _gold_Value = random(20,5);  
                n.setGold_Bool(true);
                n.setGold_Amount(_gold_Value);
                n.setGolden_Visible(true);
                _count_Gold++;
                _grid[randIntx][randInty] = n;   
            }
        }
            
        while(_count_Hidden_Gold != hiddenGoldenNumber){
            randx=((int)(Math.random()*_lines));
            randy=((int)(Math.random()*_cols));
            if(randx== _lines)
                 randx = randx - 1;
            if(randy== _cols)
                randy = randy - 1;
            Node m = new Node(randx,randy);
            if(_hidden_Golden_Array_List.contains(m) == false && (m.getP().equals(getStart())) == false){
                _hidden_Golden_Array_List.add(m);
                _gold_Value = random(20,5);
                if(m.getP().equals(start))
                    m.setHidden_Golden_Visible(false);
                m.setHidden_Golden_Visible(false);
                m.setHidden_Golden_Bool(true);
                m.setHidden_Golden_Amount(_gold_Value);   
                _count_Hidden_Gold++;
                _grid[randx][randy] = m;        
            }
            
        }
        System.out.println("Gold Number  : "+_count_Gold+" Hidden Golden Number : "+_count_Hidden_Gold);
    
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
        return _grid[p.x][p.y];
    }
    public int getLines(){
        return _lines;
    }
    
    public int getCols(){
        return _cols;
    }
    
    public Node[][] getGrid(){
        return _grid;
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

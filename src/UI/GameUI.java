
package UI;
import Players.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.util.ArrayList;
import struct.Game;
import struct.Node;

/**
 *
 * @author ayaz
 */
public class GameUI extends JPanel{
    /**
     * 
     */
    public NodeUI[][] _grid;
    private Game _game;
    private GridBagConstraints _gbc;
    private GridBagLayout _gbl;
    private Node _current_Node;
    public Color _dbg;
    public JLabel _gold_Label;
    public JLabel _player_A_Label;
    public JLabel _player_B_Label;
    public JLabel _player_C_Label;
    public JLabel _player_D_Label;
    
    public GameUI(Game _game){
        this._game = _game;
        this._gbl = new GridBagLayout();
        setLayout(_gbl);
        this._grid = new NodeUI[_game.getLines()][_game.getCols()];
        _gbc = new GridBagConstraints();
        _dbg = getBackground();
        _reset_Game_Board();
        paint(getGraphics());
    }
    
    public NodeUI[][] getGridUI(){
        return this._grid;
    }
    
    public void _reset_Game_Board(){
        NodeUI _temp_Node;
        for(int _rows = 0 ; _rows < _game.getLines(); _rows++){
            for(int _cols =0; _cols < _game.getCols(); _cols++){
                if(_grid[_rows][_cols] == null){
                    _temp_Node = new NodeUI(_game.getGrid()[_rows][_cols].p);
                    _grid[_rows][_cols] = _temp_Node;
                }
                _gbc.gridx = _cols;
                _gbc.gridy = _rows;
                setBackground(_dbg);
                
                if(_game.getGrid()[_rows][_cols].p.equals(_game.getStart())){
                    _player_A_Label = new JLabel();
                    _player_A_Label.setText("A");
                    _grid[_rows][_cols].add(_player_A_Label);
                }else if (_game.getGrid()[_rows][_cols].p.equals(_game.getStartB())){
                    _player_B_Label = new JLabel();
                    _player_B_Label.setText("B");
                    _grid[_rows][_cols].add(_player_B_Label);
                }else if (_game.getGrid()[_rows][_cols].p.equals(_game.getStartC())){
                    _player_C_Label = new JLabel();
                    _player_C_Label.setText("C");
                    _grid[_rows][_cols].add(_player_C_Label);
                }else if (_game.getGrid()[_rows][_cols].p.equals(_game.getStartD())){
                    _player_D_Label = new JLabel();
                    _player_D_Label.setText("D");
                    _grid[_rows][_cols].add(_player_D_Label);
                }else if (_game.getGrid()[_rows][_cols].isGold_Bool() && !(_game.getGrid()[_rows][_cols].p.equals(_game.getStart()))){
                    _gold_Label = new JLabel();
                    _gold_Label.setText(Integer.toString(_game.getGrid()[_rows][_cols].getGold_Amount()));
                    _grid[_rows][_cols].add(_gold_Label);
                }else if (_game.getGrid()[_rows][_cols].isHidden_Golden_Bool()){
                    _game.getGrid()[_rows][_cols].setHidden_Golden_Visible(false);
                }else
                    _grid[_rows][_cols].setBackground(_dbg);
                
                Border border = null;
                if(_rows < _game.getLines() -1){
                    if(_cols < _game.getCols() -1)
                        border = new MatteBorder(1, 1, 0, 0, Color.GRAY);
                    else
                        border = new MatteBorder(1, 1, 0, 1, Color.GRAY); 
                }else{
                    if(_cols < _game.getCols() -1 )
                        border = new MatteBorder(1, 1, 1, 0, Color.GRAY);
                    else
                        border = new MatteBorder(1, 1, 1, 1, Color.GRAY);
                }
                _grid[_rows][_cols].setBorder(border);
                add(_grid[_rows][_cols],_gbc);
            }
        }
    }
    
    public void _move_Player_A(PlayerA _player_A){
        if(_player_A.getGold_Amount() - _player_A.getMove_Cost() >= 0 && (_player_A.isHave_Gold_Bool() == false)){
            _player_A.setGold_Amount_Spent(_player_A.getGold_Amount_Spent() + _player_A.getMove_Cost());
            ArrayList<Point> _rota_Array_List = _player_A.get_Rota_List();
            _grid[_player_A.getStart().x][_player_A.getStart().y].remove(_player_A_Label);
            
            Point _temp_End_Node = new Point();
            for(Point _temp_Node : _rota_Array_List){
                _player_A.getPlayer_Passed_Rota_Array_List().add(_temp_Node);
                _player_A.setTotal_Number_Steps(_player_A.getSteps_Number() + 1);
                _grid[_temp_Node.x][_temp_Node.y].add(_player_A_Label);
                _grid[_temp_Node.x][_temp_Node.y].setBackground(Color.BLUE);
                this.paintAll(getGraphics());
                try{
                    Thread.sleep(200);
                }catch(InterruptedException e){
                     System.out.println("e.printStackTrace()");
                }
                if(_game.getGrid()[_temp_Node.x][_temp_Node.y].isHidden_Golden_Bool()){
                    if(_game.getGrid()[_temp_Node.x][_temp_Node.y].isHidden_Golden_Visible()){
                        _player_A.setGold_Amount(_player_A.getGold_Amount() + _game.getGrid()[_temp_Node.x][_temp_Node.y].getHidden_Golden_Amount());
                        _player_A.setAmount_Gold_Collected(_player_A.getAmount_Gold_Collected() + _game.getGrid()[_temp_Node.x][_temp_Node.y].getHidden_Golden_Amount());
                        _game.getGrid()[_temp_Node.x][_temp_Node.y].setHidden_Golden_Visible(false);
                        _grid[_temp_Node.x][_temp_Node.y].removeAll();
                        
                    }else{
                        _game.getGrid()[_temp_Node.x][_temp_Node.y].setHidden_Golden_Visible(true);
                        _gold_Label = new JLabel();
                        _gold_Label.setText(Integer.toString(_game.getGrid()[_temp_Node.x][_temp_Node.y].getHidden_Golden_Amount()));
                        _grid[_temp_Node.x][_temp_Node.y].add(_gold_Label);
                    }
                }
                if(_game.getGrid()[_temp_Node.x][_temp_Node.y].isGold_Bool()){
                    _player_A.setGold_Amount(_player_A.getGold_Amount() + _game.getGrid()[_temp_Node.x][_temp_Node.y].getGold_Amount());
                    _player_A.setAmount_Gold_Collected(_player_A.getAmount_Gold_Collected() + _game.getGrid()[_temp_Node.x][_temp_Node.y].getGold_Amount());
                    _game.getGrid()[_temp_Node.x][_temp_Node.y].setGold_Bool(false);
                    _grid[_temp_Node.x][_temp_Node.y].removeAll();
                    
                }
                _grid[_temp_Node.x][_temp_Node.y].setBackground(_dbg);
                _grid[_temp_Node.x][_temp_Node.y].remove(_player_A_Label);
                _temp_End_Node = _temp_Node;
            }
            
            _player_A.setTotal_Number_Steps(_player_A.getSteps_Number() - 1 );
            _player_A.setGold_Amount(_player_A.getGold_Amount() - _player_A.getMove_Cost());
            _grid[_temp_End_Node.x][_temp_End_Node.y].add(_player_A_Label);
            _player_A.setStart(_temp_End_Node);
            _player_A.getPlayer_Passed_Rota_Array_List().remove(_player_A.getPlayer_Passed_Rota_Array_List().size()-1);
            
            
        }else{
            if(!_player_A.isHave_Gold_Bool()){
                System.out.println("Game over Player A: "+ _player_A.getGold_Amount());
                _player_A.setHave_Gold_Bool(true);
            }
        }
    }
    
    public void _move_Player_B(PlayerB _player_B){
        if(_player_B.getGold_Amount() - _player_B.getMove_Cost() >= 0 && (_player_B.isHave_Gold_Bool() == false)){
            _player_B.setGold_Amount_Spent(_player_B.getGold_Amount_Spent() + _player_B.getMove_Cost());
            ArrayList<Point> _rota_Array_List = _player_B.get_Rota_List();
            _grid[_player_B.getStart().x][_player_B.getStart().y].remove(_player_B_Label);
            
            Point _temp_End_Node = new Point();
            for(Point _temp_Node : _rota_Array_List){
                _player_B.getPlayer_Passed_Rota_Array_List().add(_temp_Node);
                _player_B.setTotal_Number_Steps(_player_B.getSteps_Number() + 1);
                _grid[_temp_Node.x][_temp_Node.y].add(_player_B_Label);
                _grid[_temp_Node.x][_temp_Node.y].setBackground(Color.RED);
                this.paintAll(getGraphics());
                try{
                    Thread.sleep(200);
                }catch(InterruptedException e){
                     System.out.println("e.printStackTrace()");
                }
                if(_game.getGrid()[_temp_Node.x][_temp_Node.y].isHidden_Golden_Bool()){
                    if(_game.getGrid()[_temp_Node.x][_temp_Node.y].isHidden_Golden_Visible()){
                        _player_B.setGold_Amount(_player_B.getGold_Amount() + _game.getGrid()[_temp_Node.x][_temp_Node.y].getHidden_Golden_Amount());
                        _player_B.setAmount_Gold_Collected(_player_B.getAmount_Gold_Collected() + _game.getGrid()[_temp_Node.x][_temp_Node.y].getHidden_Golden_Amount());
                        _game.getGrid()[_temp_Node.x][_temp_Node.y].setHidden_Golden_Visible(false);
                        _grid[_temp_Node.x][_temp_Node.y].removeAll();
                        
                    }else{
                        _game.getGrid()[_temp_Node.x][_temp_Node.y].setHidden_Golden_Visible(true);
                        _gold_Label = new JLabel();
                        _gold_Label.setText(Integer.toString(_game.getGrid()[_temp_Node.x][_temp_Node.y].getHidden_Golden_Amount()));
                        _grid[_temp_Node.x][_temp_Node.y].add(_gold_Label);
                    }
                }
                if(_game.getGrid()[_temp_Node.x][_temp_Node.y].isGold_Bool()){
                    _player_B.setGold_Amount(_player_B.getGold_Amount() + _game.getGrid()[_temp_Node.x][_temp_Node.y].getGold_Amount());
                    _player_B.setAmount_Gold_Collected(_player_B.getAmount_Gold_Collected() + _game.getGrid()[_temp_Node.x][_temp_Node.y].getGold_Amount());
                    _game.getGrid()[_temp_Node.x][_temp_Node.y].setGold_Bool(false);
                    _grid[_temp_Node.x][_temp_Node.y].removeAll();
                    
                }
                _grid[_temp_Node.x][_temp_Node.y].setBackground(_dbg);
                _grid[_temp_Node.x][_temp_Node.y].remove(_player_B_Label);
                _temp_End_Node = _temp_Node;
            }
            
            _player_B.setTotal_Number_Steps(_player_B.getSteps_Number() - 1 );
            _player_B.setGold_Amount(_player_B.getGold_Amount() - _player_B.getMove_Cost());
            _grid[_temp_End_Node.x][_temp_End_Node.y].add(_player_B_Label);
            _player_B.setStart(_temp_End_Node);
            _player_B.getPlayer_Passed_Rota_Array_List().remove(_player_B.getPlayer_Passed_Rota_Array_List().size()-1);
            
            
        }else{
            if(!_player_B.isHave_Gold_Bool()){
                System.out.println("Game over Player B: "+ _player_B.getGold_Amount());
                _player_B.setHave_Gold_Bool(true);
            }
        }
    }
    
    public void _move_Player_C(PlayerC _player_C){
        if(_player_C.getGold_Amount() - _player_C.getMove_Cost() >= 0 && (_player_C.isHave_Gold_Bool() == false)){
            _player_C.setGold_Amount_Spent(_player_C.getGold_Amount_Spent() + _player_C.getMove_Cost());
            ArrayList<Point> _rota_Array_List = _player_C.get_Rota_List();
            _grid[_player_C.getStart().x][_player_C.getStart().y].remove(_player_C_Label);
            
            Point _temp_End_Node = new Point();
            for(Point _temp_Node : _rota_Array_List){
                _player_C.getPlayer_Passed_Rota_Array_List().add(_temp_Node);
                _player_C.setTotal_Number_Steps(_player_C.getSteps_Number() + 1);
                _grid[_temp_Node.x][_temp_Node.y].add(_player_C_Label);
                _grid[_temp_Node.x][_temp_Node.y].setBackground(Color.ORANGE);
                this.paintAll(getGraphics());
                try{
                    Thread.sleep(200);
                }catch(InterruptedException e){
                     System.out.println("e.printStackTrace()");
                }
                if(_game.getGrid()[_temp_Node.x][_temp_Node.y].isHidden_Golden_Bool()){
                    if(_game.getGrid()[_temp_Node.x][_temp_Node.y].isHidden_Golden_Visible()){
                        _player_C.setGold_Amount(_player_C.getGold_Amount() + _game.getGrid()[_temp_Node.x][_temp_Node.y].getHidden_Golden_Amount());
                        _player_C.setAmount_Gold_Collected(_player_C.getAmount_Gold_Collected() + _game.getGrid()[_temp_Node.x][_temp_Node.y].getHidden_Golden_Amount());
                        _game.getGrid()[_temp_Node.x][_temp_Node.y].setHidden_Golden_Visible(false);
                        _grid[_temp_Node.x][_temp_Node.y].removeAll();
                        
                    }else{
                        _game.getGrid()[_temp_Node.x][_temp_Node.y].setHidden_Golden_Visible(true);
                        _gold_Label = new JLabel();
                        _gold_Label.setText(Integer.toString(_game.getGrid()[_temp_Node.x][_temp_Node.y].getHidden_Golden_Amount()));
                        _grid[_temp_Node.x][_temp_Node.y].add(_gold_Label);
                    }
                }
                if(_game.getGrid()[_temp_Node.x][_temp_Node.y].isGold_Bool()){
                    _player_C.setGold_Amount(_player_C.getGold_Amount() + _game.getGrid()[_temp_Node.x][_temp_Node.y].getGold_Amount());
                    _player_C.setAmount_Gold_Collected(_player_C.getAmount_Gold_Collected() + _game.getGrid()[_temp_Node.x][_temp_Node.y].getGold_Amount());
                    _game.getGrid()[_temp_Node.x][_temp_Node.y].setGold_Bool(false);
                    _grid[_temp_Node.x][_temp_Node.y].removeAll();
                    
                }
                _grid[_temp_Node.x][_temp_Node.y].setBackground(_dbg);
                _grid[_temp_Node.x][_temp_Node.y].remove(_player_C_Label);
                _temp_End_Node = _temp_Node;
            }
            
            _player_C.setTotal_Number_Steps(_player_C.getSteps_Number() - 1 );
            _player_C.setGold_Amount(_player_C.getGold_Amount() - _player_C.getMove_Cost());
            _grid[_temp_End_Node.x][_temp_End_Node.y].add(_player_C_Label);
            _player_C.setStart(_temp_End_Node);
            _player_C.getPlayer_Passed_Rota_Array_List().remove(_player_C.getPlayer_Passed_Rota_Array_List().size()-1);
            
            
        }else{
            if(!_player_C.isHave_Gold_Bool()){
                System.out.println("Game over Player C: "+ _player_C.getGold_Amount());
                _player_C.setHave_Gold_Bool(true);
            }
        }
    }
    
    public void _move_Player_D(PlayerD _player_D){
        if(_player_D.getGold_Amount() - _player_D.getMove_Cost() >= 0 && (_player_D.isHave_Gold_Bool() == false)){
            _player_D.setGold_Amount_Spent(_player_D.getGold_Amount_Spent() + _player_D.getMove_Cost());
            ArrayList<Point> _rota_Array_List = _player_D.get_Rota_List();
            _grid[_player_D.getStart().x][_player_D.getStart().y].remove(_player_D_Label);
            
            Point _temp_End_Node = new Point();
            for(Point _temp_Node : _rota_Array_List){
                _player_D.getPlayer_Passed_Rota_Array_List().add(_temp_Node);
                _player_D.setTotal_Number_Steps(_player_D.getSteps_Number() + 1);
                _grid[_temp_Node.x][_temp_Node.y].add(_player_D_Label);
                _grid[_temp_Node.x][_temp_Node.y].setBackground(Color.YELLOW);
                this.paintAll(getGraphics());
                try{
                    Thread.sleep(200);
                }catch(InterruptedException e){
                     System.out.println("e.printStackTrace()");
                }
                if(_game.getGrid()[_temp_Node.x][_temp_Node.y].isHidden_Golden_Bool()){
                    if(_game.getGrid()[_temp_Node.x][_temp_Node.y].isHidden_Golden_Visible()){
                        _player_D.setGold_Amount(_player_D.getGold_Amount() + _game.getGrid()[_temp_Node.x][_temp_Node.y].getHidden_Golden_Amount());
                        _player_D.setAmount_Gold_Collected(_player_D.getAmount_Gold_Collected() + _game.getGrid()[_temp_Node.x][_temp_Node.y].getHidden_Golden_Amount());
                        _game.getGrid()[_temp_Node.x][_temp_Node.y].setHidden_Golden_Visible(false);
                        _grid[_temp_Node.x][_temp_Node.y].removeAll();
                        
                    }else{
                        _game.getGrid()[_temp_Node.x][_temp_Node.y].setHidden_Golden_Visible(true);
                        _gold_Label = new JLabel();
                        _gold_Label.setText(Integer.toString(_game.getGrid()[_temp_Node.x][_temp_Node.y].getHidden_Golden_Amount()));
                        _grid[_temp_Node.x][_temp_Node.y].add(_gold_Label);
                    }
                }
                if(_game.getGrid()[_temp_Node.x][_temp_Node.y].isGold_Bool()){
                    _player_D.setGold_Amount(_player_D.getGold_Amount() + _game.getGrid()[_temp_Node.x][_temp_Node.y].getGold_Amount());
                    _player_D.setAmount_Gold_Collected(_player_D.getAmount_Gold_Collected() + _game.getGrid()[_temp_Node.x][_temp_Node.y].getGold_Amount());
                    _game.getGrid()[_temp_Node.x][_temp_Node.y].setGold_Bool(false);
                    _grid[_temp_Node.x][_temp_Node.y].removeAll();
                    
                }
                _grid[_temp_Node.x][_temp_Node.y].setBackground(_dbg);
                _grid[_temp_Node.x][_temp_Node.y].remove(_player_D_Label);
                _temp_End_Node = _temp_Node;
            }
            
            _player_D.setTotal_Number_Steps(_player_D.getSteps_Number() - 1 );
            _player_D.setGold_Amount(_player_D.getGold_Amount() - _player_D.getMove_Cost());
            _grid[_temp_End_Node.x][_temp_End_Node.y].add(_player_D_Label);
            _player_D.setStart(_temp_End_Node);
            _player_D.getPlayer_Passed_Rota_Array_List().remove(_player_D.getPlayer_Passed_Rota_Array_List().size()-1);
            
            
        }else{
            if(!_player_D.isHave_Gold_Bool()){
                System.out.println("Game over Player D: "+ _player_D.getGold_Amount());
                _player_D.setHave_Gold_Bool(true);
            }
        }
    }

   
}

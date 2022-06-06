/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Players;

import UI.GameUI;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JLabel;
import struct.Game;

/**
 *
 * @author ayaz
 */
public class PlayerC extends Player {
     /**
     * 
     */
    private int _move_Cost;
    private int _target_Cost;
    private int _gold_Amount;
    private int _gold_Amount_Spent;
    private int _total_Number_Steps;
    private int _amount_Gold_Collected;
    private int _steps_Number;
    private boolean _have_Gold_Bool;
    private boolean _have_Target;
    private Point _start,_end;
    private ArrayList<Point> _player_Passed_Rota_Array_List;
    private ArrayList<Point> _ways;
    private ArrayList<Point> _close_Neighbors;
    private ArrayList<Point> _far_Neighbors;
    private Point _current_Point;
    private Point _temp_Point;
    private Point _last_Point;
    
    public PlayerC(Point _start){
        this._start = _start;
        this._move_Cost = 5;
        this._target_Cost = 15;
        this._gold_Amount = 200;
        this._steps_Number = 3;
        this._have_Target = true;
        this._have_Gold_Bool = false;
        this._total_Number_Steps = 0;
        this._gold_Amount_Spent = 0;
        this._amount_Gold_Collected = 0;
        this._player_Passed_Rota_Array_List = new ArrayList<>();
    }

     public void _make_Hidden_Gold_Visible(Game _game, GameUI _game_UI){
        Point _temp_start = new Point(_start);
        Point _hidden_Golden = new Point();
        Point _temp_Hidden_Golden = new Point();
        Point _temp_2_Hidden_Golden = new Point();
        int _new_Distance = 0;
        int _last_Distance = 0;
        
         for (int _rows = 0; _rows < _game.getLines(); _rows++){
                for(int _cols = 0; _cols < _game.getCols(); _cols++ ){
                    if(_game.getGrid()[_rows][_cols].isHidden_Golden_Bool() && !(_game.getGrid()[_rows][_cols].isHidden_Golden_Visible())){
                       _hidden_Golden = _game.getGrid()[_rows][_cols].getP();
                       _new_Distance = _distance(_start, _hidden_Golden);
                       _temp_Hidden_Golden = _hidden_Golden;
                       if(_last_Distance == 0){
                           _last_Distance = _new_Distance;
                           _temp_2_Hidden_Golden = _hidden_Golden;
                       }else{
                           if(_last_Distance >= _new_Distance){
                               _last_Distance = _new_Distance;
                               _temp_2_Hidden_Golden = _hidden_Golden;
                           }
                       }
                    }
                }
         }
         
         if(_temp_2_Hidden_Golden.x != 0 && _temp_2_Hidden_Golden.y != 0){
             _game.getGrid()[_temp_2_Hidden_Golden.x][_temp_2_Hidden_Golden.y].setHidden_Golden_Visible(true);
             JLabel _gold_Label = new JLabel();
             _gold_Label.setText(Integer.toString(_game.getGrid()[_temp_2_Hidden_Golden.x][_temp_2_Hidden_Golden.y].getHidden_Golden_Amount()));
             _game_UI._grid[_temp_2_Hidden_Golden.x][_temp_2_Hidden_Golden.y].add(_gold_Label);
         }
       
     }
    
     
     
     
   
     public void _set_Target(Game _game, GameUI _game_UI){
        Point _temp_Gold_Point = new Point();
        ArrayList<Point> _near_Gold_List = new ArrayList<>();
        _end = new Point();
        
        int _last_Distance = 0;
        int _new_Distance = 0;
        int _count_Gain_Gold = 0;
        float _gain = 0.f;
        float _temp_Gain = 0.f;
        float _count_Gain = 0.f;
        int _temp_Count = 0;
        while(_temp_Count != 2){
            _make_Hidden_Gold_Visible(_game, _game_UI);
            _temp_Count = _temp_Count + 1;
        }
        
        if(_have_Target && (_gold_Amount - _move_Cost) >= 0){
            
            for (int _rows = 0; _rows < _game.getLines(); _rows++){
                for(int _cols = 0; _cols < _game.getCols(); _cols++ ){
                    if(_game.getGrid()[_rows][_cols].isGold_Bool() || (_game.getGrid()[_rows][_cols].isHidden_Golden_Visible() && _game.getGrid()[_rows][_cols].isHidden_Golden_Bool())){
                        _temp_Gold_Point = _game.getGrid()[_rows][_cols].getP();
                        _count_Gain_Gold = _game.getGrid()[_rows][_cols].getGold_Amount();
                        _new_Distance = _distance(getStart(), _temp_Gold_Point);
                        
                        if(_new_Distance <= _steps_Number)
                            _count_Gain = _move_Cost;
                        else
                            _count_Gain = (_new_Distance / (float) _steps_Number) * _move_Cost;
                        
                        _gain = _count_Gain_Gold - _count_Gain;
                        
                        if(_temp_Gain == 0.f){
                            _temp_Gain = _gain;
                            //_end.x = _temp_Gold_Point.x;
                            //_end.y = _temp_Gold_Point.y;
                        }else{
                            if(_temp_Gain < _gain){
                                _temp_Gain = _gain;
                                _end.x = _temp_Gold_Point.x;
                                _end.y = _temp_Gold_Point.y;
                            }
                        }
                    }
                }
            }

            if(_end.x == 0 && _end.y == 0){
                _end = _start;
                _have_Gold_Bool = true;
            }
            if(!_have_Gold_Bool){
                _gold_Amount_Spent = _gold_Amount_Spent + _target_Cost;
                _gold_Amount = _gold_Amount - _target_Cost;
            }
        }else{
            if (_gold_Amount - _target_Cost < 0){
                _end = _start;
                System.out.println("Player C out of  Gold");
                _have_Gold_Bool = true;
            }
        }
    }
     
     
      public void _find_Ways(Point _start, Point _end, Game _game){
        Random rn = new Random();
        _ways = new ArrayList<>();
        _current_Point = _start;
        _last_Point = _end;
        _ways.add(_current_Point);
        int _temp_Steps_Number = _steps_Number;
        _close_Neighbors = new ArrayList<>();
        
        if(_distance(_current_Point,_last_Point) <= _steps_Number){
            while(!_current_Point.equals(_end)){
                _close_Neighbors = _find_Neighbours_3(_current_Point, _steps_Number, _game);
                for(Point _temp_Points : _close_Neighbors){              
                    if(rn.nextBoolean()){
                       if(!_ways.contains(_temp_Points)){
                            _ways.add(_temp_Points);
                            _current_Point = _temp_Points;
                            _temp_Steps_Number = _temp_Steps_Number - 1; 
                            break;
                        } 
                    }
   
                }
            }
            _have_Target = true;
        } else{
            _temp_Steps_Number = _steps_Number;
            while(_temp_Steps_Number != 0){
                _close_Neighbors = _find_Neighbours(_current_Point,_steps_Number,_game);
                for(Point _temp_Points : _close_Neighbors){
                    if(!_ways.contains(_temp_Points)){
                        _ways.add(_temp_Points);
                        _current_Point = _temp_Points;
                        _temp_Steps_Number = _temp_Steps_Number - 1;
                    }
                }
            }
            _have_Target = true;
        }
     }
     
    
    public void _find_Golden_Ways(Point _start, Point _end, Game _game){
        Random _rn = new Random();
        int _move_Number = _steps_Number;
        
        _ways = new ArrayList<>();
        _close_Neighbors = new ArrayList<>();
        _current_Point = _start;
        _last_Point = _end ;
        _ways.add(_current_Point);
        
        if (_distance(_current_Point, _last_Point ) <= _steps_Number){
            while(!_current_Point.equals(_end)){
                _close_Neighbors = _find_Neighbours_3(_current_Point, _move_Number, _game);
                for(Point _temp_Point : _close_Neighbors){
                    if(_rn.nextBoolean()){
                        if(!_ways.contains(_temp_Point)){
                            _ways.add(_temp_Point);
                            _current_Point = _temp_Point;
                            _move_Number = _move_Number - 1;
                            break;
                        }
                    }
                }
            }
            _have_Target = true;
        }else{
            _move_Number = _steps_Number;
            while(_move_Number != 0){
                _close_Neighbors = _find_Neighbours(_current_Point, _move_Number, _game);
                for(Point _temp_Point : _close_Neighbors ){
                    if(_rn.nextBoolean()){
                        if(!_ways.contains(_temp_Point)){
                            _ways.add(_temp_Point);
                            _current_Point = _temp_Point;
                            _move_Number = _move_Number - 1;
                            break;
                        }
                    }
                }
            }
            _have_Target = true;
        }
        
    }
     
    
    
    
    public ArrayList<Point> _find_Neighbours (Point _current_Point,int _move_Number, Game _game){
    Point _up = null, _down = null, _left = null, _right =null;
    _far_Neighbors = new ArrayList<>();
    if(_current_Point.x -1 >= 0)
        _up = new Point(_current_Point.x -1 , _current_Point.y);
    if (_current_Point.x + 1 < _game.getLines())
        _down = new Point(_current_Point.x + 1, _current_Point.y);
    if (_current_Point.y -1 >= 0)
        _left = new Point(_current_Point.x, _current_Point.y -1 );
    if( _current_Point.y + 1 < _game.getCols())
        _right = new Point(_current_Point.x, _current_Point.y + 1);

    if (_up!=null){
             if(_distance(_up, getEnd()) < _distance(_current_Point, getEnd()) ){
                  _far_Neighbors.add(_up);
        }
     }
     if (_down !=null){
            if(_distance(_down, getEnd()) < _distance(_current_Point, getEnd()) ){
                 _far_Neighbors.add(_down);
            }
     }
     if (_left !=null){
          if(_distance(_left, getEnd()) < _distance(_current_Point, getEnd()) ){
                _far_Neighbors.add(_left);
          }
     }

     if (_right != null){
             if(_distance(_right, getEnd()) < _distance(_current_Point, getEnd()) ){
                _far_Neighbors.add(_right);
             }
     }
     return _far_Neighbors;
    } 
     
    public ArrayList<Point> _find_Neighbours_3 (Point _current_Point,int _move_Number, Game _game){
       Point _up = null, _down = null, _left = null, _right =null;
        _far_Neighbors = new ArrayList<>();
       if(_current_Point.x -1 >= 0)
           _up = new Point(_current_Point.x -1 , _current_Point.y);
       if (_current_Point.x + 1 < _game.getLines())
           _down = new Point(_current_Point.x + 1, _current_Point.y);
       if (_current_Point.y -1 >= 0)
           _left = new Point(_current_Point.x, _current_Point.y -1 );
       if( _current_Point.y + 1 < _game.getCols())
           _right = new Point(_current_Point.x, _current_Point.y + 1);
       
       if(_up != null){
           if(_distance(_up, getEnd()) < _move_Number)
               _far_Neighbors.add(_up);     
       }
       if(_down !=null){
           if(_distance(_down, getEnd()) < _move_Number)
               _far_Neighbors.add(_down);
       }
       
       if(_left != null){
           if (_distance(_left, getEnd()) < _move_Number)
               _far_Neighbors.add(_left);
       }
       
       if( _right != null){
           if ( _distance(_left, getEnd()) < _move_Number )
               _far_Neighbors.add(_right);
       }
       
       if(getFar_Neighbors().size() >= 2){
           if( _far_Neighbors.contains(_right)){
               if(_distance(_current_Point, getEnd()) < _distance(_right, getEnd()))
                   _far_Neighbors.remove(_right);
           }
           if( _far_Neighbors.contains(_left)){
               if(_distance(_current_Point, getEnd()) < _distance(_left, getEnd()))
                   _far_Neighbors.remove(_left);
           }
           if( _far_Neighbors.contains(_up)){
               if(_distance(_current_Point, getEnd()) < _distance(_up, getEnd()))
                   _far_Neighbors.remove(_up);
           }
           if( _far_Neighbors.contains(_down)){
               if(_distance(_current_Point, getEnd()) < _distance(_down, getEnd()))
                   _far_Neighbors.remove(_down);
           }
           
       }
       return _far_Neighbors;
    }
    /**
     * @return the _move_Cost
     */
    public int getMove_Cost() {
        return _move_Cost;
    }

    /**
     * @param _move_Cost the _move_Cost to set
     */
    public void setMove_Cost(int _move_Cost) {
        this._move_Cost = _move_Cost;
    }

    /**
     * @return the _target_Cost
     */
    public int getTarget_Cost() {
        return _target_Cost;
    }

    /**
     * @param _target_Cost the _target_Cost to set
     */
    public void setTarget_Cost(int _target_Cost) {
        this._target_Cost = _target_Cost;
    }

    /**
     * @return the _gold_Amount
     */
    public int getGold_Amount() {
        return _gold_Amount;
    }

    /**
     * @param _gold_Amount the _gold_Amount to set
     */
    public void setGold_Amount(int _gold_Amount) {
        this._gold_Amount = _gold_Amount;
    }

    /**
     * @return the _gold_Amount_Spent
     */
    public int getGold_Amount_Spent() {
        return _gold_Amount_Spent;
    }

    /**
     * @param _gold_Amount_Spent the _gold_Amount_Spent to set
     */
    public void setGold_Amount_Spent(int _gold_Amount_Spent) {
        this._gold_Amount_Spent = _gold_Amount_Spent;
    }

    /**
     * @return the _total_Number_Steps
     */
    public int getTotal_Number_Steps() {
        return _total_Number_Steps;
    }

    /**
     * @param _total_Number_Steps the _total_Number_Steps to set
     */
    public void setTotal_Number_Steps(int _total_Number_Steps) {
        this._total_Number_Steps = _total_Number_Steps;
    }

    /**
     * @return the _amount_Gold_Collected
     */
    public int getAmount_Gold_Collected() {
        return _amount_Gold_Collected;
    }

    /**
     * @param _amount_Gold_Collected the _amount_Gold_Collected to set
     */
    public void setAmount_Gold_Collected(int _amount_Gold_Collected) {
        this._amount_Gold_Collected = _amount_Gold_Collected;
    }

    /**
     * @return the _steps_Number
     */
    public int getSteps_Number() {
        return _steps_Number;
    }

    /**
     * @param _steps_Number the _steps_Number to set
     */
    public void setSteps_Number(int _steps_Number) {
        this._steps_Number = _steps_Number;
    }

    /**
     * @return the _have_Gold_Bool
     */
    public boolean isHave_Gold_Bool() {
        return _have_Gold_Bool;
    }

    /**
     * @param _have_Gold_Bool the _have_Gold_Bool to set
     */
    public void setHave_Gold_Bool(boolean _have_Gold_Bool) {
        this._have_Gold_Bool = _have_Gold_Bool;
    }

    /**
     * @return the _have_Target
     */
    public boolean isHave_Target() {
        return _have_Target;
    }

    /**
     * @param _have_Target the _have_Target to set
     */
    public void setHave_Target(boolean _have_Target) {
        this._have_Target = _have_Target;
    }

    /**
     * @return the _start
     */
    public Point getStart() {
        return _start;
    }

    /**
     * @param _start the _start to set
     */
    public void setStart(Point _start) {
        this._start = _start;
    }

    /**
     * @return the _end
     */
    public Point getEnd() {
        return _end;
    }

    /**
     * @param _end the _end to set
     */
    public void setEnd(Point _end) {
        this._end = _end;
    }

    /**
     * @return the _player_Passed_Rota_Array_List
     */
    public ArrayList<Point> getPlayer_Passed_Rota_Array_List() {
        return _player_Passed_Rota_Array_List;
    }

    /**
     * @param _player_Passed_Rota_Array_List the _player_Passed_Rota_Array_List to set
     */
    public void setPlayer_Passed_Rota_Array_List(ArrayList<Point> _player_Passed_Rota_Array_List) {
        this._player_Passed_Rota_Array_List = _player_Passed_Rota_Array_List;
    }

    /**
     * @return the _close_Neighbors
     */
    public ArrayList<Point> getClose_Neighbors() {
        return _close_Neighbors;
    }

    /**
     * @param _close_Neighbors the _close_Neighbors to set
     */
    public void setClose_Neighbors(ArrayList<Point> _close_Neighbors) {
        this._close_Neighbors = _close_Neighbors;
    }

    /**
     * @return the _far_Neighbors
     */
    public ArrayList<Point> getFar_Neighbors() {
        return _far_Neighbors;
    }

    /**
     * @param _far_Neighbors the _far_Neighbors to set
     */
    public void setFar_Neighbors(ArrayList<Point> _far_Neighbors) {
        this._far_Neighbors = _far_Neighbors;
    }

    /**
     * @return the _current_Point
     */
    public Point getCurrent_Point() {
        return _current_Point;
    }

    /**
     * @param _current_Point the _current_Point to set
     */
    public void setCurrent_Point(Point _current_Point) {
        this._current_Point = _current_Point;
    }

    /**
     * @return the _temp_Point
     */
    public Point getTemp_Point() {
        return _temp_Point;
    }

    /**
     * @param _temp_Point the _temp_Point to set
     */
    public void setTemp_Point(Point _temp_Point) {
        this._temp_Point = _temp_Point;
    }

    /**
     * @return the _last_Point
     */
    public Point getLast_Point() {
        return _last_Point;
    }

    /**
     * @param _last_Point the _last_Point to set
     */
    public void setLast_Point(Point _last_Point) {
        this._last_Point = _last_Point;
    }
}

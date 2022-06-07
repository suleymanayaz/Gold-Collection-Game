/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Players;
import java.awt.Point;
import java.util.ArrayList;

import struct.*;

/**
 *
 * @author ayaz
 */
public class Player {
    /**
     * 
     */
    private int _move_Cost;
    private int _target_Cost;
    private int _gold_Amount;
    private int _total_Number_Steps;
    private int _amount_Gold_Collected;
    private int _steps_Number;
    private Point _start,_end;
    private boolean _have_Gold_Bool = false;
    private boolean _have_Target;
    private ArrayList<Point> _player_Passed_Rota_Array_List;
    private Game _game;
    private ArrayList<Node> _ways;
    
    public int _distance(Point a,Point b){
        return  Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
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
     * @param _setps_Number the _steps_Number to set
     */
    public void setSteps_Number(int _steps_Number) {
        this._steps_Number = _steps_Number;
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
     * @return the _game
     */
    public Game getGame() {
        return _game;
    }

    /**
     * @param _game the _game to set
     */
    public void setGame(Game _game) {
        this._game = _game;
    }

    /**
     * @return the _ways
     */
    public ArrayList<Node> getWays() {
        return _ways;
    }

    /**
     * @param _ways the _ways to set
     */
    public void setWays(ArrayList<Node> _ways) {
        this._ways = _ways;
    }
}

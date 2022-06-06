/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import java.awt.*;
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
                }else if (_game.getGrid()[_rows][_cols].p.equals(_game.getStartC())){
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

   
}

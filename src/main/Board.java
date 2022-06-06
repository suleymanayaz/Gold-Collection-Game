/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import Players.*;
import UI.GameUI;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.NumberFormat;
import javax.swing.*;

import struct.Game;

/**
 *
 * @author ayaz
 */
public class Board extends JFrame implements ActionListener{
    /**
     * 
     */
    public int _board_Rows;
    public int _board_Cols;
    public int _golden_Ratio;
    public int _hidden_Golden_Ratio;
    public JPanel _game, _board_Settings, _board_Generate, _board_Result,_board_Results,_board_Players;
    public JFormattedTextField _board_Rows_Input, _board_Cols_Input;
    public JFormattedTextField _player_Steps_Number_Input, _player_A_Target_Cost,_player_A_Move_Cost,_player_A_Initial_Gold_Mount;
    public JFormattedTextField  _player_B_Target_Cost,_player_B_Move_Cost,_player_B_Initial_Gold_Mount;
    public JFormattedTextField  _player_C_Target_Cost,_player_C_Move_Cost,_player_C_Initial_Gold_Mount;
    public JFormattedTextField  _player_D_Target_Cost,_player_D_Move_Cost,_player_D_Initial_Gold_Mount;
    public JFormattedTextField _board_Golden_Ration_Input, _board_Hidden_Golden_Ration_Input;
    
    public JButton _board_Settings_Button, _board_Player_Settings_Button, _board_Game_Start_Button;
    public JLabel _board_Game_Result_Label, _board_Winner_Label,_board_Cols_Label,_board_Row_Label;
    public JLabel _board_Player_Name_Label, _board_Player_Target_Cost_Label, _board_Player_Move_Cost_Label, _board_Player_Initial_Golden_Mount_Label,_board_Player_Steps_Number_Label;
    public JLabel _board_Golden_Ration_Text, _board_Hidden_Golden_Ration_Text;
    
    public Point _start_Player_A, _start_Player_B, _start_Player_C, _start_Player_D;
    public Player _player_instance;
    public PlayerA _player_A;
    public PlayerB _player_B;
    public PlayerC _player_C;
    public PlayerD _player_D;
    
    public Game _game_Board;
    public GameUI _game_UI;
    public File _file;
    
    
    public Board (){
        _player_instance = new Player();
         _board_Rows = 20;
         _board_Cols = 20;
         _golden_Ratio = 20;
         _hidden_Golden_Ratio = 10;
         _game = new JPanel();
         
         this.setLayout(new FlowLayout(0,1,0));
         this.setTitle("Golden Collection Game");
         _game_Board = new Game(_board_Rows,_board_Cols,_golden_Ratio,_hidden_Golden_Ratio);
         _start_Player_A = new Point(0,0);
         _player_A = new PlayerA(_start_Player_A);
         _player_A._set_Target(_game_Board);
         _start_Player_B = new Point(0,_board_Cols-1);
         _player_B = new PlayerB(_start_Player_B);
         _player_B._set_Target(_game_Board);
         _start_Player_C = new Point(_board_Rows-1,_board_Cols-1);
         _player_C = new PlayerC(_start_Player_C);
         _player_C._set_Target(_game_Board,_game_UI);
         _start_Player_D = new Point(_board_Rows-1,0);
         _player_D = new PlayerD(_start_Player_D);
         
         _game_UI = new GameUI(_game_Board);
         _game.add(_game_UI);
         this.add(_game);
         
         _set_Board_Settings();
         this.add(_board_Settings);
         this.setMinimumSize(new Dimension(1366,768));
         this.setVisible(true);
         this.setDefaultCloseOperation(EXIT_ON_CLOSE);
         
         
    }
    
 
    
    public static void main(String[] args){
        new Board();
    }
    
    public void _set_Board_Settings(){
        // CREATE board on settings
        _board_Settings = new JPanel();
        _board_Settings.setLayout(new BoxLayout(_board_Settings,BoxLayout.Y_AXIS));
        _board_Settings.setBackground(Color.YELLOW);
        // CREATE board on settings
        
        _board_Generate = new JPanel();
        _board_Generate.setLayout(new FlowLayout());
        _board_Settings_Button = new JButton("SET");
        _board_Settings_Button.addActionListener(this);
        
        _board_Rows_Input  = new JFormattedTextField(NumberFormat.getNumberInstance());
        _board_Rows_Input.setValue(20L);
        _board_Rows_Input.setPreferredSize(_board_Settings_Button.getPreferredSize());
        _board_Row_Label = new JLabel("Row :");
        
        _board_Cols_Input = new JFormattedTextField(NumberFormat.getNumberInstance());
        _board_Cols_Input.setValue(20L);
        _board_Cols_Input.setPreferredSize(_board_Settings_Button.getPreferredSize());
        _board_Cols_Label = new JLabel("Column");
        
        JPanel dimensions= new JPanel();
        dimensions.setLayout(new FlowLayout());
        JPanel _gold_Amount_Panel = new JPanel();
        _gold_Amount_Panel.setLayout(new FlowLayout());
        JPanel _hidden_Gold_Amount_Panel = new JPanel();
        _hidden_Gold_Amount_Panel.setLayout(new FlowLayout());

        dimensions.add(_board_Row_Label);
        dimensions.add(_board_Rows_Input);
        dimensions.add(_board_Cols_Label);
        dimensions.add(_board_Cols_Input);
        
        _board_Golden_Ration_Text = new JLabel("Golden Ration Number :");
        _board_Golden_Ration_Input = new JFormattedTextField(NumberFormat.getNumberInstance());
        _board_Golden_Ration_Input.setValue(20L);
        _board_Golden_Ration_Input.setPreferredSize(_board_Settings_Button.getPreferredSize());
        _gold_Amount_Panel.add(_board_Golden_Ration_Text);
        _gold_Amount_Panel.add(_board_Golden_Ration_Input);
        
        
        _board_Hidden_Golden_Ration_Text = new JLabel("Hidden Golden Ration Number :");
        _board_Hidden_Golden_Ration_Input = new JFormattedTextField(NumberFormat.getNumberInstance());
        _board_Hidden_Golden_Ration_Input.setValue(10L);
        _board_Hidden_Golden_Ration_Input.setPreferredSize(_board_Settings_Button.getPreferredSize()); 
        _hidden_Gold_Amount_Panel.add(_board_Hidden_Golden_Ration_Text);
        _hidden_Gold_Amount_Panel.add(_board_Hidden_Golden_Ration_Input);
        
        
        JPanel _settings_Panel = new JPanel();
        _settings_Panel.setLayout(new BoxLayout(_settings_Panel, BoxLayout.Y_AXIS) );
        _settings_Panel.add(dimensions);
        _settings_Panel.add(_gold_Amount_Panel);
        _settings_Panel.add(_hidden_Gold_Amount_Panel);
        _settings_Panel.add(_board_Settings_Button);
        
        _board_Generate.add(_settings_Panel);
        _board_Generate.setBorder(BorderFactory.createTitledBorder("Settings"));
        
        _board_Players = new JPanel();
        _board_Players.setLayout(new FlowLayout());
        _board_Player_Settings_Button = new JButton("SET");
        _board_Player_Settings_Button.addActionListener(this);
        
        _player_A_Target_Cost = new JFormattedTextField(NumberFormat.getNumberInstance());
        _player_A_Target_Cost.setValue(5L);
        _player_A_Target_Cost.setPreferredSize(_board_Player_Settings_Button.getPreferredSize());
        _board_Player_Name_Label = new JLabel("A : ");
        _board_Player_Target_Cost_Label = new JLabel("Target Cost");
        _player_A_Move_Cost = new JFormattedTextField(NumberFormat.getNumberInstance());
        _player_A_Move_Cost.setValue(5L);
        _player_A_Move_Cost.setPreferredSize(_board_Player_Settings_Button.getPreferredSize());
        _board_Player_Move_Cost_Label = new JLabel("Move Cost");
        _player_A_Initial_Gold_Mount = new JFormattedTextField(NumberFormat.getNumberInstance());
        _player_A_Initial_Gold_Mount.setValue(200L);
        _player_A_Initial_Gold_Mount.setPreferredSize(_board_Player_Settings_Button.getPreferredSize());
        _board_Player_Initial_Golden_Mount_Label = new JLabel("Gold Amount");
        JPanel _player_A_Panel = new JPanel();
        _player_A_Panel.setLayout(new FlowLayout());
        _player_A_Panel.add(_board_Player_Name_Label);
        _player_A_Panel.add(_board_Player_Target_Cost_Label);
        _player_A_Panel.add(_player_A_Target_Cost);
        _player_A_Panel.add(_board_Player_Move_Cost_Label);
        _player_A_Panel.add(_player_A_Move_Cost);
        _player_A_Panel.add(_board_Player_Initial_Golden_Mount_Label);
        _player_A_Panel.add(_player_A_Initial_Gold_Mount);
        
        ///////////////////////////////
        _player_B_Target_Cost = new JFormattedTextField(NumberFormat.getNumberInstance());
        _player_B_Target_Cost.setValue(10L);
        _player_B_Target_Cost.setPreferredSize(_board_Player_Settings_Button.getPreferredSize());
        _board_Player_Name_Label = new JLabel("B : ");
        _board_Player_Target_Cost_Label = new JLabel("Target Cost");
        _player_B_Move_Cost = new JFormattedTextField(NumberFormat.getNumberInstance());
        _player_B_Move_Cost.setValue(5L);
        _player_B_Move_Cost.setPreferredSize(_board_Player_Settings_Button.getPreferredSize());
        _board_Player_Move_Cost_Label = new JLabel("Move Cost");
        _player_B_Initial_Gold_Mount = new JFormattedTextField(NumberFormat.getNumberInstance());
        _player_B_Initial_Gold_Mount.setValue(200L);
        _player_B_Initial_Gold_Mount.setPreferredSize(_board_Player_Settings_Button.getPreferredSize());
        _board_Player_Initial_Golden_Mount_Label = new JLabel("Gold Amount");
        JPanel _player_B_Panel = new JPanel();
        _player_B_Panel.setLayout(new FlowLayout());
        _player_B_Panel.add(_board_Player_Name_Label);
        _player_B_Panel.add(_board_Player_Target_Cost_Label);
        _player_B_Panel.add(_player_B_Target_Cost);
        _player_B_Panel.add(_board_Player_Move_Cost_Label);
        _player_B_Panel.add(_player_B_Move_Cost);
        _player_B_Panel.add(_board_Player_Initial_Golden_Mount_Label);
        _player_B_Panel.add(_player_B_Initial_Gold_Mount);
        
        
        _player_C_Target_Cost = new JFormattedTextField(NumberFormat.getNumberInstance());
        _player_C_Target_Cost.setValue(15L);
        _player_C_Target_Cost.setPreferredSize(_board_Player_Settings_Button.getPreferredSize());
        _board_Player_Name_Label = new JLabel("C : ");
        _board_Player_Target_Cost_Label = new JLabel("Target Cost");
        _player_C_Move_Cost = new JFormattedTextField(NumberFormat.getNumberInstance());
        _player_C_Move_Cost.setValue(5L);
        _player_C_Move_Cost.setPreferredSize(_board_Player_Settings_Button.getPreferredSize());
        _board_Player_Move_Cost_Label = new JLabel("Move Cost");
        _player_C_Initial_Gold_Mount = new JFormattedTextField(NumberFormat.getNumberInstance());
        _player_C_Initial_Gold_Mount.setValue(200L);
        _player_C_Initial_Gold_Mount.setPreferredSize(_board_Player_Settings_Button.getPreferredSize());
        _board_Player_Initial_Golden_Mount_Label = new JLabel("Gold Amount");
        JPanel _player_C_Panel = new JPanel();
        _player_C_Panel.setLayout(new FlowLayout());
        _player_C_Panel.add(_board_Player_Name_Label);
        _player_C_Panel.add(_board_Player_Target_Cost_Label);
        _player_C_Panel.add(_player_C_Target_Cost);
        _player_C_Panel.add(_board_Player_Move_Cost_Label);
        _player_C_Panel.add(_player_C_Move_Cost);
        _player_C_Panel.add(_board_Player_Initial_Golden_Mount_Label);
        _player_C_Panel.add(_player_C_Initial_Gold_Mount);
        
        
        _player_D_Target_Cost = new JFormattedTextField(NumberFormat.getNumberInstance());
        _player_D_Target_Cost.setValue(20L);
        _player_D_Target_Cost.setPreferredSize(_board_Player_Settings_Button.getPreferredSize());
        _board_Player_Name_Label = new JLabel("D : ");
        _board_Player_Target_Cost_Label = new JLabel("Target Cost");
        _player_D_Move_Cost = new JFormattedTextField(NumberFormat.getNumberInstance());
        _player_D_Move_Cost.setValue(5L);
        _player_D_Move_Cost.setPreferredSize(_board_Player_Settings_Button.getPreferredSize());
        _board_Player_Move_Cost_Label = new JLabel("Move Cost");
        _player_D_Initial_Gold_Mount = new JFormattedTextField(NumberFormat.getNumberInstance());
        _player_D_Initial_Gold_Mount.setValue(200L);
        _player_D_Initial_Gold_Mount.setPreferredSize(_board_Player_Settings_Button.getPreferredSize());
        _board_Player_Initial_Golden_Mount_Label = new JLabel("Gold Amount");
        JPanel _player_D_Panel = new JPanel();
        _player_D_Panel.setLayout(new FlowLayout());
        _player_D_Panel.add(_board_Player_Name_Label);
        _player_D_Panel.add(_board_Player_Target_Cost_Label);
        _player_D_Panel.add(_player_D_Target_Cost);
        _player_D_Panel.add(_board_Player_Move_Cost_Label);
        _player_D_Panel.add(_player_D_Move_Cost);
        _player_D_Panel.add(_board_Player_Initial_Golden_Mount_Label);
        _player_D_Panel.add(_player_D_Initial_Gold_Mount);
        
        _player_Steps_Number_Input = new JFormattedTextField(NumberFormat.getNumberInstance());
        _player_Steps_Number_Input.setValue(3L);
        _player_Steps_Number_Input.setPreferredSize(_board_Player_Settings_Button.getPreferredSize());
        _board_Player_Steps_Number_Label = new JLabel("Steps Number");
        JPanel _player_Move_Number_Panel = new JPanel();
        _player_Move_Number_Panel.setLayout(new FlowLayout());
        _player_Move_Number_Panel.add(_board_Player_Steps_Number_Label);
        _player_Move_Number_Panel.add(_player_Steps_Number_Input);
        
        JPanel _player_Settings_Panel = new JPanel();
        _player_Settings_Panel.setLayout(new BoxLayout(_player_Settings_Panel, BoxLayout.Y_AXIS));
        _player_Settings_Panel.add(_player_A_Panel);
        _player_Settings_Panel.add(_player_B_Panel);
        _player_Settings_Panel.add(_player_C_Panel);
        _player_Settings_Panel.add(_player_D_Panel);
        _player_Settings_Panel.add(_player_Move_Number_Panel);
        _player_Settings_Panel.add(_board_Player_Settings_Button);
        
        _board_Players.add(_player_Settings_Panel);
        _board_Players.setBorder(BorderFactory.createTitledBorder("Player Settings"));
        
        _board_Result  = new JPanel();
        _board_Result.setLayout(new FlowLayout());
        _board_Result.setBorder(BorderFactory.createTitledBorder("Game "));
        
     
        _board_Game_Start_Button = new JButton("Start");
        _board_Game_Start_Button.addActionListener(this);
        
        _board_Result.add(_board_Game_Start_Button);
        
        _board_Results = new JPanel();
        _board_Results.setBorder(BorderFactory.createTitledBorder("Results"));
        
        _board_Game_Result_Label = new JLabel();
        _board_Winner_Label = new JLabel();
        
        _board_Results.add(_board_Game_Result_Label);
        _board_Results.add(_board_Winner_Label);
        
        _board_Settings.add(_board_Generate);
        _board_Settings.add(_board_Players);
        _board_Settings.add(_board_Result);
        _board_Settings.add(_board_Results);
        
    
    }

    @Override
    public void actionPerformed(ActionEvent e) {
         JButton source = (JButton) e.getSource();
        if(source == _board_Settings_Button){
            Long _board_Input_Rows_Temp  = (Long) _board_Rows_Input.getValue();
            Long _board_Input_Cols_Temp  = (Long) _board_Cols_Input.getValue();
            Long _board_Input_Golden_Temp = (Long) _board_Golden_Ration_Input.getValue();
            Long _board_Input_Hidden_Golden_Temp  = (Long) _board_Hidden_Golden_Ration_Input.getValue();
            
            _board_Rows = _board_Input_Rows_Temp.intValue();
            _board_Cols = _board_Input_Cols_Temp.intValue();
            _golden_Ratio = _board_Input_Golden_Temp.intValue();
            _hidden_Golden_Ratio =_board_Input_Hidden_Golden_Temp.intValue();
             _game_Board = new Game(_board_Rows,_board_Cols,_golden_Ratio,_hidden_Golden_Ratio);
            
            _player_A_Target_Cost.setValue(5L);
            _player_A_Move_Cost.setValue(5L);
            _player_A_Initial_Gold_Mount.setValue(200L);
            
            _player_B_Target_Cost.setValue(5L);
            _player_B_Move_Cost.setValue(5L);
            _player_B_Initial_Gold_Mount.setValue(200L);
            
            _player_C_Target_Cost.setValue(5L);
            _player_C_Move_Cost.setValue(5L);
            _player_C_Initial_Gold_Mount.setValue(200L);
            
            _player_D_Target_Cost.setValue(5L);
            _player_D_Move_Cost.setValue(5L);
            _player_D_Initial_Gold_Mount.setValue(200L);
            
            _player_Steps_Number_Input.setValue(3L);
            
            _start_Player_A = new Point(0,0);
            _start_Player_B = new Point(0,_board_Cols-1);

            _player_A = new PlayerA(_start_Player_A);
            _player_A._set_Target(_game_Board);         
            _player_B = new PlayerB(_start_Player_B);
            _player_B._set_Target(_game_Board);
            
            _start_Player_C = new Point(_board_Rows-1,_board_Cols-1);
            _player_C = new PlayerC(_start_Player_C);
            _player_C._set_Target(_game_Board,_game_UI);
            _start_Player_D = new Point(_board_Rows-1,0);
            _player_D = new PlayerD(_start_Player_D);
            _game_UI = new GameUI(_game_Board);
            _player_C._set_Target(_game_Board, _game_UI);
            _game.removeAll();
            _game.repaint();
            _game.revalidate();
            _board_Game_Result_Label.setText("");
            _board_Winner_Label.setText("");
            _game.add(_game_UI);
            
        }
   }
    
    
}

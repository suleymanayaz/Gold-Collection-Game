/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author ayaz
 */
public class NodeUI extends JPanel{
    /**
     * 
     */
    public Point _p;
    
    public NodeUI(Point _p){
        this._p = _p;
    }
    
    @Override
    public Dimension getPreferredSize(){
      return new Dimension(30,30);
  }
}

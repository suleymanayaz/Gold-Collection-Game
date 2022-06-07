
package struct;

import java.awt.Point;



public class Node {
    /**
     * 
     */
    public Point p;

    private int _gold_Amount;
    private int _hidden_Golden_Amount;
    private boolean _gold_Bool,_hidden_Golden_Bool,_golden_Visible,_hidden_Golden_Visible;


    public Node(int x, int y) {
        this.p = new Point(x,y);
    }

    /**
     * @return the p
     */
    public Point getP() {
        return p;
    }

    /**
     * @param p the p to set
     */
    public void setP(Point p) {
        this.p = p;
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
     * @return the _hidden_Golden_Amount
     */
    public int getHidden_Golden_Amount() {
        return _hidden_Golden_Amount;
    }

    /**
     * @param _hidden_Golden_Amount the _hidden_Golden_Amount to set
     */
    public void setHidden_Golden_Amount(int _hidden_Golden_Amount) {
        this._hidden_Golden_Amount = _hidden_Golden_Amount;
    }

    /**
     * @return the _gold_Bool
     */
    public boolean isGold_Bool() {
        return _gold_Bool;
    }

    /**
     * @param _gold_Bool the _gold_Bool to set
     */
    public void setGold_Bool(boolean _gold_Bool) {
        this._gold_Bool = _gold_Bool;
    }

    /**
     * @return the _hidden_Golden_Bool
     */
    public boolean isHidden_Golden_Bool() {
        return _hidden_Golden_Bool;
    }

    /**
     * @param _hidden_Golden_Bool the _hidden_Golden_Bool to set
     */
    public void setHidden_Golden_Bool(boolean _hidden_Golden_Bool) {
        this._hidden_Golden_Bool = _hidden_Golden_Bool;
    }

    /**
     * @return the _hidden_Golden_Visible
     */
    public boolean isHidden_Golden_Visible() {
        return _hidden_Golden_Visible;
    }

    /**
     * @param _hidden_Golden_Visible the _hidden_Golden_Visible to set
     */
    public void setHidden_Golden_Visible(boolean _hidden_Golden_Visible) {
        this._hidden_Golden_Visible = _hidden_Golden_Visible;
    }

    /**
     * @return the _golden_Visible
     */
    public boolean isGolden_Visible() {
        return _golden_Visible;
    }

    /**
     * @param _golden_Visible the _golden_Visible to set
     */
    public void setGolden_Visible(boolean _golden_Visible) {
        this._golden_Visible = _golden_Visible;
    }

    
    
}
  


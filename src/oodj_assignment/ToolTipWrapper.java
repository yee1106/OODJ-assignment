/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oodj_assignment;

/**
 *
 * @author yeech
 */
public class ToolTipWrapper implements ToolTipProvider {
    final String value;
    final String toolTip;

    public ToolTipWrapper(String value, String toolTip) {
        this.value = value;
        this.toolTip = toolTip;
    }

    @Override
    public String getToolTip() {
        return toolTip; 
    }

    @Override
    public String toString() {
        return value.toString();
    }

}



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oodj_assignment;

import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComponent;
import javax.swing.JList;

/**
 *
 * @author yeech
 */
public class ToolTipRenderer extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(JList list, Object value,
            int index, boolean isSelected, boolean cellHasFocus) {
         JComponent component = (JComponent) super.getListCellRendererComponent(list, value, index, isSelected,
                cellHasFocus);
         String tip = null;
         if (value instanceof ToolTipProvider) {
             ToolTipProvider ttp = (ToolTipProvider) value;
             tip = ttp.getToolTip();
         }
         list.setToolTipText(tip);
         return component;
    }
}



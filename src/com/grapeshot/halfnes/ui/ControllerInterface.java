/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grapeshot.halfnes.ui;

/**
 *
 * @author Andrew
 */
public interface ControllerInterface {

    public void strobe();

    public void output(final boolean state);
        
    public int getLatchByte();

    public int getbyte();
}

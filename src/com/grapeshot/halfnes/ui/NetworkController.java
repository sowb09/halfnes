/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.grapeshot.halfnes.ui;

/**
 *
 * @author Étienne B. Auger
 */
public class NetworkController implements ControllerInterface {

    private int outbyte = 0;
    private int latchbyte = 0;
    private int netbyte = 0;

    public void setNetByte(int netbyte) {
        this.netbyte = netbyte;
    }

    public void output(boolean state) {
        this.latchbyte = this.netbyte;
    }

    @Override
    public void strobe() {
        this.outbyte = (this.latchbyte & 0x1);
        this.latchbyte = (this.latchbyte >> 1 | 0x100);
    }

    @Override
    public int getbyte() {
        return this.outbyte;
    }

    @Override
    public int getLatchByte() {return this.latchbyte;}; //bs
}

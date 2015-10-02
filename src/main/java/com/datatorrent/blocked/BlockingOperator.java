/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.datatorrent.blocked;

import com.datatorrent.api.DefaultInputPort;
import com.datatorrent.common.util.BaseOperator;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BlockingOperator extends BaseOperator
{
  private transient Object lock = new Object();

  public final transient DefaultInputPort<Double> input = new DefaultInputPort<Double>() {
    @Override
    public void process(Double t)
    {
      try {
        lock.wait();
      } catch (InterruptedException ex) {
        Logger.getLogger(BlockingOperator.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  };
}

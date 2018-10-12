package com.daka.thread;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Simple Runnable class for running at specific time extends {@link Runnable} 
 * @version v0.1
 * @author DAKA
 */
public class ThreadTime implements Runnable {
	
	private ArrayList<ThreadTimeListener> listeners = new ArrayList<ThreadTimeListener>();
	private boolean running = true;
	private long endTime =0;
	private boolean indeterminate =false;
	private long currentTime =0;
	
	/**
	 * Instance of ThreadTime 
	 * @param Interval Time
	 * @param TimeUnit, specify HOURS, MINUTES OR SECONDS
	 * @return Intance
	 */
	public ThreadTime(int intervalTime, TimeUnit timeUnit){
		initialize(intervalTime, timeUnit);
	}
	
	/**
	 * Instance of ThreadTime 
	 * @param Interval Time
	 * @param TimeUnit, specify HOURS, MINUTES OR SECONDS
	 * @param Undeterminate
	 * @return Intance
	 */
	
	public ThreadTime(int intervalTime, TimeUnit timeUnit, boolean undeterminate){
		initialize(intervalTime, timeUnit);
	}
	
	/**
	 * Set if this is indeterminated
	 * @return void
	 */
	public void setIndeterminate(boolean indeterminate){
		this.indeterminate = indeterminate;
	}
	
	
	/**
	 * Check this chrono it's running
	 * @return Boolean
	 */
	public boolean isRunning() {
		return running;
	}
	
	
	
	/**
	 * Check if this is undeterminate
	 * @return Boolean
	 */
	public boolean isIndeterminate() {
		return indeterminate;
	}
	
	/**
	 * Prepare all parameters before initialize ThreadTime
	 * @param intervalTime
	 * @param timeUnit
	 */
	private void initialize(int intervalTime, TimeUnit timeUnit) {
		
		switch (timeUnit) {
			case HOURS:
				endTime = TimeUnit.HOURS.toMillis(intervalTime);
			case MINUTES:
				endTime = TimeUnit.MINUTES.toMillis(intervalTime);
				break;
			case SECONDS:
				endTime = TimeUnit.SECONDS.toMillis(intervalTime);
				break;
			default:
				break;
		}
		
		
	}
	
	/**
	 * Add ThreadListener
	 * @param ThreadListener
	 */
	
	public void addListener(ThreadTimeListener listener) {
		listeners.add(listener);
	}

	@Override
	public void run() {
		
		while(running){

			
			try {
				
				if(currentTime==0) {
					
					for(ThreadTimeListener l: listeners) {
						l.onStart(getChronometer(currentTime));
					}
					
					
				}else if(currentTime > 0 && currentTime <endTime) {
					
					for(ThreadTimeListener l: listeners) {
						l.onRunning(getChronometer(currentTime));
					}
					
				}else{
					
					for(ThreadTimeListener l: listeners) {
						l.onfinish(getChronometer(currentTime));
						if(!indeterminate) {
							running = false;
						}else {
							currentTime=0;
						}
					}
					
				}
				
				currentTime+=1000;
				
				Thread.sleep(1000);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	/**
	 * Get representation of time 
	 * @param millisecond
	 * @return String Formatted as (00:00:00)
	 */
	
	private String getChronometer(long millisecond) {
		return String.format("%02d:%02d:%02d", 
				TimeUnit.MILLISECONDS.toHours(millisecond),
				TimeUnit.MILLISECONDS.toMinutes(millisecond) % TimeUnit.HOURS.toMinutes(1),
				TimeUnit.MILLISECONDS.toSeconds(millisecond) % TimeUnit.MINUTES.toSeconds(1));
	}

}

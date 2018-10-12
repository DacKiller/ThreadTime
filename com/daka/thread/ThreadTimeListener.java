package com.daka.thread;

/**
 * Interface for listen event's onStart, onRunning, onFinish
 * @author DAKA
 */
public interface ThreadTimeListener {
	/**
	 * ThreadTime is Start
	 * @param  {@link ThreadTime#String getChronometer(long millisecond)}
	 */
	public void onStart(String cronometer);
	/**
	 * ThreadTime is Running
	 * @param  {@link ThreadTime#String getChronometer(long millisecond)}
	 */
	public void onRunning(String cronometer);
	
	/**
	 * ThreadTime is finish
	 * @param  {@link ThreadTime#String getChronometer(long millisecond)}
	 */
	public void onfinish(String cronometer);
}

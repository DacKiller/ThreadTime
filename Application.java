import java.util.concurrent.TimeUnit;

import com.daka.thread.ThreadTime;
import com.daka.thread.ThreadTimeListener;

public class Application {

	public Application() {
		
		ThreadTime crono = new ThreadTime(1, TimeUnit.MINUTES);
		crono.setIndeterminate(true);
		crono.addListener(new ThreadTimeListener() {
			
			@Override
			public void onfinish(String cronometer) {
				// TODO Auto-generated method stub
				logConsole(cronometer);
			}
			
			@Override
			public void onStart(String cronometer) {
				logConsole(cronometer);
			}
			
			@Override
			public void onRunning(String cronometer) {
				logConsole(cronometer);
			}
		});
		
		
		Thread t = new Thread(crono);
		
		t.start();
		
	}
	
	
	private void logConsole(String message) {
		System.out.print(message);
	}
	
	public static void main(String[] args) {
		new Application();
	}

}

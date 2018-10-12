# ThreadTime
Java execute Thread on Specific time

#English:

Create new instance of #ThreadTime, giving the arguments for initialized the Thread

#Spanish

Crea la nueva instancia de #ThreadTime, dando los argumentos para iniciar el Hilo

Example:

		// Instance for 1 minute execution
		ThreadTime crono = new ThreadTime(1, TimeUnit.MINUTES);
		
    // Set undeterminate 
    crono.setIndeterminate(true);
    
    // Add listener for read events onStart, onRunning, onFinish
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
	
	// Console print output as 00:00:00
  
	private void logConsole(String message) {
		System.out.print(message);
	}
	
	public static void main(String[] args) {
		new Application();
	}

}

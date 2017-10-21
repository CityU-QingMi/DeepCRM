	public void execute(Executable executable) throws TimeoutException {
		final ExecutableAdapter adapter = new ExecutableAdapter( executable );
		final Thread separateThread = new Thread( adapter );
		separateThread.start();

		int runningTime = 0;
		do {
			if ( runningTime > timeOut ) {
				try {
					executable.timedOut();
				}
				catch (Exception ignore) {
				}
				throw new TimeoutException();
			}
			try {
				Thread.sleep( checkMilliSeconds );
				runningTime += checkMilliSeconds;
			}
			catch (InterruptedException ignore) {
			}
		} while ( !adapter.isDone() );

		adapter.reThrowAnyErrors();
	}

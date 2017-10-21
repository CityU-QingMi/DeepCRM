	private FutureTask<SlowInitializationService>[] execute()
			throws InterruptedException, ExecutionException {
		FutureTask<SlowInitializationService>[] results = new FutureTask[NUMBER_OF_THREADS];
		ExecutorService executor = Executors.newFixedThreadPool( NUMBER_OF_THREADS );
		for ( int i = 0; i < NUMBER_OF_THREADS; i++ ) {
			results[i] = new FutureTask<>( new ServiceCallable( registry ) );
			executor.execute( results[i] );
		}
		return results;
	}

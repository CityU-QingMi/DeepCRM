	protected void executeSync(Runnable callable) {
		try {
			executeAsync( callable ).get();
		}
		catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		catch (ExecutionException e) {
			throw new RuntimeException( e.getCause() );
		}
	}

	private static ListenableFutureTask<Boolean> getBooleanFuture() {
		ListenableFutureTask<Boolean> futureTask = new ListenableFutureTask<>(new Callable<Boolean>() {
			@Override
			public Boolean call() throws Exception {
				return null;
			}
		});
		futureTask.run();
		return futureTask;
	}

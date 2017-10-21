	private static ListenableFutureTask<Void> getVoidFuture() {
		ListenableFutureTask<Void> futureTask = new ListenableFutureTask<>(new Callable<Void>() {
			@Override
			public Void call() throws Exception {
				return null;
			}
		});
		futureTask.run();
		return futureTask;
	}

	@Override
	public boolean runInThread(Runnable runnable) {
		Assert.state(this.taskExecutor != null, "No TaskExecutor available");
		try {
			this.taskExecutor.execute(runnable);
			return true;
		}
		catch (RejectedExecutionException ex) {
			logger.error("Task has been rejected by TaskExecutor", ex);
			return false;
		}
	}

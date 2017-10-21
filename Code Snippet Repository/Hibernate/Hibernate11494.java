	@AfterClassOnce
	public void releaseResources() throws Exception {
		try {
			if (localEnvironment != null) {
				localEnvironment.release();
			}
		}
		finally {
			if (remoteEnvironment != null) {
				remoteEnvironment.release();
			}
		}
		TestResourceTracker.testFinished(getClass().getSimpleName());
	}

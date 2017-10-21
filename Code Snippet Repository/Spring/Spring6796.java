	protected void recoverAfterListenerSetupFailure() {
		this.recovering = true;
		try {
			refreshConnectionUntilSuccessful();
			refreshDestination();
		}
		finally {
			this.recovering = false;
			this.interrupted = false;
		}
	}

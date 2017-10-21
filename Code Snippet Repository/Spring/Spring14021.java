	@Override
	public void stop() throws Exception {
		try {
			if (this.contextHandler.isRunning()) {
				this.contextHandler.stop();
			}
		}
		finally {
			if (this.jettyServer.isRunning()) {
				this.jettyServer.setStopTimeout(5000);
				this.jettyServer.stop();
			}
		}
	}

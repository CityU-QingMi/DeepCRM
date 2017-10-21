	@Override
	protected void stopInternal() throws Exception {
		try {
			if (this.contextHandler.isRunning()) {
				this.contextHandler.stop();
			}
		}
		finally {
			try {
				if (this.jettyServer.isRunning()) {
					this.jettyServer.setStopTimeout(5000);
					this.jettyServer.stop();
					this.jettyServer.destroy();
				}
			}
			catch (Exception ex) {
				// ignore
			}
		}
	}

	@Override
	protected void resetInternal() {
		try {
			if (this.jettyServer.isRunning()) {
				this.jettyServer.setStopTimeout(5000);
				this.jettyServer.stop();
				this.jettyServer.destroy();
			}
		}
		catch (Exception ex) {
			throw new IllegalStateException(ex);
		}
		finally {
			this.jettyServer = null;
			this.contextHandler = null;
		}
	}

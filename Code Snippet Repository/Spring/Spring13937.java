	@Override
	public void stop() {
		try {
			if (this.httpClient.isRunning()) {
				this.httpClient.stop();
			}
		}
		catch (Exception ex) {
			throw new SockJsException("Failed to stop " + this, ex);
		}
	}

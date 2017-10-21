	@Override
	public void start() {
		try {
			if (!this.httpClient.isRunning()) {
				this.httpClient.start();
			}
		}
		catch (Exception ex) {
			throw new SockJsException("Failed to start " + this, ex);
		}
	}

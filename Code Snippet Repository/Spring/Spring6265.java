	private void closeConnection() {
		if (this.target != null) {
			try {
				this.target.close();
			}
			catch (Throwable ex) {
				logger.warn("Could not close shared JDBC Connection", ex);
			}
		}
	}

	@Override
	public void destroy() throws IOException {
		try {
			if (this.connectorServer != null) {
				if (logger.isInfoEnabled()) {
					logger.info("Stopping JMX connector server: " + this.connectorServer);
				}
				this.connectorServer.stop();
			}
		}
		finally {
			unregisterBeans();
		}
	}

	@Override
	public void start() throws Exception {
		this.tomcatServer.start();
		this.port = this.tomcatServer.getConnector().getLocalPort();
		this.context.addLifecycleListener(event -> {
			if (logger.isDebugEnabled()) {
				logger.debug("Event: " + event.getType());
			}
		});
	}

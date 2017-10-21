	@Override
	protected void openConnection() {
		this.taskExecutor.execute(() -> {
			try {
				if (logger.isInfoEnabled()) {
					logger.info("Connecting to WebSocket at " + getUri());
				}
				Object endpointToUse = endpoint;
				if (endpointToUse == null) {
					Assert.state(endpointProvider != null, "No endpoint set");
					endpointToUse = endpointProvider.getHandler();
				}
				session = webSocketContainer.connectToServer(endpointToUse, getUri());
				logger.info("Successfully connected to WebSocket");
			}
			catch (Throwable ex) {
				logger.error("Failed to connect to WebSocket", ex);
			}
		});
	}

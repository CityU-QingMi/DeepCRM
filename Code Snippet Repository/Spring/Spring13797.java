	@Override
	protected void openConnection() {
		if (logger.isInfoEnabled()) {
			logger.info("Connecting to WebSocket at " + getUri());
		}

		ListenableFuture<WebSocketSession> future =
				this.client.doHandshake(this.webSocketHandler, this.headers, getUri());

		future.addCallback(new ListenableFutureCallback<WebSocketSession>() {
			@Override
			public void onSuccess(@Nullable WebSocketSession result) {
				webSocketSession = result;
				logger.info("Successfully connected");
			}
			@Override
			public void onFailure(Throwable ex) {
				logger.error("Failed to connect", ex);
			}
		});
	}

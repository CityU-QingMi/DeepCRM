	@Override
	public void start() {
		if (!isRunning()) {
			this.running = true;
			try {
				if (this.factory == null) {
					this.factory = new WebSocketServerFactory(servletContext, this.policy);
				}
				this.factory.setCreator(new WebSocketCreator() {
					@Override
					public Object createWebSocket(ServletUpgradeRequest request, ServletUpgradeResponse response) {
						WebSocketHandlerContainer container = containerHolder.get();
						Assert.state(container != null, "Expected WebSocketHandlerContainer");
						response.setAcceptedSubProtocol(container.getSelectedProtocol());
						response.setExtensions(container.getExtensionConfigs());
						return container.getHandler();
					}
				});
				this.factory.start();
			}
			catch (Throwable ex) {
				throw new IllegalStateException("Unable to start Jetty WebSocketServerFactory", ex);
			}
		}
	}

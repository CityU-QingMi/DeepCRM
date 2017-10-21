	@Override
	public void start() {
		synchronized (this.lifecycleMonitor) {
			ServletContext servletContext = this.servletContext;
			if (!isRunning() && servletContext != null) {
				this.running = true;
				try {
					this.factory = new WebSocketServerFactory(servletContext);
					this.factory.setCreator((request, response) -> {
						WebSocketHandlerContainer container = adapterHolder.get();
						String protocol = container.getProtocol();
						if (protocol != null) {
							response.setAcceptedSubProtocol(protocol);
						}
						return container.getAdapter();
					});
					this.factory.start();
				}
				catch (Throwable ex) {
					throw new IllegalStateException("Unable to start WebSocketServerFactory", ex);
				}
			}
		}
	}

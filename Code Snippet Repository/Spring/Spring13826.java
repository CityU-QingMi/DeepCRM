	@Override
	public SockJsServiceRegistration withSockJS() {
		this.sockJsServiceRegistration = new SockJsServiceRegistration();
		if (this.scheduler != null) {
			this.sockJsServiceRegistration.setTaskScheduler(this.scheduler);
		}
		HandshakeInterceptor[] interceptors = getInterceptors();
		if (interceptors.length > 0) {
			this.sockJsServiceRegistration.setInterceptors(interceptors);
		}
		if (this.handshakeHandler != null) {
			WebSocketTransportHandler transportHandler = new WebSocketTransportHandler(this.handshakeHandler);
			this.sockJsServiceRegistration.setTransportHandlerOverrides(transportHandler);
		}
		if (!this.allowedOrigins.isEmpty()) {
			this.sockJsServiceRegistration.setAllowedOrigins(
					this.allowedOrigins.toArray(new String[this.allowedOrigins.size()]));
		}
		return this.sockJsServiceRegistration;
	}

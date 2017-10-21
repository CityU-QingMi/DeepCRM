	@Override
	public SockJsServiceRegistration withSockJS() {
		this.registration = new SockJsServiceRegistration();
		this.registration.setTaskScheduler(this.sockJsTaskScheduler);
		HandshakeInterceptor[] interceptors = getInterceptors();
		if (interceptors.length > 0) {
			this.registration.setInterceptors(interceptors);
		}
		if (this.handshakeHandler != null) {
			WebSocketTransportHandler handler = new WebSocketTransportHandler(this.handshakeHandler);
			this.registration.setTransportHandlerOverrides(handler);
		}
		if (!this.allowedOrigins.isEmpty()) {
			this.registration.setAllowedOrigins(this.allowedOrigins.toArray(new String[this.allowedOrigins.size()]));
		}
		return this.registration;
	}

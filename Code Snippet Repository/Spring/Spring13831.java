	protected SockJsService getSockJsService() {
		TransportHandlingSockJsService service = createSockJsService();
		service.setHandshakeInterceptors(this.interceptors);

		if (this.clientLibraryUrl != null) {
			service.setSockJsClientLibraryUrl(this.clientLibraryUrl);
		}
		if (this.streamBytesLimit != null) {
			service.setStreamBytesLimit(this.streamBytesLimit);
		}
		if (this.sessionCookieNeeded != null) {
			service.setSessionCookieNeeded(this.sessionCookieNeeded);
		}
		if (this.heartbeatTime != null) {
			service.setHeartbeatTime(this.heartbeatTime);
		}
		if (this.disconnectDelay != null) {
			service.setDisconnectDelay(this.disconnectDelay);
		}
		if (this.httpMessageCacheSize != null) {
			service.setHttpMessageCacheSize(this.httpMessageCacheSize);
		}
		if (this.webSocketEnabled != null) {
			service.setWebSocketEnabled(this.webSocketEnabled);
		}
		if (this.suppressCors != null) {
			service.setSuppressCors(this.suppressCors);
		}
		service.setAllowedOrigins(this.allowedOrigins);

		if (this.messageCodec != null) {
			service.setMessageCodec(this.messageCodec);
		}
		return service;
	}

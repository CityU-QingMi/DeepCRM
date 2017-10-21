	protected final M getMappings() {
		M mappings = createMappings();
		if (this.sockJsServiceRegistration != null) {
			SockJsService sockJsService = this.sockJsServiceRegistration.getSockJsService();
			for (WebSocketHandler wsHandler : this.handlerMap.keySet()) {
				for (String path : this.handlerMap.get(wsHandler)) {
					String pathPattern = (path.endsWith("/") ? path + "**" : path + "/**");
					addSockJsServiceMapping(mappings, sockJsService, wsHandler, pathPattern);
				}
			}
		}
		else {
			HandshakeHandler handshakeHandler = getOrCreateHandshakeHandler();
			HandshakeInterceptor[] interceptors = getInterceptors();
			for (WebSocketHandler wsHandler : this.handlerMap.keySet()) {
				for (String path : this.handlerMap.get(wsHandler)) {
					addWebSocketHandlerMapping(mappings, wsHandler, handshakeHandler, interceptors, path);
				}
			}
		}

		return mappings;
	}

	@Bean
	public HandlerMapping stompWebSocketHandlerMapping() {
		WebSocketHandler handler = decorateWebSocketHandler(subProtocolWebSocketHandler());
		WebMvcStompEndpointRegistry registry = new WebMvcStompEndpointRegistry(handler,
				getTransportRegistration(), messageBrokerTaskScheduler());
		ApplicationContext applicationContext = getApplicationContext();
		if (applicationContext != null) {
			registry.setApplicationContext(applicationContext);
		}
		registerStompEndpoints(registry);
		return registry.getHandlerMapping();
	}

	public WebMvcStompWebSocketEndpointRegistration(String[] paths, WebSocketHandler webSocketHandler,
			TaskScheduler sockJsTaskScheduler) {

		Assert.notEmpty(paths, "No paths specified");
		Assert.notNull(webSocketHandler, "WebSocketHandler must not be null");

		this.paths = paths;
		this.webSocketHandler = webSocketHandler;
		this.sockJsTaskScheduler = sockJsTaskScheduler;
	}

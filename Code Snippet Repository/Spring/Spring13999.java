	public AbstractSockJsSession(String id, SockJsServiceConfig config, WebSocketHandler handler,
			@Nullable Map<String, Object> attributes) {

		Assert.notNull(id, "Session id must not be null");
		Assert.notNull(config, "SockJsServiceConfig must not be null");
		Assert.notNull(handler, "WebSocketHandler must not be null");

		this.id = id;
		this.config = config;
		this.handler = handler;

		if (attributes != null) {
			this.attributes.putAll(attributes);
		}
	}

	public void registerListenerContainer(JmsListenerEndpoint endpoint, JmsListenerContainerFactory<?> factory,
			boolean startImmediately) {

		Assert.notNull(endpoint, "Endpoint must not be null");
		Assert.notNull(factory, "Factory must not be null");
		String id = endpoint.getId();
		Assert.hasText(id, "Endpoint id must be set");

		synchronized (this.listenerContainers) {
			if (this.listenerContainers.containsKey(id)) {
				throw new IllegalStateException("Another endpoint is already registered with id '" + id + "'");
			}
			MessageListenerContainer container = createListenerContainer(endpoint, factory);
			this.listenerContainers.put(id, container);
			if (startImmediately) {
				startIfNecessary(container);
			}
		}
	}

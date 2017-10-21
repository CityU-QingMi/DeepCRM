	public void registerEndpoint(JmsListenerEndpoint endpoint, @Nullable JmsListenerContainerFactory<?> factory) {
		Assert.notNull(endpoint, "Endpoint must not be null");
		Assert.hasText(endpoint.getId(), "Endpoint id must be set");

		// Factory may be null, we defer the resolution right before actually creating the container
		JmsListenerEndpointDescriptor descriptor = new JmsListenerEndpointDescriptor(endpoint, factory);

		synchronized (this.mutex) {
			if (this.startImmediately) {  // register and start immediately
				Assert.state(this.endpointRegistry != null, "No JmsListenerEndpointRegistry set");
				this.endpointRegistry.registerListenerContainer(descriptor.endpoint,
						resolveContainerFactory(descriptor), true);
			}
			else {
				this.endpointDescriptors.add(descriptor);
			}
		}
	}

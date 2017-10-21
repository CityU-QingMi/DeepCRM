	protected void registerAllEndpoints() {
		Assert.state(this.endpointRegistry != null, "No JmsListenerEndpointRegistry set");
		synchronized (this.mutex) {
			for (JmsListenerEndpointDescriptor descriptor : this.endpointDescriptors) {
				this.endpointRegistry.registerListenerContainer(
						descriptor.endpoint, resolveContainerFactory(descriptor));
			}
			this.startImmediately = true;  // trigger immediate startup
		}
	}

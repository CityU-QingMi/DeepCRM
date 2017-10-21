	@Override
	public void start() {
		synchronized (this.lifecycleMonitor) {
			if (!this.running) {
				ResourceAdapter resourceAdapter = getResourceAdapter();
				Assert.state(resourceAdapter != null, "No ResourceAdapter set");
				try {
					resourceAdapter.endpointActivation(getMessageEndpointFactory(), getActivationSpec());
				}
				catch (ResourceException ex) {
					throw new IllegalStateException("Could not activate message endpoint", ex);
				}
				this.running = true;
			}
		}
	}

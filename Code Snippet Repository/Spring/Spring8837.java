	@Override
	public void stop() {
		synchronized (this.lifecycleMonitor) {
			if (this.running) {
				ResourceAdapter resourceAdapter = getResourceAdapter();
				Assert.state(resourceAdapter != null, "No ResourceAdapter set");
				resourceAdapter.endpointDeactivation(getMessageEndpointFactory(), getActivationSpec());
				this.running = false;
			}
		}
	}

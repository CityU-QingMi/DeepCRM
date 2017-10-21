	@Override
	public void stop() {
		if (isRunning()) {
			this.running = false;
			for (Transport transport : this.transports) {
				if (transport instanceof Lifecycle) {
					if (((Lifecycle) transport).isRunning()) {
						((Lifecycle) transport).stop();
					}
				}
			}
		}
	}

	@Override
	public void start() {
		if (!isRunning()) {
			this.running = true;
			for (Transport transport : this.transports) {
				if (transport instanceof Lifecycle) {
					if (!((Lifecycle) transport).isRunning()) {
						((Lifecycle) transport).start();
					}
				}
			}
		}
	}

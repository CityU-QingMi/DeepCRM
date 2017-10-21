	@Override
	public final void start() {
		synchronized (this.lifecycleMonitor) {
			if (!isRunning()) {
				this.running = true;
				try {
					startInternal();
				}
				catch (Throwable ex) {
					throw new IllegalStateException(ex);
				}
			}
		}

	}

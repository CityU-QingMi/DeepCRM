	@Override
	public final void stop() {
		synchronized (this.lifecycleMonitor) {
			if (isRunning()) {
				this.running = false;
				try {
					stopInternal();
				}
				catch (Throwable ex) {
					throw new IllegalStateException(ex);
				}
				finally {
					reset();
				}
			}
		}
	}

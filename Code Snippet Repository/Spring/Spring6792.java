	@Override
	public void stop(Runnable callback) throws JmsException {
		synchronized (this.lifecycleMonitor) {
			if (!isRunning() || this.stopCallback != null) {
				// Not started, already stopped, or previous stop attempt in progress
				// -> return immediately, no stop process to control anymore.
				callback.run();
				return;
			}
			this.stopCallback = callback;
		}
		stop();
	}

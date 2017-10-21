	@Override
	public void destroy() {
		// Cancel all registered timers.
		for (Timer timer : this.timers) {
			try {
				timer.cancel();
			}
			catch (Throwable ex) {
				logger.warn("Could not cancel CommonJ Timer", ex);
			}
		}
		this.timers.clear();

		// Stop the TimerManager itself.
		super.destroy();
	}

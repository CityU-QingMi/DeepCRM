	protected void doStart() throws JMSException {
		// Lazily establish a shared Connection, if necessary.
		if (sharedConnectionEnabled()) {
			establishSharedConnection();
		}

		// Reschedule paused tasks, if any.
		synchronized (this.lifecycleMonitor) {
			this.running = true;
			this.lifecycleMonitor.notifyAll();
			resumePausedTasks();
		}

		// Start the shared Connection, if any.
		if (sharedConnectionEnabled()) {
			startSharedConnection();
		}
	}

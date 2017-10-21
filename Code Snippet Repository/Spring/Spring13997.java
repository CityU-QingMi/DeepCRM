	protected void resetRequest() {
		synchronized (this.responseLock) {
			ServerHttpAsyncRequestControl control = this.asyncRequestControl;
			this.asyncRequestControl = null;
			this.readyToSend = false;
			this.response = null;
			updateLastActiveTime();
			if (control != null && !control.isCompleted()) {
				if (control.isStarted()) {
					try {
						control.complete();
					}
					catch (Throwable ex) {
						// Could be part of normal workflow (e.g. browser tab closed)
						logger.debug("Failed to complete request: " + ex.getMessage());
					}
				}
			}
		}
	}

		@Override
		public void run() {
			synchronized (responseLock) {
				if (!this.expired && !isClosed()) {
					try {
						sendHeartbeat();
					}
					catch (Throwable ex) {
						// Ignore: already handled in writeFrame...
					}
					finally {
						this.expired = true;
					}
				}
			}
		}

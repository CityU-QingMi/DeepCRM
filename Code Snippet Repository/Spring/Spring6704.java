		private void localStop() throws JMSException {
			synchronized (connectionMonitor) {
				if (this.locallyStarted) {
					this.locallyStarted = false;
					if (startedCount == 1 && connection != null) {
						connection.stop();
					}
					if (startedCount > 0) {
						startedCount--;
					}
				}
			}
		}

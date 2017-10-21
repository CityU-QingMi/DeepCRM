		private void localStart() throws JMSException {
			synchronized (connectionMonitor) {
				if (!this.locallyStarted) {
					this.locallyStarted = true;
					if (startedCount == 0 && connection != null) {
						connection.start();
					}
					startedCount++;
				}
			}
		}

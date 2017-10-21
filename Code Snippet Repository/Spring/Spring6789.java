		private void clearResources() {
			if (sharedConnectionEnabled()) {
				synchronized (sharedConnectionMonitor) {
					JmsUtils.closeMessageConsumer(this.consumer);
					JmsUtils.closeSession(this.session);
				}
			}
			else {
				JmsUtils.closeMessageConsumer(this.consumer);
				JmsUtils.closeSession(this.session);
			}
			if (this.consumer != null) {
				synchronized (lifecycleMonitor) {
					registeredWithDestination--;
				}
			}
			this.consumer = null;
			this.session = null;
		}

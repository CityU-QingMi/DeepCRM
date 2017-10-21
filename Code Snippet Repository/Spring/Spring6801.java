	protected void initializeConsumers() throws JMSException {
		// Register Sessions and MessageConsumers.
		synchronized (this.consumersMonitor) {
			if (this.consumers == null) {
				this.sessions = new HashSet<>(this.concurrentConsumers);
				this.consumers = new HashSet<>(this.concurrentConsumers);
				Connection con = getSharedConnection();
				for (int i = 0; i < this.concurrentConsumers; i++) {
					Session session = createSession(con);
					MessageConsumer consumer = createListenerConsumer(session);
					this.sessions.add(session);
					this.consumers.add(consumer);
				}
			}
		}
	}

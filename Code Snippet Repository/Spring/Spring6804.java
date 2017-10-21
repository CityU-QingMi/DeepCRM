	@Override
	protected void doShutdown() throws JMSException {
		synchronized (this.consumersMonitor) {
			if (this.consumers != null) {
				logger.debug("Closing JMS MessageConsumers");
				for (MessageConsumer consumer : this.consumers) {
					JmsUtils.closeMessageConsumer(consumer);
				}
				if (this.sessions != null) {
					logger.debug("Closing JMS Sessions");
					for (Session session : this.sessions) {
						JmsUtils.closeSession(session);
					}
				}
			}
		}
	}

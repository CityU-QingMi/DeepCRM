	@Override
	protected void doInitialize() throws JMSException {
		if (!this.connectLazily) {
			try {
				establishSharedConnection();
			}
			catch (JMSException ex) {
				logger.debug("Could not connect on initialization - registering message consumers lazily", ex);
				return;
			}
			initializeConsumers();
		}
	}

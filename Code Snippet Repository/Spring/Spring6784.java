		@Override
		public Connection createConnection() throws JMSException {
			if (AbstractPollingMessageListenerContainer.this.sharedConnectionEnabled()) {
				Connection sharedCon = AbstractPollingMessageListenerContainer.this.getSharedConnection();
				return new SingleConnectionFactory(sharedCon).createConnection();
			}
			else {
				return AbstractPollingMessageListenerContainer.this.createConnection();
			}
		}

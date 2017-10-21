	protected void processMessage(Message message, Session session) {
		ConnectionFactory connectionFactory = getConnectionFactory();
		boolean exposeResource = (connectionFactory != null && isExposeListenerSession());
		if (exposeResource) {
			TransactionSynchronizationManager.bindResource(
					connectionFactory, new LocallyExposedJmsResourceHolder(session));
		}
		try {
			executeListener(session, message);
		}
		finally {
			if (exposeResource) {
				TransactionSynchronizationManager.unbindResource(getConnectionFactory());
			}
		}
	}

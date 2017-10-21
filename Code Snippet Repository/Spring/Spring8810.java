	@Override
	protected void doCleanupAfterCompletion(Object transaction) {
		CciLocalTransactionObject txObject = (CciLocalTransactionObject) transaction;
		ConnectionFactory connectionFactory = obtainConnectionFactory();

		// Remove the connection holder from the thread.
		TransactionSynchronizationManager.unbindResource(connectionFactory);
		txObject.getConnectionHolder().clear();

		Connection con = txObject.getConnectionHolder().getConnection();
		if (logger.isDebugEnabled()) {
			logger.debug("Releasing CCI Connection [" + con + "] after transaction");
		}
		ConnectionFactoryUtils.releaseConnection(con, connectionFactory);
	}

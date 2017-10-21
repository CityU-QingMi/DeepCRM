	@Override
	protected void doBegin(Object transaction, TransactionDefinition definition) {
		if (definition.getIsolationLevel() != TransactionDefinition.ISOLATION_DEFAULT) {
			throw new InvalidIsolationLevelException("JMS does not support an isolation level concept");
		}

		ConnectionFactory connectionFactory = obtainConnectionFactory();
		JmsTransactionObject txObject = (JmsTransactionObject) transaction;
		Connection con = null;
		Session session = null;
		try {
			con = createConnection();
			session = createSession(con);
			if (logger.isDebugEnabled()) {
				logger.debug("Created JMS transaction on Session [" + session + "] from Connection [" + con + "]");
			}
			JmsResourceHolder resourceHolder = new JmsResourceHolder(connectionFactory, con, session);
			resourceHolder.setSynchronizedWithTransaction(true);
			int timeout = determineTimeout(definition);
			if (timeout != TransactionDefinition.TIMEOUT_DEFAULT) {
				resourceHolder.setTimeoutInSeconds(timeout);
			}
			txObject.setResourceHolder(resourceHolder);
			TransactionSynchronizationManager.bindResource(connectionFactory, resourceHolder);
		}
		catch (Throwable ex) {
			if (session != null) {
				try {
					session.close();
				}
				catch (Throwable ex2) {
					// ignore
				}
			}
			if (con != null) {
				try {
					con.close();
				}
				catch (Throwable ex2) {
					// ignore
				}
			}
			throw new CannotCreateTransactionException("Could not create JMS transaction", ex);
		}
	}

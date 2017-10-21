	@Override
	protected void doBegin(Object transaction, TransactionDefinition definition) {
		CciLocalTransactionObject txObject = (CciLocalTransactionObject) transaction;
		ConnectionFactory connectionFactory = obtainConnectionFactory();
		Connection con = null;

		try {
			con = connectionFactory.getConnection();
			if (logger.isDebugEnabled()) {
				logger.debug("Acquired Connection [" + con + "] for local CCI transaction");
			}

			ConnectionHolder connectionHolder = new ConnectionHolder(con);
			connectionHolder.setSynchronizedWithTransaction(true);

			con.getLocalTransaction().begin();
			int timeout = determineTimeout(definition);
			if (timeout != TransactionDefinition.TIMEOUT_DEFAULT) {
				connectionHolder.setTimeoutInSeconds(timeout);
			}

			txObject.setConnectionHolder(connectionHolder);
			TransactionSynchronizationManager.bindResource(connectionFactory, connectionHolder);
		}
		catch (NotSupportedException ex) {
			ConnectionFactoryUtils.releaseConnection(con, connectionFactory);
			throw new CannotCreateTransactionException("CCI Connection does not support local transactions", ex);
		}
		catch (LocalTransactionException ex) {
			ConnectionFactoryUtils.releaseConnection(con, connectionFactory);
			throw new CannotCreateTransactionException("Could not begin local CCI transaction", ex);
		}
		catch (Throwable ex) {
			ConnectionFactoryUtils.releaseConnection(con, connectionFactory);
			throw new TransactionSystemException("Unexpected failure on begin of CCI local transaction", ex);
		}
	}

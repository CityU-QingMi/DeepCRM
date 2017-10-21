	@Override
	protected void doRollback(DefaultTransactionStatus status) {
		JmsTransactionObject txObject = (JmsTransactionObject) status.getTransaction();
		Session session = txObject.getResourceHolder().getSession();
		if (session != null) {
			try {
				if (status.isDebug()) {
					logger.debug("Rolling back JMS transaction on Session [" + session + "]");
				}
				session.rollback();
			}
			catch (JMSException ex) {
				throw new TransactionSystemException("Could not roll back JMS transaction", ex);
			}
		}
	}

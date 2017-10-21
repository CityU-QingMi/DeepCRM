	@Override
	protected void doCommit(DefaultTransactionStatus status) {
		JmsTransactionObject txObject = (JmsTransactionObject) status.getTransaction();
		Session session = txObject.getResourceHolder().getSession();
		if (session != null) {
			try {
				if (status.isDebug()) {
					logger.debug("Committing JMS transaction on Session [" + session + "]");
				}
				session.commit();
			}
			catch (TransactionRolledBackException ex) {
				throw new UnexpectedRollbackException("JMS transaction rolled back", ex);
			}
			catch (JMSException ex) {
				throw new TransactionSystemException("Could not commit JMS transaction", ex);
			}
		}
	}

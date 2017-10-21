	@Override
	protected void doRollback(DefaultTransactionStatus status) {
		JtaTransactionObject txObject = (JtaTransactionObject) status.getTransaction();
		try {
			int jtaStatus = txObject.getUserTransaction().getStatus();
			if (jtaStatus != Status.STATUS_NO_TRANSACTION) {
				try {
					txObject.getUserTransaction().rollback();
				}
				catch (IllegalStateException ex) {
					if (jtaStatus == Status.STATUS_ROLLEDBACK) {
						// Only really happens on JBoss 4.2 in case of an early timeout...
						if (logger.isDebugEnabled()) {
							logger.debug("Rollback failure with transaction already marked as rolled back: " + ex);
						}
					}
					else {
						throw new TransactionSystemException("Unexpected internal transaction state", ex);
					}
				}
			}
		}
		catch (SystemException ex) {
			throw new TransactionSystemException("JTA failure on rollback", ex);
		}
	}

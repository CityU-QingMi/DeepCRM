	@Override
	protected void doSetRollbackOnly(DefaultTransactionStatus status) {
		JtaTransactionObject txObject = (JtaTransactionObject) status.getTransaction();
		if (status.isDebug()) {
			logger.debug("Setting JTA transaction rollback-only");
		}
		try {
			int jtaStatus = txObject.getUserTransaction().getStatus();
			if (jtaStatus != Status.STATUS_NO_TRANSACTION && jtaStatus != Status.STATUS_ROLLEDBACK) {
				txObject.getUserTransaction().setRollbackOnly();
			}
		}
		catch (IllegalStateException ex) {
			throw new TransactionSystemException("Unexpected internal transaction state", ex);
		}
		catch (SystemException ex) {
			throw new TransactionSystemException("JTA failure on setRollbackOnly", ex);
		}
	}

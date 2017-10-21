	@Override
	protected void doCleanupAfterCompletion(Object transaction) {
		JtaTransactionObject txObject = (JtaTransactionObject) transaction;
		if (txObject.resetTransactionTimeout) {
			try {
				txObject.getUserTransaction().setTransactionTimeout(0);
			}
			catch (SystemException ex) {
				logger.debug("Failed to reset transaction timeout after JTA completion", ex);
			}
		}
	}

	@Override
	protected void doResume(@Nullable Object transaction, Object suspendedResources) {
		JtaTransactionObject txObject = (JtaTransactionObject) transaction;
		try {
			doJtaResume(txObject, suspendedResources);
		}
		catch (InvalidTransactionException ex) {
			throw new IllegalTransactionStateException("Tried to resume invalid JTA transaction", ex);
		}
		catch (IllegalStateException ex) {
			throw new TransactionSystemException("Unexpected internal transaction state", ex);
		}
		catch (SystemException ex) {
			throw new TransactionSystemException("JTA failure on resume", ex);
		}
	}

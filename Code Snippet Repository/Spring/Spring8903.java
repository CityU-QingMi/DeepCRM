	@Override
	protected void registerAfterCompletionWithExistingTransaction(
			Object transaction, List<TransactionSynchronization> synchronizations) {

		JtaTransactionObject txObject = (JtaTransactionObject) transaction;
		logger.debug("Registering after-completion synchronization with existing JTA transaction");
		try {
			doRegisterAfterCompletionWithJtaTransaction(txObject, synchronizations);
		}
		catch (SystemException ex) {
			throw new TransactionSystemException("JTA failure on registerSynchronization", ex);
		}
		catch (Exception ex) {
			// Note: JBoss throws plain RuntimeException with RollbackException as cause.
			if (ex instanceof RollbackException || ex.getCause() instanceof RollbackException) {
				logger.debug("Participating in existing JTA transaction that has been marked for rollback: " +
						"cannot register Spring after-completion callbacks with outer JTA transaction - " +
						"immediately performing Spring after-completion callbacks with outcome status 'rollback'. " +
						"Original exception: " + ex);
				invokeAfterCompletion(synchronizations, TransactionSynchronization.STATUS_ROLLED_BACK);
			}
			else {
				logger.debug("Participating in existing JTA transaction, but unexpected internal transaction " +
						"state encountered: cannot register Spring after-completion callbacks with outer JTA " +
						"transaction - processing Spring after-completion callbacks with outcome status 'unknown'" +
						"Original exception: " + ex);
				invokeAfterCompletion(synchronizations, TransactionSynchronization.STATUS_UNKNOWN);
			}
		}
	}

	protected void doRegisterAfterCompletionWithJtaTransaction(
			JtaTransactionObject txObject, List<TransactionSynchronization> synchronizations)
			throws RollbackException, SystemException {

		int jtaStatus = txObject.getUserTransaction().getStatus();
		if (jtaStatus == Status.STATUS_NO_TRANSACTION) {
			throw new RollbackException("JTA transaction already completed - probably rolled back");
		}
		if (jtaStatus == Status.STATUS_ROLLEDBACK) {
			throw new RollbackException("JTA transaction already rolled back (probably due to a timeout)");
		}

		if (this.transactionSynchronizationRegistry != null) {
			// JTA 1.1 TransactionSynchronizationRegistry available - use it.
			this.transactionSynchronizationRegistry.registerInterposedSynchronization(
					new JtaAfterCompletionSynchronization(synchronizations));
		}

		else if (getTransactionManager() != null) {
			// At least the JTA TransactionManager available - use that one.
			Transaction transaction = getTransactionManager().getTransaction();
			if (transaction == null) {
				throw new IllegalStateException("No JTA Transaction available");
			}
			transaction.registerSynchronization(new JtaAfterCompletionSynchronization(synchronizations));
		}

		else {
			// No JTA TransactionManager available - log a warning.
			logger.warn("Participating in existing JTA transaction, but no JTA TransactionManager available: " +
					"cannot register Spring after-completion callbacks with outer JTA transaction - " +
					"processing Spring after-completion callbacks with outcome status 'unknown'");
			invokeAfterCompletion(synchronizations, TransactionSynchronization.STATUS_UNKNOWN);
		}
	}

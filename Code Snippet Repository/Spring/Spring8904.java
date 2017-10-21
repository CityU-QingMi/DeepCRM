	protected void checkUserTransactionAndTransactionManager() throws IllegalStateException {
		// We at least need the JTA UserTransaction.
		if (this.userTransaction != null) {
			if (logger.isInfoEnabled()) {
				logger.info("Using JTA UserTransaction: " + this.userTransaction);
			}
		}
		else {
			throw new IllegalStateException("No JTA UserTransaction available - specify either " +
					"'userTransaction' or 'userTransactionName' or 'transactionManager' or 'transactionManagerName'");
		}

		// For transaction suspension, the JTA TransactionManager is necessary too.
		if (this.transactionManager != null) {
			if (logger.isInfoEnabled()) {
				logger.info("Using JTA TransactionManager: " + this.transactionManager);
			}
		}
		else {
			logger.warn("No JTA TransactionManager found: transaction suspension not available");
		}
	}

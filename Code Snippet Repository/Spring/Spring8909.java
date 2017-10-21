	protected void initTransactionSynchronizationRegistry() {
		if (this.transactionSynchronizationRegistry == null) {
			// Fetch JTA TransactionSynchronizationRegistry from JNDI, if necessary.
			if (StringUtils.hasLength(this.transactionSynchronizationRegistryName)) {
				this.transactionSynchronizationRegistry =
						lookupTransactionSynchronizationRegistry(this.transactionSynchronizationRegistryName);
			}
			else {
				this.transactionSynchronizationRegistry = retrieveTransactionSynchronizationRegistry();
				if (this.transactionSynchronizationRegistry == null && this.autodetectTransactionSynchronizationRegistry) {
					// Autodetect in JNDI if applicable, and check UserTransaction/TransactionManager
					// object that implements TransactionSynchronizationRegistry otherwise.
					this.transactionSynchronizationRegistry =
							findTransactionSynchronizationRegistry(this.userTransaction, this.transactionManager);
				}
			}
		}

		if (this.transactionSynchronizationRegistry != null) {
			if (logger.isInfoEnabled()) {
				logger.info("Using JTA TransactionSynchronizationRegistry: " + this.transactionSynchronizationRegistry);
			}
		}
	}

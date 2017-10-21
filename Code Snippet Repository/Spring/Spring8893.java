	protected void initUserTransactionAndTransactionManager() throws TransactionSystemException {
		if (this.userTransaction == null) {
			// Fetch JTA UserTransaction from JNDI, if necessary.
			if (StringUtils.hasLength(this.userTransactionName)) {
				this.userTransaction = lookupUserTransaction(this.userTransactionName);
				this.userTransactionObtainedFromJndi = true;
			}
			else {
				this.userTransaction = retrieveUserTransaction();
				if (this.userTransaction == null && this.autodetectUserTransaction) {
					// Autodetect UserTransaction at its default JNDI location.
					this.userTransaction = findUserTransaction();
				}
			}
		}

		if (this.transactionManager == null) {
			// Fetch JTA TransactionManager from JNDI, if necessary.
			if (StringUtils.hasLength(this.transactionManagerName)) {
				this.transactionManager = lookupTransactionManager(this.transactionManagerName);
			}
			else {
				this.transactionManager = retrieveTransactionManager();
				if (this.transactionManager == null && this.autodetectTransactionManager) {
					// Autodetect UserTransaction object that implements TransactionManager,
					// and check fallback JNDI locations otherwise.
					this.transactionManager = findTransactionManager(this.userTransaction);
				}
			}
		}

		// If only JTA TransactionManager specified, create UserTransaction handle for it.
		if (this.userTransaction == null && this.transactionManager != null) {
			this.userTransaction = buildUserTransaction(this.transactionManager);
		}
	}

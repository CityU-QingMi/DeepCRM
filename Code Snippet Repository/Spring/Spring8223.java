	void startTransaction() {
		Assert.state(this.transactionStatus == null,
				"Cannot start a new transaction without ending the existing transaction first.");

		this.flaggedForRollback = this.defaultRollback;
		this.transactionStatus = this.transactionManager.getTransaction(this.transactionDefinition);
		++this.transactionsStarted;

		if (logger.isInfoEnabled()) {
			logger.info(String.format(
					"Began transaction (%s) for test context %s; transaction manager [%s]; rollback [%s]",
					this.transactionsStarted, this.testContext, this.transactionManager, flaggedForRollback));
		}
	}

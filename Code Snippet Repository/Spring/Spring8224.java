	void endTransaction() {
		if (logger.isTraceEnabled()) {
			logger.trace(String.format(
					"Ending transaction for test context %s; transaction status [%s]; rollback [%s]",
					this.testContext, this.transactionStatus, this.flaggedForRollback));
		}
		Assert.state(this.transactionStatus != null, () -> String.format(
				"Failed to end transaction for test context %s: transaction does not exist.", this.testContext));

		try {
			if (this.flaggedForRollback) {
				this.transactionManager.rollback(this.transactionStatus);
			}
			else {
				this.transactionManager.commit(this.transactionStatus);
			}
		}
		finally {
			this.transactionStatus = null;
		}

		if (logger.isInfoEnabled()) {
			logger.info(String.format("%s transaction for test context %s.",
					(this.flaggedForRollback ? "Rolled back" : "Committed"), this.testContext));
		}
	}

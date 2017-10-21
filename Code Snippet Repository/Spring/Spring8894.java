	@Override
	protected Object doGetTransaction() {
		UserTransaction ut = getUserTransaction();
		if (ut == null) {
			throw new CannotCreateTransactionException("No JTA UserTransaction available - " +
					"programmatic PlatformTransactionManager.getTransaction usage not supported");
		}
		if (!this.cacheUserTransaction) {
			ut = lookupUserTransaction(
					this.userTransactionName != null ? this.userTransactionName : DEFAULT_USER_TRANSACTION_NAME);
		}
		return doGetJtaTransaction(ut);
	}

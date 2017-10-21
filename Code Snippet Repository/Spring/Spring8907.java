	@Override
	public Transaction createTransaction(@Nullable String name, int timeout) throws NotSupportedException, SystemException {
		TransactionManager tm = getTransactionManager();
		Assert.state(tm != null, "No JTA TransactionManager available");
		if (timeout >= 0) {
			tm.setTransactionTimeout(timeout);
		}
		tm.begin();
		return new ManagedTransactionAdapter(tm);
	}

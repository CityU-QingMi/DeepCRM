	@Override
	protected void doBegin(Object transaction, TransactionDefinition definition) {
		if (!TRANSACTION.equals(transaction)) {
			throw new IllegalArgumentException("Not the same transaction object");
		}
		if (!this.canCreateTransaction) {
			throw new CannotCreateTransactionException("Cannot create transaction");
		}
		this.begin = true;
	}

	public void setTransactionManager(Object transactionManager) {
		if (transactionManager instanceof TransactionFactory) {
			this.transactionFactory = (TransactionFactory) transactionManager;
		}
		else if (transactionManager instanceof TransactionManager) {
			this.transactionFactory = new SimpleTransactionFactory((TransactionManager) transactionManager);
		}
		else {
			throw new IllegalArgumentException("Transaction manager [" + transactionManager +
					"] is neither a [org.springframework.transaction.jta.TransactionFactory} nor a " +
					"[javax.transaction.TransactionManager]");
		}
	}

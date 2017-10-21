	public static boolean isActive() {
		TransactionContext transactionContext = TransactionContextHolder.getCurrentTransactionContext();
		if (transactionContext != null) {
			TransactionStatus transactionStatus = transactionContext.getTransactionStatus();
			return (transactionStatus != null) && (!transactionStatus.isCompleted());
		}

		// else
		return false;
	}

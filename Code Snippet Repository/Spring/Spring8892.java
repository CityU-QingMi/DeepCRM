	@Override
	public void afterCompletion(int status) {
		switch (status) {
			case Status.STATUS_COMMITTED:
				try {
					TransactionSynchronizationUtils.invokeAfterCommit(this.synchronizations);
				}
				finally {
					TransactionSynchronizationUtils.invokeAfterCompletion(
							this.synchronizations, TransactionSynchronization.STATUS_COMMITTED);
				}
				break;
			case Status.STATUS_ROLLEDBACK:
				TransactionSynchronizationUtils.invokeAfterCompletion(
						this.synchronizations, TransactionSynchronization.STATUS_ROLLED_BACK);
				break;
			default:
				TransactionSynchronizationUtils.invokeAfterCompletion(
						this.synchronizations, TransactionSynchronization.STATUS_UNKNOWN);
		}
	}

	@Override
	public RuntimeException mapStatusCheckFailure(
			String message,
			SystemException systemException,
			SessionImplementor sessionImplementor) {
		return new TransactionException(
				"could not determine transaction status in beforeCompletion()",
				systemException
		);
	}

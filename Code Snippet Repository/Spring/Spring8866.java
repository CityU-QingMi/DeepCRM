	@Override
	@Nullable
	public TransactionAttribute getTransactionAttribute(Method method, @Nullable Class<?> targetClass) {
		for (TransactionAttributeSource tas : this.transactionAttributeSources) {
			TransactionAttribute ta = tas.getTransactionAttribute(method, targetClass);
			if (ta != null) {
				return ta;
			}
		}
		return null;
	}

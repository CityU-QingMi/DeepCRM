	@Override
	protected Object doSuspend(Object transaction) {
		JtaTransactionObject txObject = (JtaTransactionObject) transaction;
		try {
			return doJtaSuspend(txObject);
		}
		catch (SystemException ex) {
			throw new TransactionSystemException("JTA failure on suspend", ex);
		}
	}

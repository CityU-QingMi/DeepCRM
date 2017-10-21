	@Override
	protected void doBegin(Object transaction, TransactionDefinition definition) {
		JtaTransactionObject txObject = (JtaTransactionObject) transaction;
		try {
			doJtaBegin(txObject, definition);
		}
		catch (NotSupportedException ex) {
			// assume nested transaction not supported
			throw new NestedTransactionNotSupportedException(
					"JTA implementation does not support nested transactions", ex);
		}
		catch (UnsupportedOperationException ex) {
			// assume nested transaction not supported
			throw new NestedTransactionNotSupportedException(
					"JTA implementation does not support nested transactions", ex);
		}
		catch (SystemException ex) {
			throw new CannotCreateTransactionException("JTA failure on begin", ex);
		}
	}

	protected void doJtaResume(@Nullable JtaTransactionObject txObject, Object suspendedTransaction)
		throws InvalidTransactionException, SystemException {

		if (getTransactionManager() == null) {
			throw new TransactionSuspensionNotSupportedException(
					"JtaTransactionManager needs a JTA TransactionManager for suspending a transaction: " +
					"specify the 'transactionManager' or 'transactionManagerName' property");
		}
		getTransactionManager().resume((Transaction) suspendedTransaction);
	}

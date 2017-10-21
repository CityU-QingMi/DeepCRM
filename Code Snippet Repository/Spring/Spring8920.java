	@Override
	@Nullable
	protected UserTransaction retrieveUserTransaction() throws TransactionSystemException {
		Object helper = loadWebLogicTransactionHelper();
		try {
			logger.debug("Retrieving JTA UserTransaction from WebLogic TransactionHelper");
			Method getUserTransactionMethod = helper.getClass().getMethod("getUserTransaction");
			return (UserTransaction) getUserTransactionMethod.invoke(this.transactionHelper);
		}
		catch (InvocationTargetException ex) {
			throw new TransactionSystemException(
					"WebLogic's TransactionHelper.getUserTransaction() method failed", ex.getTargetException());
		}
		catch (Exception ex) {
			throw new TransactionSystemException(
					"Could not invoke WebLogic's TransactionHelper.getUserTransaction() method", ex);
		}
	}

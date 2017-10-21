	private Object loadWebLogicTransactionHelper() throws TransactionSystemException {
		Object helper = this.transactionHelper;
		if (helper == null) {
			try {
				Class<?> transactionHelperClass = getClass().getClassLoader().loadClass(TRANSACTION_HELPER_CLASS_NAME);
				Method getTransactionHelperMethod = transactionHelperClass.getMethod("getTransactionHelper");
				helper = getTransactionHelperMethod.invoke(null);
				this.transactionHelper = helper;
				logger.debug("WebLogic TransactionHelper found");
			}
			catch (InvocationTargetException ex) {
				throw new TransactionSystemException(
						"WebLogic's TransactionHelper.getTransactionHelper() method failed", ex.getTargetException());
			}
			catch (Exception ex) {
				throw new TransactionSystemException(
						"Could not initialize WebLogicJtaTransactionManager because WebLogic API classes are not available",
						ex);
			}
		}
		return helper;
	}

	@Override
	public TransactionManager retrieveTransactionManager() {
		try {
			final TransactionManager transactionManager = osgiServiceUtil.getServiceImpl(
					TransactionManager.class );
			if (transactionManager == null) {
				throw new TransactionException("Cannot retrieve the TransactionManager OSGi service!");
			}
			return transactionManager;
		}
		catch (Exception e) {
			throw new TransactionException("Cannot retrieve the TransactionManager OSGi service!", e);
		}
	}

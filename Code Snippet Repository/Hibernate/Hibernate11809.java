	@Override
	public UserTransaction retrieveUserTransaction() {
		try {
			final UserTransaction userTransaction = osgiServiceUtil.getServiceImpl(
					UserTransaction.class );
			if (userTransaction == null) {
				throw new TransactionException("Cannot retrieve the UserTransaction OSGi service!");
			}
			return userTransaction;
		}
		catch (Exception e) {
			throw new TransactionException("Cannot retrieve the UserTransaction OSGi service!", e);
		}
	}

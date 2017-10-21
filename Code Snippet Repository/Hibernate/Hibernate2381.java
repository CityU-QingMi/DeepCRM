	@Override
	@SuppressWarnings("")
	protected TransactionManager locateTransactionManager() {
		try {
			final Method method = transactionManagerAccessClass.getMethod( "getTransactionManager" );
			return (TransactionManager) method.invoke( null );
		}
		catch (Exception e) {
			throw new JtaPlatformException( "Could not obtain WebSphere TransactionManager", e );
		}

	}

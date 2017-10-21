	@Override
	protected TransactionManager locateTransactionManager() {
		try {
			Class transactionManagerServicesClass = serviceRegistry().getService( ClassLoaderService.class ).classForName( TM_CLASS_NAME );
			final Method getTransactionManagerMethod = transactionManagerServicesClass.getMethod( "getTransactionManager" );
			return (TransactionManager) getTransactionManagerMethod.invoke( null );
		}
		catch (Exception e) {
			throw new JtaPlatformException( "Could not locate Bitronix TransactionManager", e );
		}
	}

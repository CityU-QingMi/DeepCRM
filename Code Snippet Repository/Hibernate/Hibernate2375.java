	@Override
	protected TransactionManager locateTransactionManager() {
		try {
			final Class clazz = Class.forName( TM_CLASS_NAME );
			final Method getTransactionManagerMethod = clazz.getMethod( "getTransactionManager" );
			return (TransactionManager) getTransactionManagerMethod.invoke( null );
		}
		catch (Exception e) {
			throw new JtaPlatformException( "Could not obtain JOnAS transaction manager instance", e );
		}
	}

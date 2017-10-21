	@Override
	protected TransactionManager locateTransactionManager() {
		try {
			final Class tmClass = serviceRegistry().getService( ClassLoaderService.class ).classForName( TM_CLASS_NAME );
			final Method getTransactionManagerMethod = tmClass.getMethod( "getTransactionManager" );
			return (TransactionManager) getTransactionManagerMethod.invoke( null, (Object[]) null );
		}
		catch (Exception e) {
			throw new JtaPlatformException( "Could not obtain JOTM transaction manager instance", e );
		}
	}

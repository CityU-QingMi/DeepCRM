	@Override
	protected TransactionManager locateTransactionManager() {
		try {
			return (TransactionManager) jndiService().locate( AS7_TM_NAME );
		}
		catch (JndiException jndiException) {
			try {
				return (TransactionManager) jndiService().locate( AS4_TM_NAME );
			}
			catch (JndiException jndiExceptionInner) {
				throw new JndiException( "unable to find transaction manager", jndiException );
			}
		}
	}

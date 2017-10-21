	@Override
	protected UserTransaction locateUserTransaction() {
		try {
			return (UserTransaction) jndiService().locate( JBOSS_UT_NAME );
		}
		catch (JndiException jndiException) {
			try {
				return (UserTransaction) jndiService().locate( UT_NAME );
			}
			catch (JndiException jndiExceptionInner) {
				throw new JndiException( "unable to find UserTransaction", jndiException );
			}
		}
	}

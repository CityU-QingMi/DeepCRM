	@Override
	public RuntimeException mapManagedFlushFailure(String message, RuntimeException failure, SessionImplementor session) {
		if ( HibernateException.class.isInstance( failure ) ) {
			throw session.getExceptionConverter().convert( failure );
		}
		if ( PersistenceException.class.isInstance( failure ) ) {
			throw failure;
		}
		throw new PersistenceException( message, failure );
	}

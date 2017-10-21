	@Override
	public Session currentSession() {
		final Session current = existingSession( factory() );
		if ( current == null ) {
			throw new HibernateException( "No session currently bound to execution context" );
		}
		else {
			validateExistingSession( current );
		}
		return current;
	}

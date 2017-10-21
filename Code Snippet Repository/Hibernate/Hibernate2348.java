	@Deprecated
	public SessionDelegatorBaseImpl(SessionImplementor delegate, Session session) {
		if ( delegate == null ) {
			throw new IllegalArgumentException( "Unable to create a SessionDelegatorBaseImpl from a null delegate object" );
		}
		if ( session == null ) {
			throw new IllegalArgumentException( "Unable to create a SessionDelegatorBaseImpl from a null Session" );
		}
		if ( delegate != session ) {
			throw new IllegalArgumentException( "Unable to create a SessionDelegatorBaseImpl from different Session/SessionImplementor references" );
		}

		this.delegate = delegate;
	}

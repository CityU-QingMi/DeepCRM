	@Override
	public void register(Statement statement, boolean cancelable) {
		log.tracef( "Registering statement [%s]", statement );
		if ( xref.containsKey( statement ) ) {
			throw new HibernateException( "JDBC Statement already registered" );
		}
		xref.put( statement, null );

		if ( cancelable ) {
			lastQuery = statement;
		}
	}

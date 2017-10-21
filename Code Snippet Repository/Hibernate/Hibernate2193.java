	@Override
	public Statement createStatement() {
		try {
			final Statement statement = connection().createStatement();
			jdbcCoordinator.getResourceRegistry().register( statement, true );
			return statement;
		}
		catch ( SQLException e ) {
			throw sqlExceptionHelper().convert( e, "could not create statement" );
		}
	}

	@Override
	public void addToBatch() {
		notifyObserversImplicitExecution();
		for ( Map.Entry<String,PreparedStatement> entry : getStatements().entrySet() ) {
			try {
				final PreparedStatement statement = entry.getValue();
				final int rowCount = jdbcCoordinator.getResultSetReturn().executeUpdate( statement );
				getKey().getExpectation().verifyOutcome( rowCount, statement, 0 );
				jdbcCoordinator.getResourceRegistry().release( statement );
				jdbcCoordinator.afterStatementExecution();
			}
			catch ( SQLException e ) {
				abortBatch();
				throw sqlExceptionHelper().convert( e, "could not execute non-batched batch statement", entry.getKey() );
			}
			catch (JDBCException e) {
				abortBatch();
				throw e;
			}
		}

		getStatements().clear();
	}

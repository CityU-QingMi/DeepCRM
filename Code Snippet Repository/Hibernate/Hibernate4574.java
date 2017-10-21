	@Override
	public void release(ResultSet resultSet, Statement statement) {
		log.tracef( "Releasing result set [%s]", resultSet );

		if ( statement == null ) {
			try {
				statement = resultSet.getStatement();
			}
			catch (SQLException e) {
				throw convert( e, "unable to access Statement from ResultSet" );
			}
		}
		if ( statement != null ) {
			final Set<ResultSet> resultSets = xref.get( statement );
			if ( resultSets == null ) {
				log.unregisteredStatement();
			}
			else {
				resultSets.remove( resultSet );
				if ( resultSets.isEmpty() ) {
					xref.remove( statement );
				}
			}
		}
		else {
			final boolean removed = unassociatedResultSets.remove( resultSet );
			if ( !removed ) {
				log.unregisteredResultSetWithoutStatement();
			}

		}
		close( resultSet );
	}

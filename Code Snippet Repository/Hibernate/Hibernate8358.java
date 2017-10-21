	private void releaseQuietly(SessionImplementor s, ResultSet resultSet, Statement statement) {
		if ( resultSet == null ) {
			return;
		}
		try {
			s.getJdbcCoordinator().getResourceRegistry().release( resultSet, statement );
		}
		catch (Exception e) {
			// ignore
		}
	}

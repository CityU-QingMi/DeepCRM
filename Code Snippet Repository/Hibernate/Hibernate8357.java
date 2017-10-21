	private void releaseQuietly(SessionImplementor s, Statement statement) {
		if ( statement == null ) {
			return;
		}
		try {
			s.getJdbcCoordinator().getResourceRegistry().release( statement );
		}
		catch (Exception e) {
			// ignore
		}
	}

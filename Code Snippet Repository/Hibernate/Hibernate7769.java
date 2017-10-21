	private void releaseStatement(Session session, PreparedStatement ps) {
		if ( ps != null ) {
			try {
				( (SessionImplementor) session ).getJdbcCoordinator().getResourceRegistry().release( ps );
			}
			catch ( Throwable ignore ) {
				// ignore...
			}
		}
	}

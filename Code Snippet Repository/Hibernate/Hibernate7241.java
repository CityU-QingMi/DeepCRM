	private void updateStatuses(final SessionImplementor session) throws Throwable {
		((Session)session).doWork(
				new Work() {
					@Override
					public void execute(Connection connection) throws SQLException {
						PreparedStatement stmnt = null;
						try {
							stmnt = session.getJdbcCoordinator().getStatementPreparer().prepareStatement( "UPDATE T_JOB SET JOB_STATUS = 1" );
							session.getJdbcCoordinator().getResultSetReturn().executeUpdate( stmnt );
						}
						finally {
							if ( stmnt != null ) {
								try {
									session.getJdbcCoordinator().getResourceRegistry().release( stmnt );
								}
								catch( Throwable ignore ) {
								}
							}
						}
					}
				}
		);
	}

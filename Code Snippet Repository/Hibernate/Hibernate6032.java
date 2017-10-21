	public void executeStatement(EntityManagerFactory emf, String toExecute) {
		final SessionFactoryImplementor sf = emf.unwrap( SessionFactoryImplementor.class );
		final JdbcConnectionAccess connectionAccess = sf.getServiceRegistry()
				.getService( JdbcServices.class )
				.getBootstrapJdbcConnectionAccess();
		final Connection conn;
		try {
			conn = connectionAccess.obtainConnection();
			conn.setAutoCommit( false );

			try {
				Statement statement = conn.createStatement();
				statement.execute( toExecute );

				try {
					statement.close();
				}
				catch (SQLException e) {
					fail( e.getMessage() );
				}
			}
			finally {
				try {
					conn.commit();
				}
				catch (SQLException e) {
					fail( e.getMessage() );
				}

				try {
					connectionAccess.releaseConnection( conn );
				}
				catch (SQLException e) {
					fail( e.getMessage() );
				}
			}
		}
		catch (SQLException e) {
			throw new RuntimeException( "Unable to create stored procedures", e );
		}
	}

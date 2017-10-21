	public static void setJdbcTimeout(Session session, long millis) {

		session.doWork( connection -> {
			if ( Dialect.getDialect() instanceof PostgreSQL81Dialect ) {
				try (Statement st = connection.createStatement()) {
					//Prepared Statements fail for SET commands
					st.execute(String.format( "SET statement_timeout TO %d", millis / 10));
				}

			}
			else if( Dialect.getDialect() instanceof MySQLDialect ) {
				try (PreparedStatement st = connection.prepareStatement("SET SESSION innodb_lock_wait_timeout = ?")) {
					st.setLong( 1, TimeUnit.MILLISECONDS.toSeconds( millis ) );
					st.execute();
				}
			}
			else if( Dialect.getDialect() instanceof H2Dialect ) {
				try (PreparedStatement st = connection.prepareStatement("SET LOCK_TIMEOUT ?")) {
					st.setLong( 1, millis / 10 );
					st.execute();
				}
			}
			else if( Dialect.getDialect() instanceof SQLServerDialect ) {
				try (Statement st = connection.createStatement()) {
					//Prepared Statements fail for SET commands
					st.execute(String.format( "SET LOCK_TIMEOUT %d", millis / 10));
				}
			}
			else if( Dialect.getDialect() instanceof AbstractHANADialect ) {
				try (Statement st = connection.createStatement()) {
					//Prepared Statements fail for SET commands
					st.execute(String.format( "SET TRANSACTION LOCK WAIT TIMEOUT %d", millis ));
				}
			}
			else {
				try {
					connection.setNetworkTimeout( Executors.newSingleThreadExecutor(), (int) millis );
				}
				catch (Throwable ignore) {
					ignore.fillInStackTrace();
				}
			}
		} );
	}

	public DdlTransactionIsolatorJtaImpl(JdbcContext jdbcContext) {
		this.jdbcContext = jdbcContext;

		try {
			final JtaPlatform jtaPlatform = jdbcContext.getServiceRegistry().getService( JtaPlatform.class );
			log.tracef( "DdlTransactionIsolatorJtaImpl#prepare: JtaPlatform -> %s", jtaPlatform );

			final TransactionManager tm = jtaPlatform.retrieveTransactionManager();
			if ( tm == null ) {
				throw new HibernateException(
						"DdlTransactionIsolatorJtaImpl could not locate TransactionManager to suspend any current transaction; " +
								"base JtaPlatform impl (" + jtaPlatform.toString() + ")?"
				);
			}
			log.tracef( "DdlTransactionIsolatorJtaImpl#prepare: TransactionManager -> %s", tm );

			this.suspendedTransaction = tm.suspend();
			log.tracef( "DdlTransactionIsolatorJtaImpl#prepare: suspended Transaction -> %s", this.suspendedTransaction );
		}
		catch (SystemException e) {
			throw new HibernateException( "Unable to suspend current JTA transaction in preparation for DDL execution" );
		}

		try {
			this.jdbcConnection = jdbcContext.getJdbcConnectionAccess().obtainConnection();
		}
		catch (SQLException e) {
			throw jdbcContext.getSqlExceptionHelper().convert( e, "Unable to open JDBC Connection for DDL execution" );
		}

		try {
			jdbcConnection.setAutoCommit( true );
		}
		catch (SQLException e) {
			throw jdbcContext.getSqlExceptionHelper().convert( e, "Unable set JDBC Connection for DDL execution to autocommit" );
		}
	}

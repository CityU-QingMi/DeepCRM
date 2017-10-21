	@Override
	public void accept(String command) {
		ddlTransactionIsolator.getJdbcContext().getSqlStatementLogger().logStatement(
				command,
				DDLFormatterImpl.INSTANCE
		);

		try {
			final Statement jdbcStatement = jdbcStatement();
			jdbcStatement.execute( command );

			try {
				SQLWarning warnings = jdbcStatement.getWarnings();
				if ( warnings != null) {
					ddlTransactionIsolator.getJdbcContext().getSqlExceptionHelper().logAndClearWarnings( jdbcStatement );
				}
			}
			catch( SQLException e ) {
				log.unableToLogSqlWarnings( e );
			}
		}
		catch (SQLException e) {
			throw new CommandAcceptanceException(
					"Error executing DDL via JDBC Statement",
					e
			);
		}
	}

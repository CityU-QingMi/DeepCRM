	private Statement jdbcStatement() {
		if ( jdbcStatement == null ) {
			try {
				this.jdbcStatement = ddlTransactionIsolator.getIsolatedConnection().createStatement();
			}
			catch (SQLException e) {
				throw ddlTransactionIsolator.getJdbcContext().getSqlExceptionHelper().convert( e, "Unable to create JDBC Statement for DDL execution" );
			}
		}

		return jdbcStatement;
	}

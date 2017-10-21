	@Override
	public Connection getIsolatedConnection() {
		if ( jdbcConnection == null ) {
			try {
				this.jdbcConnection = jdbcContext.getJdbcConnectionAccess().obtainConnection();

				try {
					if ( !jdbcConnection.getAutoCommit() ) {
						ConnectionAccessLogger.INSTANCE.informConnectionLocalTransactionForNonJtaDdl( jdbcContext.getJdbcConnectionAccess() );

						try {
							jdbcConnection.commit();
							jdbcConnection.setAutoCommit( true );
						}
						catch (SQLException e) {
							throw jdbcContext.getSqlExceptionHelper().convert(
									e,
									"Unable to set JDBC Connection into auto-commit mode in preparation for DDL execution"
							);
						}
					}
				}
				catch (SQLException e) {
					throw jdbcContext.getSqlExceptionHelper().convert(
							e,
							"Unable to check JDBC Connection auto-commit in preparation for DDL execution"
					);
				}
			}
			catch (SQLException e) {
				throw jdbcContext.getSqlExceptionHelper().convert(
						e,
						"Unable to open JDBC Connection for DDL execution"
				);
			}
		}

		return jdbcConnection;
	}

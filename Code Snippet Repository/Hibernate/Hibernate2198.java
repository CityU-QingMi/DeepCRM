		public PreparedStatement prepareStatement() {
			try {
				getJdbcService().getSqlStatementLogger().logStatement( sql );

				final PreparedStatement preparedStatement;
				try {
					jdbcCoordinator.getJdbcSessionOwner().getJdbcSessionContext().getObserver().jdbcPrepareStatementStart();
					preparedStatement = doPrepare();
					setStatementTimeout( preparedStatement );
				}
				finally {
					jdbcCoordinator.getJdbcSessionOwner().getJdbcSessionContext().getObserver().jdbcPrepareStatementEnd();
				}
				postProcess( preparedStatement );
				return preparedStatement;
			}
			catch ( SQLException e ) {
				throw sqlExceptionHelper().convert( e, "could not prepare statement", sql );
			}
		}

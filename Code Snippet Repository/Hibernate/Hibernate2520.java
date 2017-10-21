	@Override
	public JDBCException convert(SQLException sqlException, String message, String sql) {
		String sqlStateClassCode = JdbcExceptionHelper.extractSqlStateClassCode( sqlException );
		if ( sqlStateClassCode != null ) {
			Integer errorCode = JdbcExceptionHelper.extractErrorCode( sqlException );
			if ( INTEGRITY_VIOLATION_CATEGORIES.contains( errorCode ) ) {
				String constraintName =
						getConversionContext()
								.getViolatedConstraintNameExtracter()
								.extractConstraintName( sqlException );
				return new ConstraintViolationException( message, sqlException, sql, constraintName );
			}
			else if ( DATA_CATEGORIES.contains( sqlStateClassCode ) ) {
				return new DataException( message, sqlException, sql );
			}
		}
		return null; // allow other delegates the chance to look
	}

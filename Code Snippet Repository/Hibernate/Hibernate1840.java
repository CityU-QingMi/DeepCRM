	@Override
	public SQLExceptionConversionDelegate buildSQLExceptionConversionDelegate() {
		return new SQLExceptionConversionDelegate() {
			@Override
			public JDBCException convert(SQLException sqlException, String message, String sql) {
				final String sqlState = JdbcExceptionHelper.extractSqlState( sqlException );
				final int errorCode = JdbcExceptionHelper.extractErrorCode( sqlException );
				if("JZ0TO".equals( sqlState ) || "JZ006".equals( sqlState )){
					throw new LockTimeoutException( message, sqlException, sql );
				}
				if ( 515 == errorCode && "ZZZZZ".equals( sqlState ) ) {
					// Attempt to insert NULL value into column; column does not allow nulls.
					final String constraintName = getViolatedConstraintNameExtracter().extractConstraintName( sqlException );
					return new ConstraintViolationException( message, sqlException, sql, constraintName );
				}
				return null;
			}
		};
	}

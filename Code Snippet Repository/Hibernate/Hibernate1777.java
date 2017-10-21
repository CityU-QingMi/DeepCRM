	protected void registerDateTimeFunctions() {
		registerFunction( "current_time", new NoArgSQLFunction( "current_time", StandardBasicTypes.TIME, false ) );
		registerFunction(
				"current_timestamp", new NoArgSQLFunction(
				"current_timestamp",
				StandardBasicTypes.TIMESTAMP,
				false
		)
		);
		registerFunction( "current_date", new NoArgSQLFunction( "current_date", StandardBasicTypes.DATE, false ) );
	}

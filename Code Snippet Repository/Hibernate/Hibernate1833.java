	public SQLServer2008Dialect() {
		registerColumnType( Types.DATE, "date" );
		registerColumnType( Types.TIME, "time" );
		registerColumnType( Types.TIMESTAMP, "datetime2" );
		registerColumnType( Types.NVARCHAR, NVARCHAR_MAX_LENGTH, "nvarchar($l)" );
		registerColumnType( Types.NVARCHAR, "nvarchar(MAX)" );

		registerFunction(
				"current_timestamp", new NoArgSQLFunction( "current_timestamp", StandardBasicTypes.TIMESTAMP, false )
		);
	}

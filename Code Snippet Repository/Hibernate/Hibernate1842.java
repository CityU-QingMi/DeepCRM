	public Teradata14Dialect() {
		super();
		//registerColumnType data types
		registerColumnType( Types.BIGINT, "BIGINT" );
		registerColumnType( Types.BINARY, "VARBYTE(100)" );
		registerColumnType( Types.LONGVARBINARY, "VARBYTE(32000)" );
		registerColumnType( Types.LONGVARCHAR, "VARCHAR(32000)" );

		getDefaultProperties().setProperty( Environment.USE_STREAMS_FOR_BINARY, "true" );
		getDefaultProperties().setProperty( Environment.STATEMENT_BATCH_SIZE, DEFAULT_BATCH_SIZE );

		registerFunction( "current_time", new SQLFunctionTemplate( StandardBasicTypes.TIME, "current_time" ) );
		registerFunction( "current_date", new SQLFunctionTemplate( StandardBasicTypes.DATE, "current_date" ) );

		TeraIndexExporter =  new TeradataIndexExporter( this );
	}

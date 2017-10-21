	public InformixDialect() {
		super();

		registerColumnType( Types.BIGINT, "int8" );
		registerColumnType( Types.BINARY, "byte" );
		// Informix doesn't have a bit type
		registerColumnType( Types.BIT, "smallint" );
		registerColumnType( Types.CHAR, "char($l)" );
		registerColumnType( Types.DATE, "date" );
		registerColumnType( Types.DECIMAL, "decimal" );
		registerColumnType( Types.DOUBLE, "float" );
		registerColumnType( Types.FLOAT, "smallfloat" );
		registerColumnType( Types.INTEGER, "integer" );
		// or BYTE
		registerColumnType( Types.LONGVARBINARY, "blob" );
		// or TEXT?
		registerColumnType( Types.LONGVARCHAR, "clob" );
		// or MONEY
		registerColumnType( Types.NUMERIC, "decimal" );
		registerColumnType( Types.REAL, "smallfloat" );
		registerColumnType( Types.SMALLINT, "smallint" );
		registerColumnType( Types.TIMESTAMP, "datetime year to fraction(5)" );
		registerColumnType( Types.TIME, "datetime hour to second" );
		registerColumnType( Types.TINYINT, "smallint" );
		registerColumnType( Types.VARBINARY, "byte" );
		registerColumnType( Types.VARCHAR, "varchar($l)" );
		registerColumnType( Types.VARCHAR, 255, "varchar($l)" );
		registerColumnType( Types.VARCHAR, 32739, "lvarchar($l)" );

		registerFunction( "concat", new VarArgsSQLFunction( StandardBasicTypes.STRING, "(", "||", ")" ) );
		registerFunction( "substring", new SQLFunctionTemplate(StandardBasicTypes.STRING, "substring(?1 FROM ?2 FOR ?3)"));
		registerFunction( "substr", new SQLFunctionTemplate( StandardBasicTypes.STRING, "substr(?1, ?2, ?3)"));
		registerFunction( "coalesce", new NvlFunction());
		registerFunction( "nvl", new NvlFunction());
		registerFunction( "current_timestamp", new NoArgSQLFunction( "current", StandardBasicTypes.TIMESTAMP, false ) );
		registerFunction( "current_date", new NoArgSQLFunction( "today", StandardBasicTypes.DATE, false ) );

		uniqueDelegate = new InformixUniqueDelegate( this );
	}

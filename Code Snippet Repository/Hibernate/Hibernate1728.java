	@SuppressWarnings("")
	public DerbyDialect() {
		super();
		if ( this.getClass() == DerbyDialect.class ) {
			LOG.deprecatedDerbyDialect();
		}

		registerFunction( "concat", new DerbyConcatFunction() );
		registerFunction( "trim", new AnsiTrimFunction() );
		registerColumnType( Types.BLOB, "blob" );
		registerDerbyKeywords();
		determineDriverVersion();

		if ( driverVersionMajor > 10 || ( driverVersionMajor == 10 && driverVersionMinor >= 7 ) ) {
			registerColumnType( Types.BOOLEAN, "boolean" );
		}

		this.limitHandler = new DerbyLimitHandler();
	}

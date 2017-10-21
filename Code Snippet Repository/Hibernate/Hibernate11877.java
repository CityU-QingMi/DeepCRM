	public OracleSpatial10gDialect() {
		super();

		// register geometry type
		registerColumnType( Types.STRUCT, "MDSYS.SDO_GEOMETRY" );
		for ( Map.Entry<String, SQLFunction> entry : sdoSupport.functionsToRegister() ) {
			registerFunction( entry.getKey(), entry.getValue() );
		}

	}

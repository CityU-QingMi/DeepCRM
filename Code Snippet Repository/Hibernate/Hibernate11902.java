	public PostgisPG91Dialect() {
		super();
		registerColumnType(
				PGGeometryTypeDescriptor.INSTANCE.getSqlType(),
				"GEOMETRY"
		);
		for ( Map.Entry<String, SQLFunction> entry : support.functionsToRegister() ) {
			registerFunction( entry.getKey(), entry.getValue() );
		}
	}

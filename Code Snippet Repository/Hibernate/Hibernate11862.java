	public MySQL56SpatialDialect() {
		super();
		registerColumnType(
				MySQLGeometryTypeDescriptor.INSTANCE.getSqlType(),
				"GEOMETRY"
		);
		final MySQLSpatialFunctions functionsToRegister = overrideObjectShapeFunctions( new MySQLSpatialFunctions() );
		for ( Map.Entry<String, SQLFunction> entry : functionsToRegister ) {
			registerFunction( entry.getKey(), entry.getValue() );
		}
	}

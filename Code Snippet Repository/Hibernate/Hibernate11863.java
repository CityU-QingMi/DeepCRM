	private MySQLSpatialFunctions overrideObjectShapeFunctions(MySQLSpatialFunctions mysqlFunctions) {
		mysqlFunctions.put( "contains", new StandardSQLFunction( "ST_Contains", StandardBasicTypes.BOOLEAN ) );
		mysqlFunctions.put( "crosses", new StandardSQLFunction( "ST_Crosses", StandardBasicTypes.BOOLEAN ) );
		mysqlFunctions.put( "disjoint", new StandardSQLFunction( "ST_Disjoint", StandardBasicTypes.BOOLEAN ) );
		mysqlFunctions.put( "equals", new StandardSQLFunction( "ST_Equals", StandardBasicTypes.BOOLEAN ) );
		mysqlFunctions.put( "intersects", new StandardSQLFunction( "ST_Intersects", StandardBasicTypes.BOOLEAN ) );
		mysqlFunctions.put( "overlaps", new StandardSQLFunction( "ST_Overlaps", StandardBasicTypes.BOOLEAN ) );
		mysqlFunctions.put( "touches", new StandardSQLFunction( "ST_Touches", StandardBasicTypes.BOOLEAN ) );
		mysqlFunctions.put( "within", new StandardSQLFunction( "ST_Within", StandardBasicTypes.BOOLEAN ) );
		return mysqlFunctions;
	}

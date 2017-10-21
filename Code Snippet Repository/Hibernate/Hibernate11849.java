	public GeoDBDialect() {
		super();

		// Register Geometry column type
		registerColumnType( GeoDBGeometryTypeDescriptor.INSTANCE.getSqlType(), "GEOMETRY" );

		// Register functions that operate on spatial types
		registerFunction( "dimension", new StandardSQLFunction( "ST_Dimension", StandardBasicTypes.INTEGER ) );
		registerFunction( "geometrytype", new StandardSQLFunction( "GeometryType", StandardBasicTypes.STRING ) );
		registerFunction( "srid", new StandardSQLFunction( "ST_SRID", StandardBasicTypes.INTEGER ) );
		registerFunction( "envelope", new StandardSQLFunction( "ST_Envelope" ) );
		registerFunction( "astext", new StandardSQLFunction( "ST_AsText", StandardBasicTypes.STRING ) );
		registerFunction( "asbinary", new StandardSQLFunction( "ST_AsEWKB", StandardBasicTypes.BINARY ) );
		registerFunction( "isempty", new StandardSQLFunction( "ST_IsEmpty", StandardBasicTypes.BOOLEAN ) );
		registerFunction( "issimple", new StandardSQLFunction( "ST_IsSimple", StandardBasicTypes.BOOLEAN ) );
		registerFunction( "boundary", new StandardSQLFunction( "ST_Boundary" ) );

		// Register functions for spatial relation constructs
		registerFunction( "overlaps", new StandardSQLFunction( "ST_Overlaps", StandardBasicTypes.BOOLEAN ) );
		registerFunction( "intersects", new StandardSQLFunction( "ST_Intersects", StandardBasicTypes.BOOLEAN ) );
		registerFunction( "equals", new StandardSQLFunction( "ST_Equals", StandardBasicTypes.BOOLEAN ) );
		registerFunction( "contains", new StandardSQLFunction( "ST_Contains", StandardBasicTypes.BOOLEAN ) );
		registerFunction( "crosses", new StandardSQLFunction( "ST_Crosses", StandardBasicTypes.BOOLEAN ) );
		registerFunction( "disjoint", new StandardSQLFunction( "ST_Disjoint", StandardBasicTypes.BOOLEAN ) );
		registerFunction( "touches", new StandardSQLFunction( "ST_Touches", StandardBasicTypes.BOOLEAN ) );
		registerFunction( "within", new StandardSQLFunction( "ST_Within", StandardBasicTypes.BOOLEAN ) );
		registerFunction( "relate", new StandardSQLFunction( "ST_Relate", StandardBasicTypes.BOOLEAN ) );
		// register the spatial analysis functions
		registerFunction( "distance", new StandardSQLFunction( "ST_Distance", StandardBasicTypes.DOUBLE ) );
		registerFunction( "buffer", new StandardSQLFunction( "ST_Buffer" ) );
		registerFunction( "convexhull", new StandardSQLFunction( "ST_ConvexHull" ) );
		registerFunction( "difference", new StandardSQLFunction( "ST_Difference" ) );
		registerFunction( "intersection", new StandardSQLFunction( "ST_Intersection" ) );
		registerFunction( "symdifference", new StandardSQLFunction( "ST_SymDifference" ) );
		registerFunction( "geomunion", new StandardSQLFunction( "ST_Union" ) );

		registerFunction( "dwithin", new StandardSQLFunction( "ST_DWithin", StandardBasicTypes.BOOLEAN ) );

	}

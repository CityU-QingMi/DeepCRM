	private static Class<? extends TestSupport> getSupportFactoryClass(Dialect dialect) {
		String canonicalName = dialect.getClass().getCanonicalName();

		if ( (dialect instanceof SpatialDialect) && PostgreSQL82Dialect.class.isAssignableFrom( dialect.getClass() ) ) {
			//this test works because all postgis dialects ultimately derive of the Postgresql82Dialect
			return PostgisTestSupport.class;
		}
		if ( "org.hibernate.spatial.dialect.h2geodb.GeoDBDialect".equals( canonicalName ) ) {
			return GeoDBTestSupport.class;
		}
		if ( "org.hibernate.spatial.dialect.sqlserver.SqlServer2008SpatialDialect".equals( canonicalName ) ) {
			return SQLServerTestSupport.class;
		}
		if ( "org.hibernate.spatial.dialect.mysql.MySQLSpatialDialect".equals( canonicalName ) ||
				"org.hibernate.spatial.dialect.mysql.MySQL5InnoDBSpatialDialect".equals( canonicalName ) ) {
			return MySQLTestSupport.class;
		}
		if ( "org.hibernate.spatial.dialect.mysql.MySQL56SpatialDialect".equals( canonicalName ) ||
				"org.hibernate.spatial.dialect.mysql.MySQL56InnoDBSpatialDialect".equals( canonicalName ) ) {
			return MySQL56TestSupport.class;
		}
		if ( "org.hibernate.spatial.dialect.oracle.OracleSpatial10gDialect".equals( canonicalName ) ||
				"org.hibernate.spatial.dialect.oracle.OracleSpatialSDO10gDialect".equals( canonicalName )) {
			return OracleSDOTestSupport.class;
		}
		if ( "org.hibernate.spatial.dialect.hana.HANASpatialDialect".equals( canonicalName ) ) {
			return HANATestSupport.class;
		}
		throw new IllegalArgumentException( "Dialect not known in test suite" );
	}

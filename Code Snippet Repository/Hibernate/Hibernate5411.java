	@Test
	public void testPreregisteredDialects() {
		DialectResolver resolver = StandardDialectResolver.INSTANCE;
		testDetermination( "HSQL Database Engine", HSQLDialect.class, resolver );
		testDetermination( "H2", H2Dialect.class, resolver );
		testDetermination( "MySQL", MySQLDialect.class, resolver );
		testDetermination( "MySQL", 5, 0, MySQL5Dialect.class, resolver );
		testDetermination( "PostgreSQL", PostgreSQL81Dialect.class, resolver );
		testDetermination( "PostgreSQL", 8, 2, PostgreSQL82Dialect.class, resolver );
		testDetermination( "PostgreSQL", 9, 0, PostgreSQL9Dialect.class, resolver );
		testDetermination( "EnterpriseDB", 9, 2, PostgresPlusDialect.class, resolver );
		testDetermination( "Apache Derby", 10, 4, DerbyDialect.class, resolver );
		testDetermination( "Apache Derby", 10, 5, DerbyTenFiveDialect.class, resolver );
		testDetermination( "Apache Derby", 10, 6, DerbyTenSixDialect.class, resolver );
		testDetermination( "Apache Derby", 11, 5, DerbyTenSevenDialect.class, resolver );
		testDetermination( "Ingres", IngresDialect.class, resolver );
		testDetermination( "ingres", IngresDialect.class, resolver );
		testDetermination( "INGRES", IngresDialect.class, resolver );
		testDetermination( "Microsoft SQL Server Database", SQLServerDialect.class, resolver );
		testDetermination( "Microsoft SQL Server", SQLServerDialect.class, resolver );
		testDetermination( "Sybase SQL Server", SybaseASE15Dialect.class, resolver );
		testDetermination( "Adaptive Server Enterprise", SybaseASE15Dialect.class, resolver );
		testDetermination( "Adaptive Server Anywhere", SybaseAnywhereDialect.class, resolver );
		testDetermination( "Informix Dynamic Server", InformixDialect.class, resolver );
		testDetermination( "DB2/NT", DB2Dialect.class, resolver );
		testDetermination( "DB2/LINUX", DB2Dialect.class, resolver );
		testDetermination( "DB2/6000", DB2Dialect.class, resolver );
		testDetermination( "DB2/HPUX", DB2Dialect.class, resolver );
		testDetermination( "DB2/SUN", DB2Dialect.class, resolver );
		testDetermination( "DB2/LINUX390", DB2Dialect.class, resolver );
		testDetermination( "DB2/AIX64", DB2Dialect.class, resolver );
		testDetermination( "DB2 UDB for AS/400", DB2400Dialect.class, resolver );
		testDetermination( "Oracle", 8, Oracle8iDialect.class, resolver );
		testDetermination( "Oracle", 9, Oracle9iDialect.class, resolver );
		testDetermination( "Oracle", 10, Oracle10gDialect.class, resolver );
		testDetermination( "Oracle", 11, Oracle10gDialect.class, resolver );
	}

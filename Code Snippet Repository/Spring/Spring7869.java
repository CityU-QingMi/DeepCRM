	@Nullable
	protected Class<?> determineDatabaseDialectClass(Database database) {
		switch (database) {
			case DB2: return DB2Dialect.class;
			case DERBY: return DerbyTenSevenDialect.class;
			case H2: return H2Dialect.class;
			case HSQL: return HSQLDialect.class;
			case INFORMIX: return InformixDialect.class;
			case MYSQL: return MySQL5Dialect.class;
			case ORACLE: return Oracle12cDialect.class;
			case POSTGRESQL: return PostgreSQL95Dialect.class;
			case SQL_SERVER: return SQLServer2012Dialect.class;
			case SYBASE: return SybaseDialect.class;
			default: return null;
		}
	}

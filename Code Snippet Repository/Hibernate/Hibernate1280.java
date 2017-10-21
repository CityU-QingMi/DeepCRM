	protected void reset() {
		implicitNamingStrategy = ImplicitNamingStrategyJpaCompliantImpl.INSTANCE;
		physicalNamingStrategy = PhysicalNamingStrategyStandardImpl.INSTANCE;
		namedQueries = new HashMap<String,NamedQueryDefinition>();
		namedSqlQueries = new HashMap<String,NamedSQLQueryDefinition>();
		sqlResultSetMappings = new HashMap<String, ResultSetMappingDefinition>();
		namedEntityGraphMap = new HashMap<String, NamedEntityGraphDefinition>();
		namedProcedureCallMap = new HashMap<String, NamedProcedureCallDefinition>(  );

		standardServiceRegistryBuilder = new StandardServiceRegistryBuilder( bootstrapServiceRegistry );
		entityTuplizerFactory = new EntityTuplizerFactory();
		interceptor = EmptyInterceptor.INSTANCE;
		properties = new Properties(  );
		properties.putAll( standardServiceRegistryBuilder.getSettings());
	}

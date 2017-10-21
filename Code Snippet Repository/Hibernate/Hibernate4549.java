	public NamedQueryRepository(
			Iterable<NamedQueryDefinition> namedQueryDefinitions,
			Iterable<NamedSQLQueryDefinition> namedSqlQueryDefinitions,
			Iterable<ResultSetMappingDefinition> namedSqlResultSetMappings,
			Map<String, ProcedureCallMemento> namedProcedureCalls) {
		final HashMap<String, NamedQueryDefinition> namedQueryDefinitionMap = new HashMap<String, NamedQueryDefinition>();
		for ( NamedQueryDefinition namedQueryDefinition : namedQueryDefinitions ) {
			namedQueryDefinitionMap.put( namedQueryDefinition.getName(), namedQueryDefinition );
		}
		this.namedQueryDefinitionMap = Collections.unmodifiableMap( namedQueryDefinitionMap );


		final HashMap<String, NamedSQLQueryDefinition> namedSqlQueryDefinitionMap = new HashMap<String, NamedSQLQueryDefinition>();
		for ( NamedSQLQueryDefinition namedSqlQueryDefinition : namedSqlQueryDefinitions ) {
			namedSqlQueryDefinitionMap.put( namedSqlQueryDefinition.getName(), namedSqlQueryDefinition );
		}
		this.namedSqlQueryDefinitionMap = Collections.unmodifiableMap( namedSqlQueryDefinitionMap );

		final HashMap<String, ResultSetMappingDefinition> namedSqlResultSetMappingMap = new HashMap<String, ResultSetMappingDefinition>();
		for ( ResultSetMappingDefinition resultSetMappingDefinition : namedSqlResultSetMappings ) {
			namedSqlResultSetMappingMap.put( resultSetMappingDefinition.getName(), resultSetMappingDefinition );
		}
		this.namedSqlResultSetMappingMap = Collections.unmodifiableMap( namedSqlResultSetMappingMap );
		this.procedureCallMementoMap = Collections.unmodifiableMap( namedProcedureCalls );
	}

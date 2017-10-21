	public NamedQueryRepository(
			Map<String,NamedQueryDefinition> namedQueryDefinitionMap,
			Map<String,NamedSQLQueryDefinition> namedSqlQueryDefinitionMap,
			Map<String,ResultSetMappingDefinition> namedSqlResultSetMappingMap,
			Map<String, ProcedureCallMemento> namedProcedureCallMap) {
		this.namedQueryDefinitionMap = Collections.unmodifiableMap( namedQueryDefinitionMap );
		this.namedSqlQueryDefinitionMap = Collections.unmodifiableMap( namedSqlQueryDefinitionMap );
		this.namedSqlResultSetMappingMap = Collections.unmodifiableMap( namedSqlResultSetMappingMap );
		this.procedureCallMementoMap = Collections.unmodifiableMap( namedProcedureCallMap );
	}

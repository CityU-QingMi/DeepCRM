	public void addToQuery(
			EnversService enversService,
			AuditReaderImplementor versionsReader,
			Map<String, String> aliasToEntityNameMap,
			String alias,
			QueryBuilder qb,
			Parameters parameters) {
		Parameters opParameters = parameters.addSubParameters( op );

		lhs.addToQuery( enversService, versionsReader, aliasToEntityNameMap, alias, qb, opParameters.addSubParameters( "and" ) );
		rhs.addToQuery( enversService, versionsReader, aliasToEntityNameMap, alias, qb, opParameters.addSubParameters( "and" ) );
	}

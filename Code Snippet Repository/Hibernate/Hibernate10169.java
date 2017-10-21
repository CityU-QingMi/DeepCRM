	@Override
	protected void addToQuery(
			EnversService enversService,
			AuditReaderImplementor versionsReader,
			String entityName,
			String alias,
			QueryBuilder qb,
			Parameters parameters) {
		String propertyName = CriteriaTools.determinePropertyName(
				enversService,
				versionsReader,
				entityName,
				propertyNameGetter
		);
		CriteriaTools.checkPropertyNotARelation( enversService, entityName, propertyName );

		Parameters subParams = parameters.addSubParameters( Parameters.AND );
		subParams.addWhereWithParam( alias, propertyName, ">=", lo );
		subParams.addWhereWithParam( alias, propertyName, "<=", hi );
	}

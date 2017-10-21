	@Override
	protected void addToQuery(
			EnversService enversService,
			AuditReaderImplementor versionsReader, String entityName,
			String alias, QueryBuilder qb, Parameters parameters) {

		String propertyName = CriteriaTools.determinePropertyName(
				enversService,
				versionsReader,
				entityName,
				propertyNameGetter
		);
		CriteriaTools.checkPropertyNotARelation( enversService, entityName, propertyName );

		parameters.addWhereWithFunction( alias, propertyName, " lower ", " like ", value.toLowerCase( Locale.ROOT ) );
	}

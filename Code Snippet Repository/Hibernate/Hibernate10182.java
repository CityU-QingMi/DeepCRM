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

		RelationDescription relatedEntity = CriteriaTools.getRelatedEntity( enversService, entityName, propertyName );
		if ( relatedEntity == null ) {
			throw new AuditException(
					"This criterion can only be used on a property that is a relation to another property."
			);
		}
		relatedEntity.getIdMapper().addIdEqualsToQuery( parameters, id, alias, null, equals );
	}

	@Override
	public void addToQuery(
			EnversService enversService,
			AuditReaderImplementor versionsReader,
			Map<String, String> aliasToEntityNameMap,
			String baseAlias,
			QueryBuilder qb,
			Parameters parameters) {
		String effectiveAlias = alias == null ? baseAlias : alias;
		String effectiveOtherAlias = otherAlias == null ? baseAlias : otherAlias;
		String entityName = aliasToEntityNameMap.get( effectiveAlias );
		String otherEntityName = aliasToEntityNameMap.get( effectiveOtherAlias );
		String propertyName = CriteriaTools.determinePropertyName(
				enversService,
				versionsReader,
				entityName,
				propertyNameGetter
		);
		CriteriaTools.checkPropertyNotARelation( enversService, entityName, propertyName );
/**/
/**/
/**/
/**/
/**/
		if ( enversService.getEntitiesConfigurations().isVersioned( otherEntityName ) ) {
			CriteriaTools.checkPropertyNotARelation( enversService, otherEntityName, otherPropertyName );
		}
		parameters.addWhere( effectiveAlias, propertyName, op, effectiveOtherAlias, otherPropertyName );
	}

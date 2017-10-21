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
					"The criterion can only be used on a property that is a relation to another property."
			);
		}

		// todo: should this throw an error if qpdList is null?  is it possible?
		List<QueryParameterData> qpdList = relatedEntity.getIdMapper().mapToQueryParametersFromId( propertyName );
		if ( qpdList != null ) {
			QueryParameterData qpd = qpdList.iterator().next();
			parameters.addWhereWithParams( alias, qpd.getQueryParameterName(), "in (", ids, ")" );
		}
	}

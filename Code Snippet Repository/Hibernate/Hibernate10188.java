	public AuditQuery addProjection(AuditProjection projection) {
		AuditProjection.ProjectionData projectionData = projection.getData( enversService );
		String projectionEntityAlias = projectionData.getAlias( REFERENCED_ENTITY_ALIAS );
		String projectionEntityName = aliasToEntityNameMap.get( projectionEntityAlias );
		registerProjection( projectionEntityName, projection );
		String propertyName = CriteriaTools.determinePropertyName(
				enversService,
				versionsReader,
				projectionEntityName,
				projectionData.getPropertyName()
		);
		qb.addProjection(
				projectionData.getFunction(),
				projectionEntityAlias,
				propertyName,
				projectionData.isDistinct()
		);
		return this;
	}

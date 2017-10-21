	@Override
	public AuditAssociationQueryImpl<Q> addProjection(AuditProjection projection) {
		AuditProjection.ProjectionData projectionData = projection.getData( enversService );
		String projectionEntityAlias = projectionData.getAlias( alias );
		String projectionEntityName = aliasToEntityNameMap.get( projectionEntityAlias );
		String propertyName = CriteriaTools.determinePropertyName(
				enversService,
				auditReader,
				projectionEntityName,
				projectionData.getPropertyName()
		);
		queryBuilder.addProjection(
				projectionData.getFunction(),
				projectionEntityAlias,
				propertyName,
				projectionData.isDistinct()
		);
		registerProjection( projectionEntityName, projection );
		return this;
	}

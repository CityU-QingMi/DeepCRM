	protected void fillDataWithId(Map<String, Object> data, Object revision) {
		final AuditEntitiesConfiguration entitiesCfg = enversService.getAuditEntitiesConfiguration();

		final Map<String, Object> originalId = new HashMap<>();
		originalId.put( entitiesCfg.getRevisionFieldName(), revision );

		enversService.getEntitiesConfigurations().get( getEntityName() ).getIdMapper().mapToMapFromId( originalId, id );
		data.put( entitiesCfg.getRevisionTypePropName(), revisionType );
		data.put( entitiesCfg.getOriginalIdPropName(), originalId );
	}

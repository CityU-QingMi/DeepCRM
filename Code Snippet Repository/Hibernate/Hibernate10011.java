	protected EntityInfo getEntityInfo(EnversService enversService, String entityName) {
		EntityConfiguration entCfg = enversService.getEntitiesConfigurations().get( entityName );
		boolean isRelationAudited = true;
		if ( entCfg == null ) {
			// a relation marked as RelationTargetAuditMode.NOT_AUDITED
			entCfg = enversService.getEntitiesConfigurations().getNotVersionEntityConfiguration( entityName );
			isRelationAudited = false;
		}
		final Class entityClass = ReflectionTools.loadClass( entCfg.getEntityClassName(), enversService.getClassLoaderService() );
		return new EntityInfo( entityClass, entityName, isRelationAudited );
	}

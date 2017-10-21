	private Number getRevisionNumber(Map versionsEntity) {
		AuditEntitiesConfiguration verEntCfg = enversService.getAuditEntitiesConfiguration();

		String originalId = verEntCfg.getOriginalIdPropName();
		String revisionPropertyName = verEntCfg.getRevisionFieldName();

		Object revisionInfoObject = ( (Map) versionsEntity.get( originalId ) ).get( revisionPropertyName );

		if ( revisionInfoObject instanceof HibernateProxy ) {
			return (Number) ( (HibernateProxy) revisionInfoObject ).getHibernateLazyInitializer().getIdentifier();
		}
		else {
			// Not a proxy - must be read from cache or with a join
			return enversService.getRevisionInfoNumberReader().getRevisionNumber( revisionInfoObject );
		}
	}

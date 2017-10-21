	@Override
	public void mapToEntityFromMap(
			EnversService enversService,
			Object obj,
			Map data,
			Object primaryKey,
			AuditReaderImplementor versionsReader,
			Number revision) {
		if ( obj != null ) {
			nullSafeMapToEntityFromMap( enversService, obj, data, primaryKey, versionsReader, revision );
		}
	}

	@Override
	public void mapToEntityFromMap(
			EnversService enversService,
			Object obj,
			Map data,
			Object primaryKey,
			AuditReaderImplementor versionsReader,
			Number revision) {
		for ( PropertyMapper mapper : properties.values() ) {
			mapper.mapToEntityFromMap( enversService, obj, data, primaryKey, versionsReader, revision );
		}
	}

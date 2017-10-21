	@Override
	public void mapToEntityFromMap(
			EnversService enversService,
			Object obj,
			Map data,
			Object primaryKey,
			AuditReaderImplementor versionsReader,
			Number revision) {
		parentMapper.mapToEntityFromMap( enversService, obj, data, primaryKey, versionsReader, revision );
		main.mapToEntityFromMap( enversService, obj, data, primaryKey, versionsReader, revision );
	}

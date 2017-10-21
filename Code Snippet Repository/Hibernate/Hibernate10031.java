	public static Object createProxyOrLoadImmediate(
			AuditReaderImplementor versionsReader,
			Class<?> entityClass,
			String entityName,
			Object entityId,
			Number revision,
			boolean removed,
			EnversService enversService) {
		final EntityPersister persister = versionsReader.getSessionImplementor()
				.getFactory()
				.getMetamodel()
				.entityPersister( entityName );
		if ( persister.hasProxy() ) {
			return createProxy( versionsReader, entityClass, entityName, entityId, revision, removed, enversService );
		}
		return loadImmediate( versionsReader, entityClass, entityName, entityId, revision, removed, enversService );
	}

	public static Object loadImmediate(
			AuditReaderImplementor versionsReader,
			Class<?> entityClass,
			String entityName,
			Object entityId,
			Number revision,
			boolean removed,
			EnversService enversService) {
		if ( enversService.getEntitiesConfigurations().getNotVersionEntityConfiguration( entityName ) == null ) {
			// Audited relation, look up entity with Envers.
			// When user traverses removed entities graph, do not restrict revision type of referencing objects
			// to ADD or MOD (DEL possible). See HHH-5845.
			return versionsReader.find( entityClass, entityName, entityId, revision, removed );
		}
		else {
			// Not audited relation, look up entity with Hibernate.
			return versionsReader.getSessionImplementor().immediateLoad( entityName, (Serializable) entityId );
		}
	}

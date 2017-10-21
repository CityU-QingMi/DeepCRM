	@Override
	public Object doImmediateLoad(String entityName) throws HibernateException {
		return ToOneEntityLoader.loadImmediate(
				versionsReader,
				entityClass,
				entityName,
				entityId,
				revision,
				removed,
				enversService
		);
	}

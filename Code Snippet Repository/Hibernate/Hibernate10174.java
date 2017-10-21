	@Override
	protected void addToQuery(
			EnversService enversService,
			AuditReaderImplementor versionsReader,
			String entityName,
			String alias,
			QueryBuilder qb,
			Parameters parameters) {
		String prefix = enversService.getAuditEntitiesConfiguration().getOriginalIdPropName();
		enversService.getEntitiesConfigurations().get( entityName )
				.getIdMapper()
				.addIdEqualsToQuery( parameters, id, alias, prefix, equals );
	}

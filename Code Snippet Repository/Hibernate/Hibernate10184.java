	@Override
	protected void addToQuery(
			EnversService enversService,
			AuditReaderImplementor versionsReader,
			String entityName,
			String alias,
			QueryBuilder qb,
			Parameters parameters) {
		parameters.addWhereWithParam( alias, enversService.getAuditEntitiesConfiguration().getRevisionTypePropName(), op, value );
	}

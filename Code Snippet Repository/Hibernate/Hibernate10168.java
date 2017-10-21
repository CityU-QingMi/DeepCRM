	@Override
	public void addToQuery(
			EnversService enversService,
			AuditReaderImplementor versionsReader,
			Map<String, String> aliasToEntityNameMap,
			String baseAlias,
			QueryBuilder qb,
			Parameters parameters) {
		final String effectiveAlias = alias == null ? baseAlias : alias;
		final String entityName = aliasToEntityNameMap.get( effectiveAlias );
		addToQuery(enversService, versionsReader, entityName, effectiveAlias, qb, parameters);
	}

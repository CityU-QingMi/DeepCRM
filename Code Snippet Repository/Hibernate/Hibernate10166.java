	@Override
	public void addToQuery(
			EnversService enversService,
			AuditReaderImplementor versionsReader,
			Map<String, String> aliasToEntityNameMap,
			String alias,
			QueryBuilder qb,
			Parameters parameters) {
		Parameters andParameters = parameters.addSubParameters( Parameters.AND );

		if ( criterions.size() == 0 ) {
			andParameters.addWhere( "1", false, "=", "1", false );
		}
		else {
			for ( AuditCriterion criterion : criterions ) {
				criterion.addToQuery( enversService, versionsReader, aliasToEntityNameMap, alias, qb, andParameters );
			}
		}
	}

	@Override
	public void addToQuery(
			EnversService enversService,
			AuditReaderImplementor versionsReader,
			Map<String, String> aliasToEntityNameMap,
			String alias,
			QueryBuilder qb,
			Parameters parameters) {
		Parameters orParameters = parameters.addSubParameters( Parameters.OR );

		if ( criterions.size() == 0 ) {
			orParameters.addWhere( "0", false, "=", "1", false );
		}
		else {
			for ( AuditCriterion criterion : criterions ) {
				criterion.addToQuery( enversService, versionsReader, aliasToEntityNameMap, alias, qb, orParameters );
			}
		}
	}

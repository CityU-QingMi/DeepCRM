	@SuppressWarnings({""})
	private void addJoins(
			PersistentClass pc,
			CompositeMapperBuilder currentMapper,
			ClassAuditingData auditingData,
			String entityName,
			EntityXmlMappingData xmlMappingData,
			boolean firstPass) {
		final Iterator<Join> joins = pc.getJoinIterator();

		while ( joins.hasNext() ) {
			final Join join = joins.next();
			final Element joinElement = entitiesJoins.get( entityName ).get( join );

			if ( joinElement != null ) {
				addProperties(
						joinElement,
						join.getPropertyIterator(),
						currentMapper,
						auditingData,
						entityName,
						xmlMappingData,
						firstPass
				);
			}
		}
	}

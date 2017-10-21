	public static NativeSQLQueryRootReturn extractReturnDescription(
			JaxbHbmNativeQueryReturnType rtnSource,
			HbmLocalMetadataBuildingContext context,
			int queryReturnPosition) {
		String alias = rtnSource.getAlias();
		if ( StringHelper.isEmpty( alias ) ) {
			// hack-around as sqlquery impl depend on having a key.
			alias = "alias_" + queryReturnPosition;
		}
		final String entityName = context.determineEntityName(
				rtnSource.getEntityName(),
				rtnSource.getClazz()
		);
		final PersistentClass pc = context.getMetadataCollector().getEntityBinding( entityName );

		return new NativeSQLQueryRootReturn(
				alias,
				entityName,
				extractPropertyResults( alias, rtnSource, pc, context ),
				rtnSource.getLockMode()
		);
	}

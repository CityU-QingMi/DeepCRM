	public static Table buildAndFillTable(
			String schema,
			String catalog,
			ObjectNameSource nameSource,
			NamingStrategyHelper namingStrategyHelper,
			boolean isAbstract,
			List<UniqueConstraintHolder> uniqueConstraints,
			List<JPAIndexHolder> jpaIndexHolders,
			String constraints,
			MetadataBuildingContext buildingContext,
			String subselect,
			InFlightMetadataCollector.EntityTableXref denormalizedSuperTableXref) {
		final Identifier logicalName;
		if ( StringHelper.isNotEmpty( nameSource.getExplicitName() ) ) {
			logicalName = namingStrategyHelper.handleExplicitName( nameSource.getExplicitName(), buildingContext );
		}
		else {
			logicalName = namingStrategyHelper.determineImplicitName( buildingContext );
		}

		return buildAndFillTable(
				schema,
				catalog,
				logicalName,
				isAbstract,
				uniqueConstraints,
				jpaIndexHolders,
				constraints,
				buildingContext,
				subselect,
				denormalizedSuperTableXref
		);
	}

	public Identifier determineLogicalName(String explicitName, NamingStrategyHelper namingStrategyHelper) {
		Identifier logicalName;
		if ( StringHelper.isEmpty( explicitName ) ) {
			logicalName = namingStrategyHelper.determineImplicitName( getBuildingContext() );
		}
		else {
			logicalName = namingStrategyHelper.handleExplicitName( explicitName, getBuildingContext() );
		}
		logicalName = getBuildingContext().getMetadataCollector()
				.getDatabase()
				.getJdbcEnvironment()
				.getIdentifierHelper()
				.normalizeQuoting( logicalName );

		return logicalName;
	}

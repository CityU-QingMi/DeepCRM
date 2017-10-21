	@Override
	public void redefineColumnName(String columnName, String propertyName, boolean applyNamingStrategy) {
		if ( StringHelper.isNotEmpty( columnName ) ) {
			if ( applyNamingStrategy ) {
				getMappingColumn().setName(
						getBuildingContext().getBuildingOptions().getPhysicalNamingStrategy().toPhysicalColumnName(
								getBuildingContext().getMetadataCollector().getDatabase().toIdentifier( columnName ),
								getBuildingContext().getMetadataCollector().getDatabase().getJdbcEnvironment()
						).render()
				);
			}
			else {
				getMappingColumn().setName( columnName );
			}
		}
	}

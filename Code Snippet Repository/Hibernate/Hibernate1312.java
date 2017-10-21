	public static Ejb3JoinColumn[] buildJoinColumns(
			JoinColumn[] anns,
			String mappedBy,
			Map<String, Join> joins,
			PropertyHolder propertyHolder,
			String propertyName,
			MetadataBuildingContext buildingContext) {
		return buildJoinColumnsWithDefaultColumnSuffix(
				anns, mappedBy, joins, propertyHolder, propertyName, "", buildingContext
		);
	}

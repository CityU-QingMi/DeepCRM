	public static Ejb3JoinColumn[] buildJoinColumnsWithDefaultColumnSuffix(
			JoinColumn[] anns,
			String mappedBy,
			Map<String, Join> joins,
			PropertyHolder propertyHolder,
			String propertyName,
			String suffixForDefaultColumnName,
			MetadataBuildingContext buildingContext) {
		JoinColumn[] actualColumns = propertyHolder.getOverriddenJoinColumn(
				StringHelper.qualify( propertyHolder.getPath(), propertyName )
		);
		if ( actualColumns == null ) actualColumns = anns;
		if ( actualColumns == null || actualColumns.length == 0 ) {
			return new Ejb3JoinColumn[] {
					buildJoinColumn(
							null,
							mappedBy,
							joins,
							propertyHolder,
							propertyName,
							suffixForDefaultColumnName,
							buildingContext
					)
			};
		}
		else {
			int size = actualColumns.length;
			Ejb3JoinColumn[] result = new Ejb3JoinColumn[size];
			for (int index = 0; index < size; index++) {
				result[index] = buildJoinColumn(
						actualColumns[index],
						mappedBy,
						joins,
						propertyHolder,
						propertyName,
						suffixForDefaultColumnName,
						buildingContext
				);
			}
			return result;
		}
	}

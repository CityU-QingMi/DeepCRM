	@Override
	public SecondPass getSecondPass(
			final Ejb3JoinColumn[] fkJoinColumns,
			final Ejb3JoinColumn[] keyColumns,
			final Ejb3JoinColumn[] inverseColumns,
			final Ejb3Column[] elementColumns,
			Ejb3Column[] mapKeyColumns,
			final Ejb3JoinColumn[] mapKeyManyToManyColumns,
			final boolean isEmbedded,
			final XProperty property,
			final XClass collType,
			final boolean ignoreNotFound,
			final boolean unique,
			final TableBinder assocTableBinder,
			final MetadataBuildingContext buildingContext) {
		return new CollectionSecondPass( getBuildingContext(), ListBinder.this.collection ) {
			@Override
            public void secondPass(Map persistentClasses, Map inheritedMetas)
					throws MappingException {
				bindStarToManySecondPass(
						persistentClasses,
						collType,
						fkJoinColumns,
						keyColumns,
						inverseColumns,
						elementColumns,
						isEmbedded,
						property,
						unique,
						assocTableBinder,
						ignoreNotFound,
						buildingContext
				);
				bindIndex( buildingContext );
			}
		};
	}

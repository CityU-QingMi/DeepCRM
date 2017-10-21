	private static void bindCollectionSecondPass(
			Collection collValue,
			PersistentClass collectionEntity,
			Ejb3JoinColumn[] joinColumns,
			boolean cascadeDeleteEnabled,
			XProperty property,
			PropertyHolder propertyHolder,
			MetadataBuildingContext buildingContext) {
		try {
			BinderHelper.createSyntheticPropertyReference(
					joinColumns,
					collValue.getOwner(),
					collectionEntity,
					collValue,
					false,
					buildingContext
			);
		}
		catch (AnnotationException ex) {
			throw new AnnotationException( "Unable to map collection " + collValue.getOwner().getClassName() + "." + property.getName(), ex );
		}
		SimpleValue key = buildCollectionKey( collValue, joinColumns, cascadeDeleteEnabled, property, propertyHolder, buildingContext );
		if ( property.isAnnotationPresent( ElementCollection.class ) && joinColumns.length > 0 ) {
			joinColumns[0].setJPA2ElementCollection( true );
		}
		TableBinder.bindFk( collValue.getOwner(), collectionEntity, joinColumns, key, false, buildingContext );
	}

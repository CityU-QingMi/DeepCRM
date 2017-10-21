	private void bindJoinToPersistentClass(Join join, Ejb3JoinColumn[] ejb3JoinColumns, MetadataBuildingContext buildingContext) {
		SimpleValue key = new DependantValue( buildingContext.getMetadataCollector(), join.getTable(), persistentClass.getIdentifier() );
		join.setKey( key );
		setFKNameIfDefined( join );
		key.setCascadeDeleteEnabled( false );
		TableBinder.bindFk( persistentClass, null, ejb3JoinColumns, key, false, buildingContext );
		join.createPrimaryKey();
		join.createForeignKey();
		persistentClass.addJoin( join );
	}

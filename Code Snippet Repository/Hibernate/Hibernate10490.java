	private AdvancedEntity getAdvancedEntity(ManyToOneEntity manyToOne, OneToOneEntity oneToOne, ManyToManyEntity manyToManyEntity) {
		AdvancedEntity advancedEntity = new AdvancedEntity();
		advancedEntity.setId( 1L );
		advancedEntity.setNote( "Test note" );
		advancedEntity.getDynamicConfiguration().put( PROP_BOOLEAN, true );
		advancedEntity.getDynamicConfiguration().put( PROP_INT, 19 );
		advancedEntity.getDynamicConfiguration().put( PROP_FLOAT, 15.9f );
		advancedEntity.getDynamicConfiguration().put( PROP_MANY_TO_ONE, manyToOne );
		advancedEntity.getDynamicConfiguration().put( PROP_ONE_TO_ONE, oneToOne );
		advancedEntity.getDynamicConfiguration().put( INTERNAL_COMPONENT, new InternalComponent( "Internal value" ) );
		List<String> list = new ArrayList<String>();
		list.add( "One" );
		list.add( "Two" );
		list.add( "Three" );
		advancedEntity.getDynamicConfiguration().put( INTERNAL_LIST, list );
		Map<String, String> map = new HashMap<String, String>();
		map.put( "one", "1" );
		map.put( "two", "2" );
		advancedEntity.getDynamicConfiguration().put( INTERNAL_MAP, map );
		Map<String, ManyToManyEntity> mapWithManyToMany = new HashMap<String, ManyToManyEntity>();
		mapWithManyToMany.put( "entity1", manyToManyEntity );
		advancedEntity.getDynamicConfiguration().put( INTERNAL_MAP_WITH_MANY_TO_MANY, mapWithManyToMany );
		Set<String> set = new HashSet<String>();
		set.add( "Une" );
		set.add( "Due" );
		advancedEntity.getDynamicConfiguration().put( INTERNAL_SET, set );
		Set<InternalComponent> componentSet = new HashSet<InternalComponent>();
		componentSet.add( new InternalComponent( "Ein" ) );
		componentSet.add( new InternalComponent( "Zwei" ) );
		advancedEntity.getDynamicConfiguration().put( INTERNAL_SET_OF_COMPONENTS, componentSet );
		advancedEntity.getDynamicConfiguration().put( AGE_USER_TYPE, new Age( 18 ) );
		List<Age> ages = new ArrayList<Age>();
		ages.add( new Age( 1 ) );
		ages.add( new Age( 2 ) );
		ages.add( new Age( 3 ) );

		advancedEntity.getDynamicConfiguration().put( INTERNAL_LIST_OF_USER_TYPES, ages );
		return advancedEntity;
	}

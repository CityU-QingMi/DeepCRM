	private PlainEntity getPlainEntity(ManyToOneEntity manyToOne, ManyToManyEntity manyToMany, OneToOneEntity oneToOne) {
		PlainComponent plainComponent = new PlainComponent();
		List<ManyToManyEntity> manyToManyEntityList = new ArrayList<ManyToManyEntity>();
		manyToManyEntityList.add( manyToMany );
		plainComponent.setManyToManyList( manyToManyEntityList );
		plainComponent.setComponentNote( "Note" );
		plainComponent.setOneToOneEntity( oneToOne );
		plainComponent.setManyToOneEntity( manyToOne );
		plainComponent.setInternalComponent( new InternalComponent( "Some val" ) );
		ArrayList<InternalComponent> internalComponents = new ArrayList<InternalComponent>();
		internalComponents.add( new InternalComponent( "test" ) );
		plainComponent.setInternalComponents( internalComponents );

		PlainEntity plainEntity = new PlainEntity();
		plainEntity.setId( 1L );
		plainEntity.setNote( "Plain note" );
		plainEntity.setComponent( plainComponent );
		return plainEntity;
	}

	@Override
	public CollectionDefinition toCollectionDefinition() {
		if ( isEntityType() ) {
			throw new IllegalStateException( "Cannot treat entity attribute as collection type" );
		}
		if ( isAnyType() ) {
			throw new IllegalStateException( "Cannot treat any-type attribute as collection type" );
		}
		return (CollectionPersister) getJoinable();
	}

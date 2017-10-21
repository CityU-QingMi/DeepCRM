	@Override
	public CollectionDefinition toCollectionDefinition() {
		if ( getAssociationNature() == AssociationNature.ANY ) {
			throw new WalkingException( "Cannot treat any-type attribute as a collection type" );
		}
		if ( getAssociationNature() == AssociationNature.ENTITY ) {
			throw new IllegalStateException( "Cannot treat entity-valued attribute as collection type" );
		}
		return (QueryableCollection) getJoinable();
	}

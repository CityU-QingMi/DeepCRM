	@Override
	public EntityDefinition toEntityDefinition() {
		if ( getAssociationNature() == AssociationNature.ANY ) {
			throw new WalkingException( "Cannot treat any-type attribute as an entity type" );
		}
		if ( getAssociationNature() == AssociationNature.COLLECTION ) {
			throw new IllegalStateException( "Cannot treat collection-valued attribute as entity type" );
		}
		return (EntityPersister) getJoinable();
	}

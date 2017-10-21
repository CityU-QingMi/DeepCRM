	@Override
	public EntityDefinition toEntityDefinition() {
		if ( isCollection() ) {
			throw new IllegalStateException( "Cannot treat collection attribute as entity type" );
		}
		if ( isAnyType() ) {
			throw new IllegalStateException( "Cannot treat any-type attribute as entity type" );
		}
		return (EntityPersister) getJoinable();
	}

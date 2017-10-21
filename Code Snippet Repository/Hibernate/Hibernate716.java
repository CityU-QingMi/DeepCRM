	@Override
	public void validate() throws MappingException {
		for ( PersistentClass entityBinding : this.getEntityBindings() ) {
			entityBinding.validate( this );
		}

		for ( Collection collectionBinding : this.getCollectionBindings() ) {
			collectionBinding.validate( this );
		}
	}

	Type getIdentifierType(final Mapping factory) {
		final Type type = associatedIdentifierType;
		//The following branch implements a simple lazy-initialization, but rather than the canonical
		//form it returns the local variable to avoid a second volatile read: associatedIdentifierType
		//needs to be volatile as the initialization might happen by a different thread than the readers.
		if ( type == null ) {
			associatedIdentifierType = factory.getIdentifierType( getAssociatedEntityName() );
			return associatedIdentifierType;
		}
		else {
			return type;
		}
	}

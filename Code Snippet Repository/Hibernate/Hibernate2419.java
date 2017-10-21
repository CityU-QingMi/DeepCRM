	private boolean wrapCollections(
			EventSource session,
			EntityPersister persister,
			Type[] types,
			Object[] values
	) {
		if ( persister.hasCollections() ) {

			// wrap up any new collections directly referenced by the object
			// or its components

			// NOTE: we need to do the wrap here even if its not "dirty",
			// because collections need wrapping but changes to _them_
			// don't dirty the container. Also, for versioned data, we
			// need to wrap before calling searchForDirtyCollections

			WrapVisitor visitor = new WrapVisitor( session );
			// substitutes into values by side-effect
			visitor.processEntityPropertyValues( values, types );
			return visitor.isSubstitutionRequired();
		}
		else {
			return false;
		}
	}

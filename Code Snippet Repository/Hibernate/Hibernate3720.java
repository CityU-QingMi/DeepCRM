	private EntityIdentifierDescription buildIdentifierDescription() {
		final EntityIdentifierDefinition identifierDefinition = getEntityPersister().getEntityKeyDefinition();

		if ( identifierDefinition.isEncapsulated() ) {
			final EncapsulatedEntityIdentifierDefinition encapsulatedIdentifierDefinition = (EncapsulatedEntityIdentifierDefinition) identifierDefinition;
			final Type idAttributeType = encapsulatedIdentifierDefinition.getAttributeDefinition().getType();
			if ( ! CompositeType.class.isInstance( idAttributeType ) ) {
				return new SimpleEntityIdentifierDescriptionImpl();
			}
		}

		// if we get here, we know we have a composite identifier...
		final ExpandingCompositeQuerySpace querySpace = expandingEntityQuerySpace().makeCompositeIdentifierQuerySpace();
		return identifierDefinition.isEncapsulated()
				? buildEncapsulatedCompositeIdentifierDescription( querySpace )
				: buildNonEncapsulatedCompositeIdentifierDescription( querySpace );
	}

	@Override
	public void foundCircularAssociation(AssociationAttributeDefinition attributeDefinition) {
		final FetchStrategy fetchStrategy = determineFetchStrategy( attributeDefinition );
		if ( fetchStrategy.getStyle() != FetchStyle.JOIN ) {
			return; // nothing to do
		}

		final AssociationKey associationKey = attributeDefinition.getAssociationKey();

		// go ahead and build the bidirectional fetch
		if ( attributeDefinition.getAssociationNature() == AssociationAttributeDefinition.AssociationNature.ENTITY ) {
			final Joinable currentEntityPersister = (Joinable) currentSource().resolveEntityReference().getEntityPersister();
			final AssociationKey currentEntityReferenceAssociationKey =
					new AssociationKey( currentEntityPersister.getTableName(), currentEntityPersister.getKeyColumnNames() );
			// if associationKey is equal to currentEntityReferenceAssociationKey
			// that means that the current EntityPersister has a single primary key attribute
			// (i.e., derived attribute) which is mapped by attributeDefinition.
			// This is not a bidirectional association.
			// TODO: AFAICT, to avoid an overflow, the associated entity must already be loaded into the session, or
			// it must be loaded when the ID for the dependent entity is resolved. Is there some other way to
			// deal with this???
			final FetchSource registeredFetchSource = registeredFetchSource( associationKey );
			if ( registeredFetchSource != null && ! associationKey.equals( currentEntityReferenceAssociationKey ) ) {
				currentSource().buildBidirectionalEntityReference(
						attributeDefinition,
						fetchStrategy,
						registeredFetchSource( associationKey ).resolveEntityReference()
				);
			}
		}
		else {
			// Do nothing for collection
		}
	}

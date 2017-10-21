	private static boolean shouldVetoHandleAssociationAttributeInId(
			Return rootReturn,
			EntityIdentifierDefinition identifierDefinition) {
		// only check the identifierDefinition for a root EntityReturn.
		if ( EntityReturn.class.isInstance( rootReturn ) ) {
			final EntityIdentifierDefinition rootEntityIdentifierDefinition =
					( (EntityReturn) rootReturn ).getEntityPersister().getEntityKeyDefinition();
			if ( rootEntityIdentifierDefinition == identifierDefinition ) {
				// There are 2 cases where an association in an ID should not be "handled":
				// 1) a composite, encapsulated ID (e.g., @EmbeddedId). In this case, the ID is provided
				//    by the application by Session#get or EntityManager#find. Hibernate uses the
				//    provided ID "as is".
				// 2) a non-encapsulated ID without an @IdClass. In this case, the application provides
				//    an instance of the entity with the ID properties initialized. Hibernate uses
				//    the provided ID properties "as is".
				// In these two cases, it is important that associations in the ID not be "handled"
				// (i.e, joined); doing so can result in unexpected results.
				if ( rootEntityIdentifierDefinition.isEncapsulated() ) {
					final EncapsulatedEntityIdentifierDefinition encapsulated =
							(EncapsulatedEntityIdentifierDefinition ) rootEntityIdentifierDefinition;
					if ( encapsulated.getAttributeDefinition().getType().isComponentType() ) {
						// This is 1) (@EmbeddedId).
						return true;
					}
				}
				else {
					final NonEncapsulatedEntityIdentifierDefinition nonEncapsulated =
							(NonEncapsulatedEntityIdentifierDefinition) rootEntityIdentifierDefinition;
					if ( nonEncapsulated.getSeparateIdentifierMappingClass() == null ) {
						// This is 2) (a non-encapsulated ID without an @IdClass)
						return true;
					}
				}
			}
		}
		return false;
	}

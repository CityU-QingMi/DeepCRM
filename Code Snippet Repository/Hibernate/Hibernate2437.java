	private void checkIdClass(
			final EntityPersister persister,
			final LoadEvent event,
			final LoadEventListener.LoadType loadType,
			final Class idClass) {
				// we may have the kooky jpa requirement of allowing find-by-id where
			// "id" is the "simple pk value" of a dependent objects parent.  This
			// is part of its generally goofy "derived identity" "feature"
			if ( persister.getEntityMetamodel().getIdentifierProperty().isEmbedded() ) {
				final EmbeddedComponentType dependentIdType =
						(EmbeddedComponentType) persister.getEntityMetamodel().getIdentifierProperty().getType();
				if ( dependentIdType.getSubtypes().length == 1 ) {
					final Type singleSubType = dependentIdType.getSubtypes()[0];
					if ( singleSubType.isEntityType() ) {
						final EntityType dependentParentType = (EntityType) singleSubType;
						final Type dependentParentIdType = dependentParentType.getIdentifierOrUniqueKeyType( event.getSession().getFactory() );
						if ( dependentParentIdType.getReturnedClass().isInstance( event.getEntityId() ) ) {
							// yep that's what we have...
							loadByDerivedIdentitySimplePkValue(
									event,
									loadType,
									persister,
									dependentIdType,
									event.getSession().getFactory().getEntityPersister( dependentParentType.getAssociatedEntityName() )
							);
							return;
						}
					}
				}
			}
			throw new TypeMismatchException(
					"Provided id of the wrong type for class " + persister.getEntityName() + ". Expected: " + idClass
							+ ", got " + event.getEntityId().getClass()
			);
	}

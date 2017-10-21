	@Override
	public void finishingEntityIdentifier(EntityIdentifierDefinition entityIdentifierDefinition) {
		// only pop from stack if the current source is ExpandingEntityIdentifierDescription..
		final ExpandingFetchSource currentSource = currentSource();
		if ( ! ExpandingEntityIdentifierDescription.class.isInstance( currentSource ) ) {
			// in this case, the current source should be the entity that owns entityIdentifierDefinition
			if ( ! EntityReference.class.isInstance( currentSource ) ) {
				throw new WalkingException( "Unexpected state in FetchSource stack" );
			}
			final EntityReference entityReference = (EntityReference) currentSource;
			if ( entityReference.getEntityPersister().getEntityKeyDefinition() != entityIdentifierDefinition ) {
				throw new WalkingException(
						String.format(
								"Encountered unexpected fetch owner [%s] in stack while processing entity identifier for [%s]",
								entityReference.getEntityPersister().getEntityName(),
								entityIdentifierDefinition.getEntityDefinition().getEntityPersister().getEntityName()
						)
				);
			}
			return;
		}

		// the current source is ExpandingEntityIdentifierDescription...
		final ExpandingEntityIdentifierDescription identifierDescription =
				(ExpandingEntityIdentifierDescription) popFromStack();

		// and then on the node before it (which should be the entity that owns the identifier being described)
		final ExpandingFetchSource entitySource = currentSource();
		if ( ! EntityReference.class.isInstance( entitySource ) ) {
			throw new WalkingException( "Unexpected state in FetchSource stack" );
		}
		final EntityReference entityReference = (EntityReference) entitySource;
		if ( entityReference.getIdentifierDescription() != identifierDescription ) {
			throw new WalkingException(
					String.format(
							"Encountered unexpected fetch owner [%s] in stack while processing entity identifier for [%s]",
							entityReference.getEntityPersister().getEntityName(),
							entityIdentifierDefinition.getEntityDefinition().getEntityPersister().getEntityName()
					)
			);
		}

		log.tracef(
				"%s Finished entity identifier : %s",
				StringHelper.repeat( "<<", fetchSourceStack.size() ),
				entityIdentifierDefinition.getEntityDefinition().getEntityPersister().getEntityName()
		);
	}

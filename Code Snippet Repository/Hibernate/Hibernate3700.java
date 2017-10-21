	@Override
	public void startingEntity(EntityDefinition entityDefinition) {
		// see if the EntityDefinition is a root...
		final boolean isRoot = fetchSourceStack.isEmpty();
		if ( ! isRoot ) {
			// if not, this call should represent a fetch which should have been handled in #startingAttribute
			return;
		}

		// if we get here, it is a root

		log.tracef(
				"%s Starting root entity : %s",
				StringHelper.repeat( ">>", fetchSourceStack.size() ),
				entityDefinition.getEntityPersister().getEntityName()
		);

		if ( !supportsRootEntityReturns() ) {
			throw new HibernateException( "This strategy does not support root entity returns" );
		}

		final EntityReturnImpl entityReturn = new EntityReturnImpl( entityDefinition, querySpaces );
		addRootReturn( entityReturn );
		pushToStack( entityReturn );

		// also add an AssociationKey for the root so we can later on recognize circular references back to the root.
		final Joinable entityPersister = (Joinable) entityDefinition.getEntityPersister();
		associationKeyRegistered(
				new AssociationKey( entityPersister.getTableName(), entityPersister.getKeyColumnNames() )
		);
	}

	private void logCannotResolveNonNullableTransientDependencies(SharedSessionContractImplementor session) {
		for ( Map.Entry<Object,Set<AbstractEntityInsertAction>> entry : dependentActionsByTransientEntity.entrySet() ) {
			final Object transientEntity = entry.getKey();
			final String transientEntityName = session.guessEntityName( transientEntity );
			final Serializable transientEntityId = session.getFactory().getMetamodel().entityPersister( transientEntityName ).getIdentifier( transientEntity, session );
			final String transientEntityString = MessageHelper.infoString( transientEntityName, transientEntityId );
			final Set<String> dependentEntityStrings = new TreeSet<>();
			final Set<String> nonNullableTransientPropertyPaths = new TreeSet<>();
			for ( AbstractEntityInsertAction dependentAction : entry.getValue() ) {
				dependentEntityStrings.add( MessageHelper.infoString( dependentAction.getEntityName(), dependentAction.getId() ) );
				for ( String path : dependenciesByAction.get( dependentAction ).getNonNullableTransientPropertyPaths( transientEntity ) ) {
					final String fullPath = dependentAction.getEntityName() + '.' + path;
					nonNullableTransientPropertyPaths.add( fullPath );
				}
			}

			LOG.cannotResolveNonNullableTransientDependencies(
					transientEntityString,
					dependentEntityStrings,
					nonNullableTransientPropertyPaths
			);
		}
	}

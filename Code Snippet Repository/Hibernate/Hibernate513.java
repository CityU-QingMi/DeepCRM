	@SuppressWarnings({ "" })
	public BulkOperationCleanupAction(SharedSessionContractImplementor session, Set tableSpaces) {
		final LinkedHashSet<String> spacesList = new LinkedHashSet<>();
		spacesList.addAll( tableSpaces );

		final SessionFactoryImplementor factory = session.getFactory();
		for ( EntityPersister persister : factory.getMetamodel().entityPersisters().values() ) {
			final String[] entitySpaces = (String[]) persister.getQuerySpaces();
			if ( affectedEntity( tableSpaces, entitySpaces ) ) {
				spacesList.addAll( Arrays.asList( entitySpaces ) );

				if ( persister.hasCache() ) {
					entityCleanups.add( new EntityCleanup( persister.getCacheAccessStrategy() ) );
				}
				if ( persister.hasNaturalIdentifier() && persister.hasNaturalIdCache() ) {
					naturalIdCleanups.add( new NaturalIdCleanup( persister.getNaturalIdCacheAccessStrategy() ) );
				}

				final Set<String> roles = session.getFactory().getMetamodel().getCollectionRolesByEntityParticipant( persister.getEntityName() );
				if ( roles != null ) {
					for ( String role : roles ) {
						final CollectionPersister collectionPersister = factory.getMetamodel().collectionPersister( role );
						if ( collectionPersister.hasCache() ) {
							collectionCleanups.add(
									new CollectionCleanup( collectionPersister.getCacheAccessStrategy() )
							);
						}
					}
				}
			}
		}

		this.affectedTableSpaces = spacesList.toArray( new String[ spacesList.size() ] );
	}

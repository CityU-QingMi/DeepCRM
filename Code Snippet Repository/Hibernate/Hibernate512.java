	public BulkOperationCleanupAction(SharedSessionContractImplementor session, Queryable... affectedQueryables) {
		final SessionFactoryImplementor factory = session.getFactory();
		final LinkedHashSet<String> spacesList = new LinkedHashSet<>();
		for ( Queryable persister : affectedQueryables ) {
			spacesList.addAll( Arrays.asList( (String[]) persister.getQuerySpaces() ) );

			if ( persister.hasCache() ) {
				entityCleanups.add( new EntityCleanup( persister.getCacheAccessStrategy() ) );
			}
			if ( persister.hasNaturalIdentifier() && persister.hasNaturalIdCache() ) {
				naturalIdCleanups.add( new NaturalIdCleanup( persister.getNaturalIdCacheAccessStrategy() ) );
			}

			final Set<String> roles = factory.getMetamodel().getCollectionRolesByEntityParticipant( persister.getEntityName() );
			if ( roles != null ) {
				for ( String role : roles ) {
					final CollectionPersister collectionPersister = factory.getMetamodel().collectionPersister( role );
					if ( collectionPersister.hasCache() ) {
						collectionCleanups.add( new CollectionCleanup( collectionPersister.getCacheAccessStrategy() ) );
					}
				}
			}
		}

		this.affectedTableSpaces = spacesList.toArray( new String[ spacesList.size() ] );
	}

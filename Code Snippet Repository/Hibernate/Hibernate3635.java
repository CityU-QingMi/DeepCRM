	public CollectionElementLoader(
			QueryableCollection collectionPersister,
			SessionFactoryImplementor factory,
			LoadQueryInfluencers loadQueryInfluencers) throws MappingException {
		super( factory, loadQueryInfluencers );

		this.keyType = collectionPersister.getKeyType();
		this.indexType = collectionPersister.getIndexType();
		this.persister = (OuterJoinLoadable) collectionPersister.getElementPersister();
		this.entityName = persister.getEntityName();

		JoinWalker walker = new EntityJoinWalker(
				persister,
				ArrayHelper.join(
						collectionPersister.getKeyColumnNames(),
						collectionPersister.toColumns( "index" )
				),
				1,
				LockMode.NONE,
				factory,
				loadQueryInfluencers
		);
		initFromWalker( walker );

		postInstantiate();

		if ( LOG.isDebugEnabled() ) {
			LOG.debugf( "Static select for entity %s: %s", entityName, getSQLString() );
		}

	}

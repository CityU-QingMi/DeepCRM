	public EntityLoader(
			OuterJoinLoadable persister,
			String[] uniqueKey,
			Type uniqueKeyType,
			int batchSize,
			LockMode lockMode,
			SessionFactoryImplementor factory,
			LoadQueryInfluencers loadQueryInfluencers) throws MappingException {
		super( persister, uniqueKeyType, factory, loadQueryInfluencers );

		EntityJoinWalker walker = new EntityJoinWalker(
				persister,
				uniqueKey,
				batchSize,
				lockMode,
				factory,
				loadQueryInfluencers
		);
		initFromWalker( walker );
		this.compositeKeyManyToOneTargetIndices = walker.getCompositeKeyManyToOneTargetIndices();
		postInstantiate();

		batchLoader = batchSize > 1;

		if ( LOG.isDebugEnabled() ) {
			LOG.debugf( "Static select for entity %s [%s]: %s", entityName, lockMode, getSQLString() );
		}
	}

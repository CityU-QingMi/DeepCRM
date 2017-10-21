	public EntityJoinWalker(
			OuterJoinLoadable persister, 
			String[] uniqueKey, 
			int batchSize, 
			LockMode lockMode,
			final SessionFactoryImplementor factory,
			LoadQueryInfluencers loadQueryInfluencers) throws MappingException {
		super( persister, factory, loadQueryInfluencers );

		this.lockOptions.setLockMode(lockMode);
		
		StringBuilder whereCondition = whereString( getAlias(), uniqueKey, batchSize )
				//include the discriminator and class-level where, but not filters
				.append( persister.filterFragment( getAlias(), Collections.EMPTY_MAP ) );

		AssociationInitCallbackImpl callback = new AssociationInitCallbackImpl( factory );
		initAll( whereCondition.toString(), "", lockOptions, callback );
		this.compositeKeyManyToOneTargetIndices = callback.resolve();
	}

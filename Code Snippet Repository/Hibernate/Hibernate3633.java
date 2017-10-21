	public CascadeEntityJoinWalker(
			OuterJoinLoadable persister,
			CascadingAction action,
			SessionFactoryImplementor factory)
			throws MappingException {
		super( persister, factory, LoadQueryInfluencers.NONE );
		this.cascadeAction = action;
		StringBuilder whereCondition = whereString( getAlias(), persister.getIdentifierColumnNames(), 1 )
				//include the discriminator and class-level where, but not filters
				.append( persister.filterFragment( getAlias(), Collections.EMPTY_MAP ) );

		initAll( whereCondition.toString(), "", LockOptions.READ );
	}

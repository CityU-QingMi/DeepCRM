	@Override
	protected CollectionInitializer createSubselectInitializer(SubselectFetch subselect, SharedSessionContractImplementor session) {
		return new SubselectCollectionLoader(
				this,
				subselect.toSubselectString( getCollectionType().getLHSPropertyName() ),
				subselect.getResult(),
				subselect.getQueryParameters(),
				subselect.getNamedParameterLocMap(),
				session.getFactory(),
				session.getLoadQueryInfluencers()
		);
	}

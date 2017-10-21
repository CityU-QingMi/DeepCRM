	@Override
	public FetchStrategy determineFetchPlan(LoadQueryInfluencers loadQueryInfluencers, PropertyPath propertyPath) {
		final EntityPersister owningPersister = getSource().getEntityPersister();

		FetchStyle style = FetchStrategyHelper.determineFetchStyleByProfile(
				loadQueryInfluencers,
				owningPersister,
				propertyPath,
				attributeNumber()
		);
		if ( style == null ) {
			style = FetchStrategyHelper.determineFetchStyleByMetadata(
					( (OuterJoinLoadable) getSource().getEntityPersister() ).getFetchMode( attributeNumber() ),
					getType(),
					sessionFactory()
			);
		}

		return new FetchStrategy(
				FetchStrategyHelper.determineFetchTiming( style, getType(), sessionFactory() ),
				style
		);
	}

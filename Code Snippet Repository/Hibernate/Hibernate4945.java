	@Override
	public FetchStrategy determineFetchPlan(LoadQueryInfluencers loadQueryInfluencers, PropertyPath propertyPath) {
		final EntityPersister owningPersister = getSource().locateOwningPersister();

		FetchStyle style = FetchStrategyHelper.determineFetchStyleByProfile(
				loadQueryInfluencers,
				owningPersister,
				propertyPath,
				attributeNumber()
		);
		if ( style == null ) {
			style = determineFetchStyleByMetadata( getFetchMode(), getType() );
		}

		return new FetchStrategy( determineFetchTiming( style ), style );
	}

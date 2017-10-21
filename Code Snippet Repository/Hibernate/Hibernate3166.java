	private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException, SQLException {
		log.tracef( "Deserializing Session [%s]", getSessionIdentifier() );

		ois.defaultReadObject();

		persistenceContext = StatefulPersistenceContext.deserialize( ois, this );
		actionQueue = ActionQueue.deserialize( ois, this );

		loadQueryInfluencers = (LoadQueryInfluencers) ois.readObject();

		// LoadQueryInfluencers.getEnabledFilters() tries to validate each enabled
		// filter, which will fail when called before FilterImpl.afterDeserialize( factory );
		// Instead lookup the filter by name and then call FilterImpl.afterDeserialize( factory ).
		for ( String filterName : loadQueryInfluencers.getEnabledFilterNames() ) {
			( (FilterImpl) loadQueryInfluencers.getEnabledFilter( filterName ) ).afterDeserialize( getFactory() );
		}

		initializeFromSessionOwner( null );

		this.disallowOutOfTransactionUpdateOperations = !getFactory().getSessionFactoryOptions().isAllowOutOfTransactionUpdateOperations();
		this.discardOnClose = getFactory().getSessionFactoryOptions().isReleaseResourcesOnCloseEnabled();
	}

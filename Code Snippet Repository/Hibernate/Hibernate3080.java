	public SessionImpl(SessionFactoryImpl factory, SessionCreationOptions options) {
		super( factory, options );

		this.actionQueue = new ActionQueue( this );
		this.persistenceContext = new StatefulPersistenceContext( this );

		this.sessionOwner = options.getSessionOwner();
		initializeFromSessionOwner( sessionOwner );

		this.autoClear = options.shouldAutoClear();
		this.autoClose = options.shouldAutoClose();
		this.queryParametersValidationEnabled = options.isQueryParametersValidationEnabled();
		this.disallowOutOfTransactionUpdateOperations = !factory.getSessionFactoryOptions().isAllowOutOfTransactionUpdateOperations();
		this.discardOnClose = getFactory().getSessionFactoryOptions().isReleaseResourcesOnCloseEnabled();

		if ( options instanceof SharedSessionCreationOptions && ( (SharedSessionCreationOptions) options ).isTransactionCoordinatorShared() ) {
			final SharedSessionCreationOptions sharedOptions = (SharedSessionCreationOptions) options;
			if ( sharedOptions.getTransactionCompletionProcesses() != null ) {
				actionQueue.setTransactionCompletionProcesses( sharedOptions.getTransactionCompletionProcesses(), true );
			}
		}

		loadQueryInfluencers = new LoadQueryInfluencers( factory );

		if ( getFactory().getStatistics().isStatisticsEnabled() ) {
			getFactory().getStatistics().openSession();
		}

		// NOTE : pulse() already handles auto-join-ability correctly
		getTransactionCoordinator().pulse();

		setDefaultProperties();
		applyProperties();

		if ( TRACE_ENABLED ) {
			log.tracef( "Opened Session [%s] at timestamp: %s", getSessionIdentifier(), getTimestamp() );
		}
	}

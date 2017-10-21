	@Override
	public void finishUp(ResultSetProcessingContextImpl context, List<AfterLoadAction> afterLoadActionList) {
		final List<HydratedEntityRegistration> hydratedEntityRegistrations = context.getHydratedEntityRegistrationList();

		// for arrays, we should end the collection load before resolving the entities, since the
		// actual array instances are not instantiated during loading
		finishLoadingArrays( context );


		// IMPORTANT: reuse the same event instances for performance!
		final PreLoadEvent preLoadEvent;
		final PostLoadEvent postLoadEvent;
		if ( context.getSession().isEventSource() ) {
			preLoadEvent = new PreLoadEvent( (EventSource) context.getSession() );
			postLoadEvent = new PostLoadEvent( (EventSource) context.getSession() );
		}
		else {
			preLoadEvent = null;
			postLoadEvent = null;
		}

		// now finish loading the entities (2-phase load)
		performTwoPhaseLoad( preLoadEvent, context, hydratedEntityRegistrations );

		// now we can finalize loading collections
		finishLoadingCollections( context );

		// finally, perform post-load operations
		postLoad( postLoadEvent, context, hydratedEntityRegistrations, afterLoadActionList );
	}

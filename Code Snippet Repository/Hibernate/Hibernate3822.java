	private void performTwoPhaseLoad(
			PreLoadEvent preLoadEvent,
			ResultSetProcessingContextImpl context,
			List<HydratedEntityRegistration> hydratedEntityRegistrations) {
		final int numberOfHydratedObjects = hydratedEntityRegistrations == null
				? 0
				: hydratedEntityRegistrations.size();
		log.tracev( "Total objects hydrated: {0}", numberOfHydratedObjects );

		if ( hydratedEntityRegistrations == null ) {
			return;
		}

		for ( HydratedEntityRegistration registration : hydratedEntityRegistrations ) {
			TwoPhaseLoad.initializeEntity(
					registration.getInstance(),
					context.isReadOnly(),
					context.getSession(),
					preLoadEvent
			);
		}
	}

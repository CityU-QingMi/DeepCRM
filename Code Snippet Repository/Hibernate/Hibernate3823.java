	private void postLoad(
			PostLoadEvent postLoadEvent,
			ResultSetProcessingContextImpl context,
			List<HydratedEntityRegistration> hydratedEntityRegistrations,
			List<AfterLoadAction> afterLoadActionList) {
		// Until this entire method is refactored w/ polymorphism, postLoad was
		// split off from initializeEntity.  It *must* occur after
		// endCollectionLoad to ensure the collection is in the
		// persistence context.
		if ( hydratedEntityRegistrations == null ) {
			return;
		}

		for ( HydratedEntityRegistration registration : hydratedEntityRegistrations ) {
			TwoPhaseLoad.postLoad( registration.getInstance(), context.getSession(), postLoadEvent );
			if ( afterLoadActionList != null ) {
				for ( AfterLoadAction afterLoadAction : afterLoadActionList ) {
					afterLoadAction.afterLoad(
							context.getSession(),
							registration.getInstance(),
							(Loadable) registration.getEntityReference().getEntityPersister()
					);
				}
			}
		}

	}

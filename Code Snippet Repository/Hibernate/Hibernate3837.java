	void finishUpRow() {
		nRowsRead++;

		if ( currentRowHydratedEntityRegistrationList == null ) {
			if ( identifierResolutionContextMap != null ) {
				identifierResolutionContextMap.clear();
			}
			return;
		}


		// managing the running list of registrations ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		if ( hydratedEntityRegistrationList == null ) {
			hydratedEntityRegistrationList = new ArrayList<>();
		}
		hydratedEntityRegistrationList.addAll( currentRowHydratedEntityRegistrationList );


		// managing the map forms needed for subselect fetch generation ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		if ( hadSubselectFetches ) {
			if ( subselectLoadableEntityKeyMap == null ) {
				subselectLoadableEntityKeyMap = new HashMap<>();
			}
			for ( HydratedEntityRegistration registration : currentRowHydratedEntityRegistrationList ) {
				Set<EntityKey> entityKeys = subselectLoadableEntityKeyMap.get(
						registration.getEntityReference()
				);
				if ( entityKeys == null ) {
					entityKeys = new HashSet<>();
					subselectLoadableEntityKeyMap.put( registration.getEntityReference(), entityKeys );
				}
				entityKeys.add( registration.getKey() );
			}
		}

		// release the currentRowHydratedEntityRegistrationList entries
		currentRowHydratedEntityRegistrationList.clear();

		identifierResolutionContextMap.clear();
	}

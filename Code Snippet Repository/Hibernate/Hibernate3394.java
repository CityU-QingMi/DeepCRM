	private HashMap<Class, Callback[]> determineAppropriateCallbackMap(CallbackType callbackType) {
		if ( callbackType == CallbackType.PRE_PERSIST ) {
			return preCreates;
		}

		if ( callbackType == CallbackType.POST_PERSIST ) {
			return postCreates;
		}

		if ( callbackType == CallbackType.PRE_REMOVE ) {
			return preRemoves;
		}

		if ( callbackType == CallbackType.POST_REMOVE ) {
			return postRemoves;
		}

		if ( callbackType == CallbackType.PRE_UPDATE ) {
			return preUpdates;
		}

		if ( callbackType == CallbackType.POST_UPDATE ) {
			return postUpdates;
		}

		if ( callbackType == CallbackType.POST_LOAD ) {
			return postLoads;
		}

		throw new PersistenceException( "Unrecognized JPA callback type [" + callbackType + "]" );
	}

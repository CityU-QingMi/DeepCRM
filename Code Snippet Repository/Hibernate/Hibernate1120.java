	private void takeCollectionSizeSnapshot(Object target, String fieldName, Object value) {
		if ( value != null && value instanceof Collection && target instanceof SelfDirtinessTracker ) {
			CollectionTracker tracker = ( (SelfDirtinessTracker) target ).$$_hibernate_getCollectionTracker();
			if ( tracker == null ) {
				( (SelfDirtinessTracker) target ).$$_hibernate_clearDirtyAttributes();
				tracker = ( (SelfDirtinessTracker) target ).$$_hibernate_getCollectionTracker();
			}
			tracker.add( fieldName, ( (Collection) value ).size() );
		}
	}

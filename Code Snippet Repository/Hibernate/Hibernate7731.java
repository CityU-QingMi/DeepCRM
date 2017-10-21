	protected void checkListener(
			int eventIndex,
			Class<? extends AbstractCollectionEvent> expectedEventType,
			Identifiable expectedOwner,
			List<? extends Identifiable> expectedCollectionEntrySnapshot) {
		final AggregatedCollectionEventListener.EventEntry eventEntry
				= collectionListenerIntegrator.getListener().getEventEntryList().get( eventIndex );
		final AbstractCollectionEvent event = eventEntry.getEvent();

		assertTyping( expectedEventType, event );

// because of the merge graphs, the instances are likely different.  just base check on type and id
//		assertEquals( expectedOwner, event.getAffectedOwnerOrNull() );
		assertEquals( expectedOwner.getClass().getName(), event.getAffectedOwnerEntityName() );
		assertEquals( expectedOwner.getId(), event.getAffectedOwnerIdOrNull() );

		if ( event instanceof PreCollectionUpdateEvent
				|| event instanceof PreCollectionRemoveEvent
				|| event instanceof PostCollectionRecreateEvent ) {
			List<Identifiable> snapshot = (List) eventEntry.getSnapshotAtTimeOfEventHandling();
			assertEquals( expectedCollectionEntrySnapshot.size(), snapshot.size() );
			for ( int i = 0; i < expectedCollectionEntrySnapshot.size(); i++ ) {
				Identifiable expected = expectedCollectionEntrySnapshot.get( i );
				Identifiable found = snapshot.get( i );
				assertEquals( expected.getClass().getName(), found.getClass().getName() );
				assertEquals( expected.getId(), found.getId() );
			}
		}
	}

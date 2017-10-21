	protected void checkListener(
			MultipleCollectionListeners listeners,
			MultipleCollectionListeners.Listener listenerExpected,
			org.hibernate.test.event.collection.Entity ownerExpected,
			List<? extends org.hibernate.test.event.collection.Entity> expectedCollectionEntrySnapshot,
			int index) {
		AbstractCollectionEvent event = listeners
				.getEvents().get(index);

		assertSame(listenerExpected, listeners.getListenersCalled().get(index));
		assertEquals(ownerExpected, event.getAffectedOwnerOrNull());
		assertEquals(ownerExpected.getId(), event.getAffectedOwnerIdOrNull());
		assertEquals(ownerExpected.getClass().getName(),
				event.getAffectedOwnerEntityName());

		if (event instanceof PreCollectionUpdateEvent) {
			Serializable snapshot = listeners.getSnapshots().get(index);
			assertEquals(expectedCollectionEntrySnapshot, snapshot);
		}
		if (event instanceof PreCollectionRemoveEvent) {
			Serializable snapshot = listeners.getSnapshots().get(index);
			assertEquals(expectedCollectionEntrySnapshot, snapshot);
		}
		if (event instanceof PostCollectionRecreateEvent) {
			Serializable snapshot = listeners.getSnapshots().get(index);
			assertEquals(expectedCollectionEntrySnapshot, snapshot);
		}

	}

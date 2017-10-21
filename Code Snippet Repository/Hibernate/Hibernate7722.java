	protected void checkResult(CollectionListeners listeners,
							 CollectionListeners.Listener listenerExpected,
							 Entity ownerExpected,
							 Collection collExpected,
							 int index) {
		assertSame( listenerExpected, listeners.getListenersCalled().get( index ) );
		assertSame(
				ownerExpected,
				( ( AbstractCollectionEvent ) listeners.getEvents().get( index ) ).getAffectedOwnerOrNull()
		);
		assertEquals(
				ownerExpected.getId(),
				( ( AbstractCollectionEvent ) listeners.getEvents().get( index ) ).getAffectedOwnerIdOrNull()
		);
		assertEquals(
				ownerExpected.getClass().getName(),
				( (AbstractCollectionEvent) listeners.getEvents().get( index ) ).getAffectedOwnerEntityName()
		);
		assertSame(
				collExpected, ( ( AbstractCollectionEvent ) listeners.getEvents().get( index ) ).getCollection()
		);
	}

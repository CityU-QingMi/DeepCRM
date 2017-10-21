	public void addEvent(AbstractCollectionEvent event, Listener listener) {


		CollectionEntry collectionEntry = event.getSession()
				.getPersistenceContext()
				.getCollectionEntry(event.getCollection());
		Serializable snapshot = collectionEntry.getSnapshot();

		log.debug("add Event: " + event.getClass() + "; listener = "
				+ listener.getClass() + "; snapshot = " + snapshot);

		listenersCalled.add(listener);
		events.add(event);
		snapshots.add(snapshot);
	}

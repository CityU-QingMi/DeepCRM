	public void resetStoredSnapshot(PersistentCollection collection, Serializable storedSnapshot) {
		LOG.debugf("Reset storedSnapshot to %s for %s", storedSnapshot, this);

		if ( fromMerge ) {
			return; // EARLY EXIT!
		}

		snapshot = storedSnapshot;
		collection.setSnapshot( loadedKey, role, snapshot );
		fromMerge = true;
	}

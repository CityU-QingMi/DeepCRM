	private Object handleTombstone(MVCCEntry e, Tombstone tombstone) {
		// Tombstones always come with lifespan in metadata
		Object storedValue = e.getValue();
		if (storedValue instanceof Tombstone) {
			setValue(e, ((Tombstone) storedValue).merge(tombstone));
		}
		else {
			setValue(e, tombstone);
		}
		return null;
	}

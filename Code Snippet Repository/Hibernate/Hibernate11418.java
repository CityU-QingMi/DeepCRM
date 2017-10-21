	protected Object handleTombstoneUpdate(MVCCEntry e, TombstoneUpdate tombstoneUpdate, PutKeyValueCommand command) {
		Object storedValue = e.getValue();
		Object value = tombstoneUpdate.getValue();

		if (value == null) {
			// eviction
			if (storedValue == null || storedValue instanceof Tombstone) {
				setFailed(command);
			}
			else {
				// We have to keep Tombstone, because otherwise putFromLoad could insert a stale entry
				// (after it has been already updated and *then* evicted)
				setValue(e, new Tombstone(ZERO, tombstoneUpdate.getTimestamp()));
			}
		}
		else if (storedValue instanceof Tombstone) {
			Tombstone tombstone = (Tombstone) storedValue;
			if (tombstone.getLastTimestamp() < tombstoneUpdate.getTimestamp()) {
				setValue(e, value);
			}
		}
		else if (storedValue == null) {
			// async putFromLoads shouldn't cross the invalidation timestamp
			if (region.getLastRegionInvalidation() < tombstoneUpdate.getTimestamp()) {
				setValue(e, value);
			}
		}
		else {
			// Don't do anything locally. This could be the async remote write, though, when local
			// value has been already updated: let it propagate to remote nodes, too
		}
		return null;
	}

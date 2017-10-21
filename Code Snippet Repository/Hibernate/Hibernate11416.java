	private Object handleFutureUpdate(MVCCEntry e, FutureUpdate futureUpdate, PutKeyValueCommand command) {
		Object storedValue = e.getValue();
		if (storedValue instanceof Tombstone) {
			// Note that the update has to keep tombstone even if the transaction was unsuccessful;
			// before write we have removed the value and we have to protect the entry against stale putFromLoads
			Tombstone tombstone = (Tombstone) storedValue;
			setValue(e, tombstone.applyUpdate(futureUpdate.getUuid(), futureUpdate.getTimestamp(), futureUpdate.getValue()));

		}
		else {
			// This is an async future update, and it's timestamp may be vastly outdated
			// We need to first execute the async update and then local one, because if we're on the primary
			// owner the local future update would fail the async one.
			// TODO: There is some discrepancy with TombstoneUpdate handling which does not fail the update
			setFailed(command);
		}
		return null;
	}

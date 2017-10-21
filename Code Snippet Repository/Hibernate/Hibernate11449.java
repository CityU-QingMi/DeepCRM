	@Override
	protected void runInvalidation(boolean inTransaction) {
		if (strategy == null) {
			throw new IllegalStateException("Strategy was not set");
		}
		switch (strategy) {
			case NONE:
			case VALIDATION:
				super.runInvalidation(inTransaction);
				return;
			case TOMBSTONES:
				removeEntries(inTransaction, Tombstone.EXCLUDE_TOMBSTONES);
				return;
			case VERSIONED_ENTRIES:
				removeEntries(inTransaction, VersionedEntry.EXCLUDE_EMPTY_EXTRACT_VALUE);
				return;
		}
	}

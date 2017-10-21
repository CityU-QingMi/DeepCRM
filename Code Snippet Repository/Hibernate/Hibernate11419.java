	private Object setValue(MVCCEntry e, Object value) {
		if (e.isRemoved()) {
			e.setRemoved(false);
			e.setCreated(true);
			e.setValid(true);
		}
		else {
			e.setChanged(true);
		}
		if (value instanceof Tombstone) {
			e.setMetadata(expiringMetadata);
		}
		else {
			e.setMetadata(defaultMetadata);
		}
		return e.setValue(value);
	}

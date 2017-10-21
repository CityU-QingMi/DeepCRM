	private Object setValue(MVCCEntry e, Object value, Metadata metadata) {
		if (e.isRemoved()) {
			e.setRemoved(false);
			e.setCreated(true);
			e.setValid(true);
		}
		else {
			e.setChanged(true);
		}
		e.setMetadata(metadata);
		return e.setValue(value);
	}

	@Override
	@Nullable
	public String getVersion() {
		if (this.event.isStartDocument()) {
			return ((StartDocument) this.event).getVersion();
		}
		else {
			return null;
		}
	}

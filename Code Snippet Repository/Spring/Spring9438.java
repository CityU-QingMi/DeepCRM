	@Override
	@Nullable
	@SuppressWarnings("")
	public Object scanUnsafe(Attr key) {
		if (key == Attr.PREFETCH) {
			return Integer.MAX_VALUE;
		}
		if (key == Attr.PARENT) {
			return this.source;
		}
		return null;
	}

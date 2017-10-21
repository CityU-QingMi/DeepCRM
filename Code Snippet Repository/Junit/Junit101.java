	private void add(Throwable t) {
		Preconditions.notNull(t, "Throwable must not be null");

		if (this.throwable == null) {
			this.throwable = t;
		}
		else {
			this.throwable.addSuppressed(t);
		}
	}

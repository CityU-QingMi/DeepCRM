	@Override
	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( o == null || getClass() != o.getClass() ) {
			return false;
		}
		final DelayedPostInsertIdentifier that = (DelayedPostInsertIdentifier) o;
		return identifier == that.identifier;
	}

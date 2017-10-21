	@Override
	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( o == null || getClass() != o.getClass() ) {
			return false;
		}

		final AbstractAttributeKey that = (AbstractAttributeKey) o;
		return this.fullPath.equals( that.fullPath );

	}

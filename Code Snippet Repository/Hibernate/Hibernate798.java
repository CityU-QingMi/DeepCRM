	@Override
	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( o == null || getClass() != o.getClass() ) {
			return false;
		}

		final Namespace that = (Namespace) o;
		return EqualsHelper.equals( this.name, that.name );
	}

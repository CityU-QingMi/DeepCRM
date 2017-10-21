	@Override
	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( !( o instanceof Origin ) ) {
			return false;
		}

		final Origin other = (Origin) o;
		return type == other.type
				&& EqualsHelper.equals( name, other.name );

	}

	@Override
	public boolean equals(Object other) {
		if ( this == other ) {
			return true;
		}
		if ( other == null || getClass() != other.getClass() ) {
			return false;
		}
		final TypedValue that = (TypedValue) other;
		return type.getReturnedClass() == that.type.getReturnedClass()
				&& type.isEqual( that.value, value );
	}

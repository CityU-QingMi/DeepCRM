	@Override
	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( !(o instanceof Triple) ) {
			return false;
		}

		final Triple other = (Triple) o;
		return EqualsHelper.equals( obj1, other.obj1 )
				&& EqualsHelper.equals( obj2, other.obj2 )
				&& EqualsHelper.equals( obj3, other.obj3 );
	}

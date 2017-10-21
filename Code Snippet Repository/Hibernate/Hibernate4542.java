	@Override
	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( o == null || getClass() != o.getClass() ) {
			return false;
		}

		QueryParameterNamedImpl<?> that = (QueryParameterNamedImpl<?>) o;
		return getName().equals( that.getName() );
	}

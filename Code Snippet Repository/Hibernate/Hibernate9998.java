	@Override
	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( !(o instanceof QueryParameterData) ) {
			return false;
		}

		final QueryParameterData that = (QueryParameterData) o;
		return EqualsHelper.equals( flatEntityPropertyName, that.flatEntityPropertyName );
	}

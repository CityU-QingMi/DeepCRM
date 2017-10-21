	@Override
	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( o == null || getClass() != o.getClass() ) {
			return false;
		}

		final NativeSQLQuerySpecification that = (NativeSQLQuerySpecification) o;

		return querySpaces.equals( that.querySpaces )
				&& queryString.equals( that.queryString )
				&& Arrays.equals( queryReturns, that.queryReturns );
	}

	@Override
	@SuppressWarnings("")
	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( o == null || getClass() != o.getClass() ) {
			return false;
		}
		if ( ! super.equals( o ) ) {
			return false;
		}

		final NativeSQLQueryRootReturn that = (NativeSQLQueryRootReturn) o;

		if ( returnEntityName != null ? !returnEntityName.equals( that.returnEntityName ) : that.returnEntityName != null ) {
			return false;
		}

		return true;
	}

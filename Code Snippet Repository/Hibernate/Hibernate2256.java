	@Override
	@SuppressWarnings("")
	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( o == null || getClass() != o.getClass() ) {
			return false;
		}

		final NativeSQLQueryNonScalarReturn that = (NativeSQLQueryNonScalarReturn) o;

		if ( alias != null ? !alias.equals( that.alias ) : that.alias != null ) {
			return false;
		}
		if ( lockMode != null ? !lockMode.equals( that.lockMode ) : that.lockMode != null ) {
			return false;
		}
		if ( !propertyResults.equals( that.propertyResults ) ) {
			return false;
		}

		return true;
	}

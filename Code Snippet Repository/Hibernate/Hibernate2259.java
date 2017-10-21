	@Override
	@SuppressWarnings("")
	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( o == null || getClass() != o.getClass() ) {
			return false;
		}

		final NativeSQLQueryScalarReturn that = (NativeSQLQueryScalarReturn) o;
		if ( columnAlias != null ? !columnAlias.equals( that.columnAlias ) : that.columnAlias != null ) {
			return false;
		}
		if ( type != null ? !type.equals( that.type ) : that.type != null ) {
			return false;
		}

		return true;
	}

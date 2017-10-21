	@Override
	@SuppressWarnings("")
	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( o == null || getClass() != o.getClass() ) {
			return false;
		}
		if ( !super.equals( o ) ) {
			return false;
		}

		final NativeSQLQueryCollectionReturn that = (NativeSQLQueryCollectionReturn) o;

		if ( ownerEntityName != null ? !ownerEntityName.equals( that.ownerEntityName ) : that.ownerEntityName != null ) {
			return false;
		}
		if ( ownerProperty != null ? !ownerProperty.equals( that.ownerProperty ) : that.ownerProperty != null ) {
			return false;
		}

		return true;
	}

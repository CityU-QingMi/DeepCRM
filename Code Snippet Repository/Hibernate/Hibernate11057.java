	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( !(o instanceof UnversionedOptimisticLockingFieldEntity) ) {
			return false;
		}

		UnversionedOptimisticLockingFieldEntity that = (UnversionedOptimisticLockingFieldEntity) o;

		if ( id != null ? !id.equals( that.id ) : that.id != null ) {
			return false;
		}
		if ( str != null ? !str.equals( that.str ) : that.str != null ) {
			return false;
		}

		return true;
	}

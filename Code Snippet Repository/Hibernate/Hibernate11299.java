	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( !(o instanceof MappedParentEntity) ) {
			return false;
		}
		if ( !super.equals( o ) ) {
			return false;
		}

		MappedParentEntity that = (MappedParentEntity) o;

		if ( parent != null ? !parent.equals( that.parent ) : that.parent != null ) {
			return false;
		}

		return true;
	}

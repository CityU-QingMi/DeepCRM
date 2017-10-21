	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( !(o instanceof TransitiveParentEntity) ) {
			return false;
		}
		if ( !super.equals( o ) ) {
			return false;
		}

		TransitiveParentEntity that = (TransitiveParentEntity) o;

		if ( parent != null ? !parent.equals( that.parent ) : that.parent != null ) {
			return false;
		}

		return true;
	}

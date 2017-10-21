	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( !(o instanceof ChildEntity) ) {
			return false;
		}
		if ( !super.equals( o ) ) {
			return false;
		}

		ChildEntity that = (ChildEntity) o;

		if ( specificData != null ? !specificData.equals( that.specificData ) : that.specificData != null ) {
			return false;
		}

		return true;
	}

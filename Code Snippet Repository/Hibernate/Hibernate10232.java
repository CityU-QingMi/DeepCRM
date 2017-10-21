	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( !(o instanceof IntNoAutoIdTestEntity) ) {
			return false;
		}

		IntNoAutoIdTestEntity that = (IntNoAutoIdTestEntity) o;

		if ( id != null ? !id.equals( that.id ) : that.id != null ) {
			return false;
		}
		//noinspection RedundantIfStatement
		if ( numVal != null ? !numVal.equals( that.numVal ) : that.numVal != null ) {
			return false;
		}

		return true;
	}

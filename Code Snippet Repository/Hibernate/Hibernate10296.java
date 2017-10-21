	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( !(o instanceof MulId) ) {
			return false;
		}

		MulId mulId = (MulId) o;

		if ( id1 != null ? !id1.equals( mulId.id1 ) : mulId.id1 != null ) {
			return false;
		}
		if ( id2 != null ? !id2.equals( mulId.id2 ) : mulId.id2 != null ) {
			return false;
		}

		return true;
	}

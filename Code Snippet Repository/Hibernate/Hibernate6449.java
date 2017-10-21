	public boolean equals(Object other) {
		if ( other == this ) {
			return true;
		}
		if ( other == null || getClass() != other.getClass() ) {
			return false;
		}
		CustomerInventoryPK cip = ( CustomerInventoryPK ) other;
		return ( custId == cip.custId && ( id == cip.id ||
				( id != null && id.equals( cip.id ) ) ) );
	}

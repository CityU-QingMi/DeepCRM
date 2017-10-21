	public boolean equals(Object other) {
		if ( other == this ) {
			return true;
		}
		if ( other == null || getClass() != other.getClass() ) {
			return false;
		}
		CustomerInventoryPK cip = ( CustomerInventoryPK ) other;
		return ( getCustomer().getId() == cip.getCustomer().getId() && ( id == cip.id ||
				( id != null && id.equals( cip.id ) ) ) );
	}

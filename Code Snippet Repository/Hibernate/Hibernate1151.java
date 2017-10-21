	@Override
	public boolean equals(Object other) {
		if ( other == null ) {
			return false;
		}
		if ( this == other ) {
			return true;
		}
		if ( hashCode != other.hashCode() || !( other instanceof CacheKeyImplementation) ) {
			//hashCode is part of this check since it is pre-calculated and hash must match for equals to be true
			return false;
		}
		final CacheKeyImplementation that = (CacheKeyImplementation) other;
		return EqualsHelper.equals( entityOrRoleName, that.entityOrRoleName )
				&& type.isEqual( id, that.id)
				&& EqualsHelper.equals( tenantId, that.tenantId );
	}

	@Override
	public boolean equals(Object other) {
		if ( this == other ) {
			return true;
		}
		if ( other == null || EntityKey.class != other.getClass() ) {
			return false;
		}

		final EntityKey otherKey = (EntityKey) other;
		return samePersistentType( otherKey )
				&& sameIdentifier( otherKey );

	}

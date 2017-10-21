	@Override
	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( o == null || getClass() != o.getClass() ) {
			return false;
		}

		final AssociationKey that = (AssociationKey) o;
		return ownerKey.equals( that.ownerKey )
				&& propertyName.equals( that.propertyName );
	}

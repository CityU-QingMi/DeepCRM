	private boolean determineNaturalIdNullability() {
		boolean[] nullability = getPropertyNullability();
		for ( int position : getNaturalIdentifierProperties() ) {
			// if any individual property is nullable, return false
			if ( nullability[position] ) {
				return false;
			}
		}
		// return true if we found no individually nullable properties
		return true;
	}

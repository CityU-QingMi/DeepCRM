	@SuppressWarnings({"", ""})
	public boolean isPropertyDefinedInHierarchy(String name) {
		if ( hasProperty( name ) ) {
			return true;
		}

		if ( getSuperMappedSuperclass() != null
				&& getSuperMappedSuperclass().isPropertyDefinedInHierarchy( name ) ) {
			return true;
		}

		if ( getSuperclass() != null
				&& getSuperclass().isPropertyDefinedInHierarchy( name ) ) {
			return true;
		}

		return false;
	}

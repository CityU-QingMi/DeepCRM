	@SuppressWarnings({"", ""})
	public boolean isPropertyDefinedInHierarchy(String name) {
		if ( hasProperty( name ) ) {
			return true;
		}

		if ( getSuperMappedSuperclass() != null
				&& getSuperMappedSuperclass().isPropertyDefinedInHierarchy( name ) ) {
			return true;
		}

		if ( getSuperPersistentClass() != null
				&& getSuperPersistentClass().isPropertyDefinedInHierarchy( name ) ) {
			return true;
		}

		return false;
	}

	public boolean hasAnyUninitializedAttributes() {
		if ( lazyFields == null ) {
			return false;
		}

		if ( initializedLazyFields == null ) {
			return true;
		}

		for ( String fieldName : lazyFields ) {
			if ( !initializedLazyFields.contains( fieldName ) ) {
				return true;
			}
		}

		return false;
	}

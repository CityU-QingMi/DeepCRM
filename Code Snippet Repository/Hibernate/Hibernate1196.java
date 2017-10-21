	private Column[] getExactOverriddenColumn(String propertyName) {
		Column[] override = null;
		if ( parent != null ) {
			override = parent.getExactOverriddenColumn( propertyName );
		}
		if ( override == null && currentPropertyColumnOverride != null ) {
			override = currentPropertyColumnOverride.get( propertyName );
		}
		if ( override == null && holderColumnOverride != null ) {
			override = holderColumnOverride.get( propertyName );
		}
		return override;
	}

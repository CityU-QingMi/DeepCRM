	private ForeignKey getExactOverriddenForeignKey(String propertyName) {
		ForeignKey override = null;
		if ( parent != null ) {
			override = parent.getExactOverriddenForeignKey( propertyName );
		}
		if ( override == null && currentPropertyForeignKeyOverride != null ) {
			override = currentPropertyForeignKeyOverride.get( propertyName );
		}
		if ( override == null && holderForeignKeyOverride != null ) {
			override = holderForeignKeyOverride.get( propertyName );
		}
		return override;
	}

	private JoinColumn[] getExactOverriddenJoinColumn(String propertyName) {
		JoinColumn[] override = null;
		if ( parent != null ) {
			override = parent.getExactOverriddenJoinColumn( propertyName );
		}
		if ( override == null && currentPropertyJoinColumnOverride != null ) {
			override = currentPropertyJoinColumnOverride.get( propertyName );
		}
		if ( override == null && holderJoinColumnOverride != null ) {
			override = holderJoinColumnOverride.get( propertyName );
		}
		return override;
	}

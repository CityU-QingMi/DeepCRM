	@Override
	public JoinColumn[] getOverriddenJoinColumn(String propertyName) {
		JoinColumn[] result = getExactOverriddenJoinColumn( propertyName );
		if ( result == null && propertyName.contains( ".collection&&element." ) ) {
			//support for non map collections where no prefix is needed
			//TODO cache the underlying regexp
			result = getExactOverriddenJoinColumn( propertyName.replace( ".collection&&element.", "."  ) );
		}
		return result;
	}

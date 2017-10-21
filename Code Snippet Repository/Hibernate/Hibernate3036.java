	public void render(StringBuilder buffer, FilterAliasGenerator aliasGenerator, Map enabledFilters) {
		if ( CollectionHelper.isEmpty( filterNames ) ) {
			return;
		}
		for ( int i = 0, max = filterNames.length; i < max; i++ ) {
			if ( enabledFilters.containsKey( filterNames[i] ) ) {
				final String condition = filterConditions[i];
				if ( StringHelper.isNotEmpty( condition ) ) {
					buffer.append( " and " ).append( render( aliasGenerator, i ) );
				}
			}
		}
	}

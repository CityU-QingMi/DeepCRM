	private void addNamedNativeQueryIfNeeded(NamedNativeQuery annotation, List<NamedNativeQuery> queries) {
		if ( annotation != null ) {
			String queryName = annotation.name();
			boolean present = false;
			for ( NamedNativeQuery current : queries ) {
				if ( current.name().equals( queryName ) ) {
					present = true;
					break;
				}
			}
			if ( !present ) {
				queries.add( annotation );
			}
		}
	}

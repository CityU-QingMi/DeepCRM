	private void addNamedEntityGraphIfNeeded(NamedEntityGraph annotation, List<NamedEntityGraph> queries) {
		if ( annotation != null ) {
			String queryName = annotation.name();
			boolean present = false;
			for ( NamedEntityGraph current : queries ) {
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

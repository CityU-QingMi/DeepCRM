	public Set<Serializable> getQuerySpaces() {
		if ( querySpaces == null ) {
			for ( E e : executables ) {
				Serializable[] propertySpaces = e.getPropertySpaces();
				if ( propertySpaces != null && propertySpaces.length > 0 ) {
					if( querySpaces == null ) {
						querySpaces = new HashSet<Serializable>();
					}
					Collections.addAll( querySpaces, propertySpaces );
				}
			}
			if( querySpaces == null ) {
				return Collections.emptySet();
			}
		}
		return querySpaces;
	}

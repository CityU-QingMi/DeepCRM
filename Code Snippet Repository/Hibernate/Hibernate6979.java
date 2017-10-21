	public Object instantiate(Serializable id) {
		if ( Cuisine.class.getName().equals( entityName ) ) {
			return ProxyHelper.newCuisineProxy( id );
		}
		if ( Country.class.getName().equals( entityName ) ) {
			return ProxyHelper.newCountryProxy( id );
		}
		else {
			throw new IllegalArgumentException( "unknown entity for instantiation [" + entityName + "]" );
		}
	}

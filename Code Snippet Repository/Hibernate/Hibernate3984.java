	@Override
	@SuppressWarnings({ "" })
	public MapAttribute<? super X, ?, ?> getMap(String name) {
		PluralAttribute<? super X, ?, ?> attribute = getPluralAttribute( name );
		if ( attribute == null && getSupertype() != null ) {
			attribute = getSupertype().getPluralAttribute( name );
		}
		basicMapCheck( attribute, name );
		return (MapAttribute<? super X, ?, ?>) attribute;
	}

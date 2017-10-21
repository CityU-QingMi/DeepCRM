	@Override
	@SuppressWarnings({ "" })
	public SetAttribute<? super X, ?> getSet(String name) {
		PluralAttribute<? super X, ?, ?> attribute = getPluralAttribute( name );
		if ( attribute == null && getSupertype() != null ) {
			attribute = getSupertype().getPluralAttribute( name );
		}
		basicSetCheck( attribute, name );
		return (SetAttribute<? super X, ?>) attribute;
	}

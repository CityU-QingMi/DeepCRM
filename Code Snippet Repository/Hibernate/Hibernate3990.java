	@Override
	@SuppressWarnings({ "" })
	public Attribute<? super X, ?> getAttribute(String name) {
		Attribute<? super X, ?> attribute = declaredAttributes.get( name );
		if ( attribute == null && getSupertype() != null ) {
			attribute = getSupertype().getAttribute( name );
		}
		checkNotNull( "Attribute ", attribute, name );
		return attribute;
	}

	@Override
	@SuppressWarnings({ "" })
	public SingularAttribute<? super X, ?> getSingularAttribute(String name) {
		SingularAttribute<? super X, ?> attribute = declaredSingularAttributes.get( name );
		if ( attribute == null && getSupertype() != null ) {
			attribute = getSupertype().getSingularAttribute( name );
		}
		checkNotNull( "SingularAttribute ", attribute, name );
		return attribute;
	}

	@Override
	@SuppressWarnings({ "" })
	public <Y> SingularAttribute<? super X, Y> getSingularAttribute(String name, Class<Y> type) {
		SingularAttribute<? super X, ?> attribute = declaredSingularAttributes.get( name );
		if ( attribute == null && getSupertype() != null ) {
			attribute = getSupertype().getSingularAttribute( name );
		}
		checkTypeForSingleAttribute( "SingularAttribute ", attribute, name, type );
		return ( SingularAttribute<? super X, Y> ) attribute;
	}

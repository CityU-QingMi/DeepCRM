	@Override
	@SuppressWarnings({ "" })
	public ListAttribute<? super X, ?> getList(String name) {
		PluralAttribute<? super X, ?, ?> attribute = getPluralAttribute( name );
		if ( attribute == null && getSupertype() != null ) {
			attribute = getSupertype().getPluralAttribute( name );
		}
		basicListCheck( attribute, name );
		return (ListAttribute<? super X, ?>) attribute;
	}

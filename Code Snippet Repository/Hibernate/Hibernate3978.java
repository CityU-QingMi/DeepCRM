	@Override
	@SuppressWarnings({ "" })
	public <Y> SingularAttribute<X, Y> getDeclaredId(Class<Y> javaType) {
		ensureNoIdClass();
		if ( id == null ) {
			throw new IllegalArgumentException( "The id attribute is not declared on this type [" + getTypeName() + "]" );
		}
		checkType( id, javaType );
		return (SingularAttribute<X, Y>) id;
	}

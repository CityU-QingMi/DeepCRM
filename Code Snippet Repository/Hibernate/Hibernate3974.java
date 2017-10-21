	@Override
	@SuppressWarnings({ "" })
	public <Y> SingularAttribute<? super X, Y> getId(Class<Y> javaType) {
		ensureNoIdClass();
		SingularAttributeImpl id = locateIdAttribute();
		if ( id != null ) {
			checkType( id, javaType );
		}
		return ( SingularAttribute<? super X, Y> ) id;
	}

	@Override
	@SuppressWarnings({ "" })
	public <Y> SingularAttribute<? super X, Y> getVersion(Class<Y> javaType) {
		// todo : is return null allowed?
		if ( ! hasVersionAttribute() ) {
			return null;
		}

		SingularAttributeImpl version = locateVersionAttribute();
		if ( version != null ) {
			checkType( version, javaType );
		}
		return ( SingularAttribute<? super X, Y> ) version;
	}

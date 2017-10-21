	public Type byClass(Class clazz, Properties parameters) {
		if ( Type.class.isAssignableFrom( clazz ) ) {
			return type( clazz, parameters );
		}

		if ( CompositeUserType.class.isAssignableFrom( clazz ) ) {
			return customComponent( clazz, parameters );
		}

		if ( UserType.class.isAssignableFrom( clazz ) ) {
			return custom( clazz, parameters );
		}

		if ( Lifecycle.class.isAssignableFrom( clazz ) ) {
			// not really a many-to-one association *necessarily*
			return manyToOne( clazz.getName() );
		}

		if ( Serializable.class.isAssignableFrom( clazz ) ) {
			return serializable( clazz );
		}

		return null;
	}

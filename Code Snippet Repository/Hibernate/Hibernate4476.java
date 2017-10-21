	@Override
	@SuppressWarnings("")
	public QueryImplementor setProperties(Object bean) {
		Class clazz = bean.getClass();
		String[] params = getNamedParameters();
		for ( String namedParam : params ) {
			try {
				final PropertyAccess propertyAccess = BuiltInPropertyAccessStrategies.BASIC.getStrategy().buildPropertyAccess(
						clazz,
						namedParam
				);
				final Getter getter = propertyAccess.getGetter();
				final Class retType = getter.getReturnType();
				final Object object = getter.get( bean );
				if ( Collection.class.isAssignableFrom( retType ) ) {
					setParameterList( namedParam, (Collection) object );
				}
				else if ( retType.isArray() ) {
					setParameterList( namedParam, (Object[]) object );
				}
				else {
					Type type = determineType( namedParam, retType );
					setParameter( namedParam, object, type );
				}
			}
			catch (PropertyNotFoundException pnfe) {
				// ignore
			}
		}
		return this;
	}

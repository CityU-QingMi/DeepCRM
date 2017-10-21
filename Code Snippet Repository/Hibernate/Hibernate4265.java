	public PropertyAccessBasicImpl(
			PropertyAccessStrategyBasicImpl strategy,
			Class containerJavaType,
			final String propertyName) {
		this.strategy = strategy;

		final Method getterMethod = ReflectHelper.findGetterMethod( containerJavaType, propertyName );
		this.getter = new GetterMethodImpl( containerJavaType, propertyName, getterMethod );

		final Method setterMethod = ReflectHelper.findSetterMethod( containerJavaType, propertyName, getterMethod.getReturnType() );
		this.setter = new SetterMethodImpl( containerJavaType, propertyName, setterMethod );
	}

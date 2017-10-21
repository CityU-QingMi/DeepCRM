	public PropertyAccessFieldImpl(
			PropertyAccessStrategyFieldImpl strategy,
			Class containerJavaType,
			final String propertyName) {
		this.strategy = strategy;

		final Field field = ReflectHelper.findField( containerJavaType, propertyName );
		this.getter = new GetterFieldImpl( containerJavaType, propertyName, field );
		this.setter = new SetterFieldImpl( containerJavaType, propertyName, field );
	}

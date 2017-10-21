	@Override
	public PropertyAccessStrategy resolvePropertyAccessStrategy(
			Class containerClass,
			String explicitAccessStrategyName,
			EntityMode entityMode) {

		if ( BuiltInPropertyAccessStrategies.BASIC.getExternalName().equals( explicitAccessStrategyName )
				|| BuiltInPropertyAccessStrategies.FIELD.getExternalName().equals( explicitAccessStrategyName )
				|| BuiltInPropertyAccessStrategies.MIXED.getExternalName().equals( explicitAccessStrategyName ) ) {
			if ( Managed.class.isAssignableFrom( containerClass ) ) {
				// PROPERTY (BASIC) and MIXED are not valid for bytecode enhanced entities...
				return PropertyAccessStrategyEnhancedImpl.INSTANCE;
			}
		}

		if ( StringHelper.isNotEmpty( explicitAccessStrategyName ) ) {
			return resolveExplicitlyNamedPropertyAccessStrategy( explicitAccessStrategyName );
		}

		if ( entityMode == EntityMode.MAP ) {
			return BuiltInPropertyAccessStrategies.MAP.getStrategy();
		}
		else {
			return BuiltInPropertyAccessStrategies.BASIC.getStrategy();
		}
	}

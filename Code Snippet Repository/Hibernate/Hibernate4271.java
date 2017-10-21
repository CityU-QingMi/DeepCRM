	protected PropertyAccessStrategy resolveExplicitlyNamedPropertyAccessStrategy(String explicitAccessStrategyName) {
		final BuiltInPropertyAccessStrategies builtInStrategyEnum = BuiltInPropertyAccessStrategies.interpret(
				explicitAccessStrategyName
		);
		if ( builtInStrategyEnum != null ) {
			return builtInStrategyEnum.getStrategy();
		}

		return strategySelectorService().resolveStrategy( PropertyAccessStrategy.class, explicitAccessStrategyName );
	}

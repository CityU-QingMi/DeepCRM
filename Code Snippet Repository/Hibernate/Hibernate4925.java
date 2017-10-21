	public PojoInstantiator(Class componentClass, ReflectionOptimizer.InstantiationOptimizer optimizer) {
		this.mappedClass = componentClass;
		this.isAbstract = ReflectHelper.isAbstractClass( mappedClass );
		this.optimizer = optimizer;

		this.embeddedIdentifier = false;

		try {
			constructor = ReflectHelper.getDefaultConstructor(mappedClass);
		}
		catch ( PropertyNotFoundException pnfe ) {
			LOG.noDefaultConstructor(mappedClass.getName());
			constructor = null;
		}
	}

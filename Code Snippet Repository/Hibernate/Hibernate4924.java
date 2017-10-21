	public PojoInstantiator(
			Class mappedClass,
			ReflectionOptimizer.InstantiationOptimizer optimizer,
			boolean embeddedIdentifier) {
		this.mappedClass = mappedClass;
		this.optimizer = optimizer;
		this.embeddedIdentifier = embeddedIdentifier;
		this.isAbstract = ReflectHelper.isAbstractClass( mappedClass );

		try {
			constructor = ReflectHelper.getDefaultConstructor(mappedClass);
		}
		catch ( PropertyNotFoundException pnfe ) {
			LOG.noDefaultConstructor( mappedClass.getName() );
			constructor = null;
		}
	}

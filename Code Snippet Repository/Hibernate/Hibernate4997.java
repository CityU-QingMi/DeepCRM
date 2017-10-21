	public PojoEntityInstantiator(
			EntityMetamodel entityMetamodel,
			PersistentClass persistentClass,
			ReflectionOptimizer.InstantiationOptimizer optimizer) {
		super(
				persistentClass.getMappedClass(),
				optimizer,
				persistentClass.hasEmbeddedIdentifier()
		);
		this.entityMetamodel = entityMetamodel;

		this.proxyInterface = persistentClass.getProxyInterface();
		this.applyBytecodeInterception = PersistentAttributeInterceptable.class.isAssignableFrom( persistentClass.getMappedClass() );
	}

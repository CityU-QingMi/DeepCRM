	@Override
	public void postInstantiate(
			final String entityName,
			final Class persistentClass,
			final Set<Class> interfaces,
			final Method getIdentifierMethod,
			final Method setIdentifierMethod,
			CompositeType componentIdType) throws HibernateException {
		this.entityName = entityName;
		this.persistentClass = persistentClass;
		this.interfaces = toArray( interfaces );
		this.getIdentifierMethod = getIdentifierMethod;
		this.setIdentifierMethod = setIdentifierMethod;
		this.componentIdType = componentIdType;
		this.overridesEquals = ReflectHelper.overridesEquals( persistentClass );

		this.proxyClass = buildJavassistProxyFactory().createClass();
	}

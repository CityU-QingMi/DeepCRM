	private static EntityManager createProxy(
			EntityManager rawEm, @Nullable Class<? extends EntityManager> emIfc, @Nullable ClassLoader cl,
			@Nullable PersistenceExceptionTranslator exceptionTranslator, @Nullable Boolean jta,
			boolean containerManaged, boolean synchronizedWithTransaction) {

		Assert.notNull(rawEm, "EntityManager must not be null");
		Set<Class<?>> ifcs = new LinkedHashSet<>();
		if (emIfc != null) {
			ifcs.add(emIfc);
		}
		else {
			ifcs.addAll(ClassUtils.getAllInterfacesForClassAsSet(rawEm.getClass(), cl));
		}
		ifcs.add(EntityManagerProxy.class);
		return (EntityManager) Proxy.newProxyInstance(
				(cl != null ? cl : ExtendedEntityManagerCreator.class.getClassLoader()),
				ifcs.toArray(new Class<?>[ifcs.size()]),
				new ExtendedEntityManagerInvocationHandler(
						rawEm, exceptionTranslator, jta, containerManaged, synchronizedWithTransaction));
	}

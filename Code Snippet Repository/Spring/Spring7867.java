	@Override
	@Nullable
	public SessionFactory getObject() {
		EntityManagerFactory emf = getEntityManagerFactory();
		Assert.state(emf != null, "EntityManagerFactory must not be null");
		try {
			Method getSessionFactory = emf.getClass().getMethod("getSessionFactory");
			return (SessionFactory) ReflectionUtils.invokeMethod(getSessionFactory, emf);
		}
		catch (NoSuchMethodException ex) {
			throw new IllegalStateException("No compatible Hibernate EntityManagerFactory found: " + ex);
		}
	}

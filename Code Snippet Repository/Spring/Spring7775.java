	@Nullable
	public static DataSource getDataSource(SessionFactory sessionFactory) {
		Method getProperties = ClassUtils.getMethodIfAvailable(sessionFactory.getClass(), "getProperties");
		if (getProperties != null) {
			Map<?, ?> props = (Map<?, ?>) ReflectionUtils.invokeMethod(getProperties, sessionFactory);
			if (props != null) {
				Object dataSourceValue = props.get(Environment.DATASOURCE);
				if (dataSourceValue instanceof DataSource) {
					return (DataSource) dataSourceValue;
				}
			}
		}
		if (sessionFactory instanceof SessionFactoryImplementor) {
			SessionFactoryImplementor sfi = (SessionFactoryImplementor) sessionFactory;
			try {
				ConnectionProvider cp = sfi.getServiceRegistry().getService(ConnectionProvider.class);
				if (cp != null) {
					return cp.unwrap(DataSource.class);
				}
			}
			catch (UnknownServiceException ex) {
				if (logger.isDebugEnabled()) {
					logger.debug("No ConnectionProvider found - cannot determine DataSource for SessionFactory: " + ex);
				}
			}
		}
		return null;
	}

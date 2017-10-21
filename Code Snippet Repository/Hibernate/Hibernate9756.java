	@Override
	public synchronized void registerMBeanForCacheManager(final CacheManager manager, final Properties properties)
			throws Exception {
		final String sessionFactoryName = properties.getProperty( Environment.SESSION_FACTORY_NAME );
		final String name;
		if ( sessionFactoryName == null ) {
			name = manager.getName();
		}
		else {
			name = "".equals( sessionFactoryName.trim() ) ? manager.getName() : sessionFactoryName;
		}
		registerBean( name, manager );
	}

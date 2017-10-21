	@Override
	public void start(SessionFactoryOptions settings, Properties properties) throws CacheException {
		this.settings = settings;
		try {
			String configurationResourceName = null;
			if ( properties != null ) {
				configurationResourceName = (String) properties.get( NET_SF_EHCACHE_CONFIGURATION_RESOURCE_NAME );
			}
			if ( configurationResourceName == null || configurationResourceName.length() == 0 ) {
				manager = CacheManager.create();
				REFERENCE_COUNT.incrementAndGet();
			}
			else {
				URL url;
				try {
					url = new URL( configurationResourceName );
				}
				catch (MalformedURLException e) {
					if ( !configurationResourceName.startsWith( "/" ) ) {
						configurationResourceName = "/" + configurationResourceName;
						LOG.debugf(
								"prepending / to %s. It should be placed in the root of the classpath rather than in a package.",
								configurationResourceName
						);
					}
					url = loadResource( configurationResourceName );
				}
				final Configuration configuration = HibernateEhcacheUtils.loadAndCorrectConfiguration( url );
				manager = CacheManager.create( configuration );
				REFERENCE_COUNT.incrementAndGet();
			}
			mbeanRegistrationHelper.registerMBean( manager, properties );
		}
		catch (net.sf.ehcache.CacheException e) {
			throw new CacheException( e );
		}
	}

	private void registerBean(String name, CacheManager manager) throws Exception {
		ehcacheHibernate = new EhcacheHibernate( manager );
		int tries = 0;
		boolean success;
		Exception exception = null;
		final String cacheManagerClusterUUID = manager.getClusterUUID();
		String registeredCacheManagerName;
		do {
			registeredCacheManagerName = name;
			if ( tries != 0 ) {
				registeredCacheManagerName += "_" + tries;
			}
			try {
				// register the CacheManager MBean
				final MBeanServer mBeanServer = getMBeanServer();
				cacheManagerObjectName = EhcacheHibernateMbeanNames.getCacheManagerObjectName(
						cacheManagerClusterUUID,
						registeredCacheManagerName
				);
				mBeanServer.registerMBean( ehcacheHibernate, cacheManagerObjectName );
				success = true;
				break;
			}
			catch (InstanceAlreadyExistsException e) {
				success = false;
				exception = e;
			}
			tries++;
		} while ( tries < MAX_MBEAN_REGISTRATION_RETRIES );
		if ( !success ) {
			throw new Exception(
					"Cannot register mbean for CacheManager with name" + manager.getName() + " after "
							+ MAX_MBEAN_REGISTRATION_RETRIES + " retries. Last tried name=" + registeredCacheManagerName,
					exception
			);
		}
		status = Status.STATUS_ALIVE;
	}

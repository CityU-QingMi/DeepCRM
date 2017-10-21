	@Override
	public void startUp()  {
		super.startUp();
		// Bind a listener to the "local" cache
		// Our region factory makes its CacheManager available to us
		localManager = ClusterAwareRegionFactory.getCacheManager( DualNodeTest.LOCAL );
		// Cache localCache = localManager.getCache("entity");
		localCustomerCache = localManager.getCache( Customer.class.getName() ).getAdvancedCache();
		localContactCache = localManager.getCache( Contact.class.getName() ).getAdvancedCache();
		localCollectionCache = localManager.getCache( Customer.class.getName() + ".contacts" ).getAdvancedCache();
		localListener = new MyListener( "local" );
		localCustomerCache.addListener( localListener );
		localContactCache.addListener( localListener );
		localCollectionCache.addListener( localListener );

		// Bind a listener to the "remote" cache
		remoteManager = ClusterAwareRegionFactory.getCacheManager( DualNodeTest.REMOTE );
		remoteCustomerCache = remoteManager.getCache( Customer.class.getName() ).getAdvancedCache();
		remoteContactCache = remoteManager.getCache( Contact.class.getName() ).getAdvancedCache();
		remoteCollectionCache = remoteManager.getCache( Customer.class.getName() + ".contacts" ).getAdvancedCache();
		remoteListener = new MyListener( "remote" );
		remoteCustomerCache.addListener( remoteListener );
		remoteContactCache.addListener( remoteListener );
		remoteCollectionCache.addListener( remoteListener );

		localFactory = sessionFactory();
		remoteFactory = secondNodeEnvironment().getSessionFactory();
	}

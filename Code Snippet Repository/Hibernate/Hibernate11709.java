			@CacheEntryActivated
			@CacheEntryCreated
			@CacheEntryInvalidated
			@CacheEntryLoaded
			@CacheEntryModified
			@CacheEntryPassivated
			@CacheEntryRemoved
			@CacheEntryVisited
			public void event(Event event) throws Throwable {
				ClassLoader cl = Thread.currentThread().getContextClassLoader();
				String notFoundPackage = "org.hibernate.test.cache.infinispan.functional.entities";
				String[] notFoundClasses = { notFoundPackage + ".Account", notFoundPackage + ".AccountHolder" };
				SelectedClassnameClassLoader visible = new SelectedClassnameClassLoader(null, null, notFoundClasses, cl);
				Thread.currentThread().setContextClassLoader(visible);
				super.event(event);
				Thread.currentThread().setContextClassLoader(cl);
			}

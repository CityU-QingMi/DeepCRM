	@Override
	@SuppressWarnings("")
	public Object narrowProxy(Object proxy, EntityPersister persister, EntityKey key, Object object)
			throws HibernateException {

		final Class concreteProxyClass = persister.getConcreteProxyClass();
		final boolean alreadyNarrow = concreteProxyClass.isInstance( proxy );

		if ( !alreadyNarrow ) {
			LOG.narrowingProxy( concreteProxyClass );

			// If an impl is passed, there is really no point in creating a proxy.
			// It would just be extra processing.  Just return the impl
			if ( object != null ) {
				proxiesByKey.remove( key );
				return object;
			}

			// Similarly, if the original HibernateProxy is initialized, there
			// is again no point in creating a proxy.  Just return the impl
			final HibernateProxy originalHibernateProxy = (HibernateProxy) proxy;
			if ( !originalHibernateProxy.getHibernateLazyInitializer().isUninitialized() ) {
				final Object impl = originalHibernateProxy.getHibernateLazyInitializer().getImplementation();
				// can we return it?
				if ( concreteProxyClass.isInstance( impl ) ) {
					proxiesByKey.remove( key );
					return impl;
				}
			}


			// Otherwise, create the narrowed proxy
			final HibernateProxy narrowedProxy = (HibernateProxy) persister.createProxy( key.getIdentifier(), session );

			// set the read-only/modifiable mode in the new proxy to what it was in the original proxy
			final boolean readOnlyOrig = originalHibernateProxy.getHibernateLazyInitializer().isReadOnly();
			narrowedProxy.getHibernateLazyInitializer().setReadOnly( readOnlyOrig );

			return narrowedProxy;
		}
		else {

			if ( object != null ) {
				final LazyInitializer li = ( (HibernateProxy) proxy ).getHibernateLazyInitializer();
				li.setImplementation( object );
			}
			return proxy;
		}
	}

	@Override
	public Object unproxy(Object maybeProxy) throws HibernateException {
		if ( maybeProxy instanceof HibernateProxy ) {
			final HibernateProxy proxy = (HibernateProxy) maybeProxy;
			final LazyInitializer li = proxy.getHibernateLazyInitializer();
			if ( li.isUninitialized() ) {
				throw new PersistentObjectException(
						"object was an uninitialized proxy for " + li.getEntityName()
				);
			}
			//unwrap the object and return
			return li.getImplementation();
		}
		else {
			return maybeProxy;
		}
	}

	public boolean contains(Object collection, Object childObject, SharedSessionContractImplementor session) {
		// we do not have to worry about queued additions to uninitialized
		// collections, since they can only occur for inverse collections!
		Iterator elems = getElementsIterator( collection, session );
		while ( elems.hasNext() ) {
			Object element = elems.next();
			// worrying about proxies is perhaps a little bit of overkill here...
			if ( element instanceof HibernateProxy ) {
				LazyInitializer li = ( (HibernateProxy) element ).getHibernateLazyInitializer();
				if ( !li.isUninitialized() ) {
					element = li.getImplementation();
				}
			}
			if ( element == childObject ) {
				return true;
			}
		}
		return false;
	}

	private void checkContainer(Container c, Set expectedInitializedObjects, Set expectedReadOnlyObjects, Session s) {
		checkObject( c, expectedInitializedObjects, expectedReadOnlyObjects, s );
		if ( ! expectedInitializedObjects.contains( c ) ) {
			return;
		}
		checkObject( c.getNoProxyInfo(), expectedInitializedObjects, expectedReadOnlyObjects, s);
		checkObject( c.getProxyInfo(), expectedInitializedObjects, expectedReadOnlyObjects, s);
		checkObject( c.getNonLazyInfo(), expectedInitializedObjects, expectedReadOnlyObjects, s );
		checkObject( c.getNoProxyOwner(), expectedInitializedObjects, expectedReadOnlyObjects, s );
		checkObject( c.getProxyOwner(), expectedInitializedObjects, expectedReadOnlyObjects, s );
		checkObject( c.getNonLazyOwner(), expectedInitializedObjects, expectedReadOnlyObjects, s );
		if ( Hibernate.isInitialized( c.getLazyDataPoints() ) ) {
			for ( Iterator it=c.getLazyDataPoints().iterator(); it.hasNext(); ) {
				checkObject( it.next(), expectedInitializedObjects, expectedReadOnlyObjects, s );
			}
		}
		for ( Iterator it=c.getNonLazyJoinDataPoints().iterator(); it.hasNext(); ) {
			checkObject( it.next(), expectedInitializedObjects, expectedReadOnlyObjects, s );
		}
		for ( Iterator it=c.getNonLazySelectDataPoints().iterator(); it.hasNext(); ) {
			checkObject( it.next(), expectedInitializedObjects, expectedReadOnlyObjects, s );
		}
	}

	@Override
	public void sessionFactoryCreated(SessionFactory factory) {
		if ( observers == null ) {
			return;
		}

		for ( SessionFactoryObserver observer : observers ) {
			observer.sessionFactoryCreated( factory );
		}
	}

	@Override
	public <R extends Service> R getService(Class<R> serviceRole) {

		//HHH-11051 cache EventListenerRegistry
		if ( serviceRole.equals( EventListenerRegistry.class ) ) {
			if ( cachedEventListenerRegistry == null ) {
				cachedEventListenerRegistry = (EventListenerRegistry) super.getService( serviceRole );
			}
			return (R) cachedEventListenerRegistry;
		}

		return super.getService( serviceRole );
	}

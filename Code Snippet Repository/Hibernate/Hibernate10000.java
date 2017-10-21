	@Override
	public Object mapToIdFromEntity(Object data) {
		if ( data == null ) {
			return null;
		}

		if ( data instanceof HibernateProxy ) {
			final HibernateProxy hibernateProxy = (HibernateProxy) data;
			return hibernateProxy.getHibernateLazyInitializer().getIdentifier();
		}
		else {
			final Getter getter = ReflectionTools.getGetter( data.getClass(), propertyData, getServiceRegistry() );
			return getter.get( data );
		}
	}

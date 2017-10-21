	@Override
	public void reassociateProxy(Object value, Serializable id) throws MappingException {
		if ( value instanceof HibernateProxy ) {
			LOG.debugf( "Setting proxy identifier: %s", id );
			final HibernateProxy proxy = (HibernateProxy) value;
			final LazyInitializer li = proxy.getHibernateLazyInitializer();
			li.setIdentifier( id );
			reassociateProxy( li, proxy );
		}
	}

	@Override
	public boolean reassociateIfUninitializedProxy(Object value) throws MappingException {
		if ( !Hibernate.isInitialized( value ) ) {
			final HibernateProxy proxy = (HibernateProxy) value;
			final LazyInitializer li = proxy.getHibernateLazyInitializer();
			reassociateProxy( li, proxy );
			return true;
		}
		else {
			return false;
		}
	}

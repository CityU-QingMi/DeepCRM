	@Override
	public void mapToMapFromEntity(Map<String, Object> data, Object obj) {
		if ( obj == null ) {
			data.put( propertyData.getName(), null );
		}
		else {
			if ( obj instanceof HibernateProxy ) {
				final HibernateProxy hibernateProxy = (HibernateProxy) obj;
				data.put( propertyData.getName(), hibernateProxy.getHibernateLazyInitializer().getIdentifier() );
			}
			else {
				final Getter getter = ReflectionTools.getGetter( obj.getClass(), propertyData, getServiceRegistry() );
				data.put( propertyData.getName(), getter.get( obj ) );
			}
		}
	}

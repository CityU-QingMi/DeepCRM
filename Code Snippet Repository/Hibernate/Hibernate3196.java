	public void toString(Iterable<Map.Entry<EntityKey, Object>> entitiesByEntityKey) throws HibernateException {
		if ( !LOG.isDebugEnabled() || !entitiesByEntityKey.iterator().hasNext() ) {
			return;
		}

		LOG.debug( "Listing entities:" );
		int i = 0;
		for ( Map.Entry<EntityKey, Object> entityKeyAndEntity : entitiesByEntityKey ) {
			if ( i++ > 20 ) {
				LOG.debug( "More......" );
				break;
			}
			LOG.debug( toString( entityKeyAndEntity.getKey().getEntityName(), entityKeyAndEntity.getValue() ) );
		}
	}

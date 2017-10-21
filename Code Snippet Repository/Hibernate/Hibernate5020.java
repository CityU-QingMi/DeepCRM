	public void register(BasicType type, String[] keys) {
		if ( locked ) {
			throw new HibernateException( "Can not alter TypeRegistry at this time" );
		}

		if ( type == null ) {
			throw new HibernateException( "Type to register cannot be null" );
		}

		if ( keys == null || keys.length == 0 ) {
			LOG.typeDefinedNoRegistrationKeys( type );
			return;
		}

		for ( String key : keys ) {
			// be safe...
			if ( key == null ) {
				continue;
			}
			LOG.debugf( "Adding type registration %s -> %s", key, type );
			final Type old = registry.put( key, type );
			if ( old != null && old != type ) {
				LOG.typeRegistrationOverridesPrevious( key, old );
			}
		}
	}

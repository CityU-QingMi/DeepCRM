	@Override
	public Object getIdentifier(Object entity) {
		if ( entity == null ) {
			throw new IllegalArgumentException( "Passed entity cannot be null" );
		}

		if ( entity instanceof HibernateProxy ) {
			return ((HibernateProxy) entity).getHibernateLazyInitializer().getIdentifier();
		}
		else if ( entity instanceof ManagedEntity ) {
			EntityEntry entityEntry = ((ManagedEntity) entity).$$_hibernate_getEntityEntry();
			if ( entityEntry != null ) {
				return entityEntry.getId();
			}
			else {
				// HHH-11426 - best effort to deal with the case of detached entities
				log.debug( "javax.persistence.PersistenceUnitUtil.getIdentifier may not be able to read identifier of a detached entity" );
				return getIdentifierFromPersister( entity );
			}
		}
		else {
			log.debugf(
					"javax.persistence.PersistenceUnitUtil.getIdentifier is only intended to work with enhanced entities " +
							"(although Hibernate also adapts this support to its proxies); " +
							"however the passed entity was not enhanced (nor a proxy).. may not be able to read identifier"
			);
			return getIdentifierFromPersister( entity );
		}
	}

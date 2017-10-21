	@Override
	public EntityManagerFactory createEntityManagerFactory(String persistenceUnitName, Map properties) {
		log.tracef( "Starting createEntityManagerFactory for persistenceUnitName %s", persistenceUnitName );

		try {
			final EntityManagerFactoryBuilder builder = getEntityManagerFactoryBuilderOrNull( persistenceUnitName, properties );
			if ( builder == null ) {
				log.trace( "Could not obtain matching EntityManagerFactoryBuilder, returning null" );
				return null;
			}
			else {
				return builder.build();
			}
		}
		catch (PersistenceException pe) {
			throw pe;
		}
		catch (Exception e) {
			log.debug( "Unable to build entity manager factory", e );
			throw new PersistenceException( "Unable to build entity manager factory", e );
		}
	}

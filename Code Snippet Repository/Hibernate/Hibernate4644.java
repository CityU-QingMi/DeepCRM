	@Override
	public synchronized void deRegisterChild(ServiceRegistryImplementor child) {
		if ( childRegistries == null ) {
			throw new IllegalStateException( "No child ServiceRegistry registrations found" );
		}
		childRegistries.remove( child );
		if ( childRegistries.isEmpty() ) {
			if ( autoCloseRegistry ) {
				log.debug(
						"Implicitly destroying ServiceRegistry on de-registration " +
								"of all child ServiceRegistries"
				);
				destroy();
			}
			else {
				log.debug(
						"Skipping implicitly destroying ServiceRegistry on de-registration " +
								"of all child ServiceRegistries"
				);
			}
		}
	}

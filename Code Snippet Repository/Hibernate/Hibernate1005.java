	@Override
	public void deRegisterChild(ServiceRegistryImplementor child) {
		if ( childRegistries == null ) {
			throw new IllegalStateException( "No child ServiceRegistry registrations found" );
		}
		childRegistries.remove( child );
		if ( childRegistries.isEmpty() ) {
			if ( autoCloseRegistry ) {
				LOG.debug(
						"Implicitly destroying Boot-strap registry on de-registration " +
								"of all child ServiceRegistries"
				);
				destroy();
			}
			else {
				LOG.debug(
						"Skipping implicitly destroying Boot-strap registry on de-registration " +
								"of all child ServiceRegistries"
				);
			}
		}
	}

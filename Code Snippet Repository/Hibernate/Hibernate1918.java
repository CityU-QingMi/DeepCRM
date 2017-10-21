	@Override
	public void setReadOnly(boolean readOnly, Object entity) {
		if ( readOnly == isReadOnly() ) {
			// simply return since the status is not being changed
			return;
		}
		if ( readOnly ) {
			setStatus( Status.READ_ONLY );
			loadedState = null;
		}
		else {
			if ( ! persister.isMutable() ) {
				throw new IllegalStateException( "Cannot make an immutable entity modifiable." );
			}
			setStatus( Status.MANAGED );
			loadedState = getPersister().getPropertyValues( entity );
			getPersistenceContext().getNaturalIdHelper().manageLocalNaturalIdCrossReference(
					persister,
					id,
					loadedState,
					null,
					CachedNaturalIdValueSource.LOAD
			);
		}
	}

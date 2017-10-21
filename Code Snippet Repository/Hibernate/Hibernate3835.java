	private void registerNonExists(EntityFetch fetch) {
		final EntityType fetchedType = fetch.getFetchedType();
		if ( ! fetchedType.isOneToOne() ) {
			return;
		}

		final EntityReferenceProcessingState fetchOwnerState = getOwnerProcessingState( fetch );
		if ( fetchOwnerState == null ) {
			throw new IllegalStateException( "Could not locate fetch owner state" );
		}

		final EntityKey ownerEntityKey = fetchOwnerState.getEntityKey();
		if ( ownerEntityKey == null ) {
			throw new IllegalStateException( "Could not locate fetch owner EntityKey" );
		}

		session.getPersistenceContext().addNullProperty(
				ownerEntityKey,
				fetchedType.getPropertyName()
		);
	}

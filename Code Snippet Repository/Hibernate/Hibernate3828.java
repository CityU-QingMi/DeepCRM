	private void handleMissingIdentifier(ResultSetProcessingContext context) {
		if ( EntityFetch.class.isInstance( entityReference ) ) {
			final EntityFetch fetch = (EntityFetch) entityReference;
			final EntityType fetchedType = fetch.getFetchedType();
			if ( ! fetchedType.isOneToOne() ) {
				return;
			}

			final EntityReferenceProcessingState fetchOwnerState = context.getOwnerProcessingState( fetch );
			if ( fetchOwnerState == null ) {
				throw new IllegalStateException( "Could not locate fetch owner state" );
			}

			final EntityKey ownerEntityKey = fetchOwnerState.getEntityKey();
			if ( ownerEntityKey != null ) {
				context.getSession().getPersistenceContext().addNullProperty(
						ownerEntityKey,
						fetchedType.getPropertyName()
				);
			}
		}
	}

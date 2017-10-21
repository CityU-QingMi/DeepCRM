	private void resolveEntityKey(
			ResultSet resultSet,
			ResultSetProcessingContextImpl context,
			FetchSource fetchSource) throws SQLException {
		// Resolve any bidirectional entity references first.
		for ( BidirectionalEntityReference bidirectionalEntityReference : fetchSource.getBidirectionalEntityReferences() ) {
			final EntityReferenceInitializer targetEntityReferenceInitializer = entityInitializerByEntityReference.get(
					bidirectionalEntityReference.getTargetEntityReference()
			);
			resolveEntityKey(
					resultSet,
					context,
					targetEntityReferenceInitializer
			);
			targetEntityReferenceInitializer.hydrateEntityState( resultSet, context );
		}
		for ( Fetch fetch : fetchSource.getFetches() ) {
			if ( EntityFetch.class.isInstance( fetch ) ) {
				final EntityFetch entityFetch = (EntityFetch) fetch;
				final EntityReferenceInitializer  entityReferenceInitializer = entityInitializerByEntityReference.get(
						entityFetch
				);
				if ( entityReferenceInitializer != null ) {
					resolveEntityKey(
							resultSet,
							context,
							entityReferenceInitializer
					);
					entityReferenceInitializer.hydrateEntityState( resultSet, context );
				}
			}
			else if ( CompositeFetch.class.isInstance( fetch ) ) {
				resolveEntityKey(
						resultSet,
						context,
						(CompositeFetch) fetch
				);
			}
		}
	}

	@Override
	public void resolveEntityKey(ResultSet resultSet, ResultSetProcessingContextImpl context) {

		final EntityReferenceProcessingState processingState = context.getProcessingState( entityReference );

		// see if we already have an EntityKey associated with this EntityReference in the processing state.
		// if we do, this should have come from the optional entity identifier...
		final EntityKey entityKey = processingState.getEntityKey();
		if ( entityKey != null ) {
			log.debugf(
					"On call to EntityIdentifierReaderImpl#resolve, EntityKey was already known; " +
							"should only happen on root returns with an optional identifier specified"
			);
			return;
		}

		// Look for the hydrated form
		final Object identifierHydratedForm = processingState.getIdentifierHydratedForm();
		if ( identifierHydratedForm == null ) {
			// we need to register the missing identifier, but that happens later after all readers have had a chance
			// to resolve its EntityKey
			return;
		}

		final Type identifierType = entityReference.getEntityPersister().getIdentifierType();
		final Serializable resolvedId = (Serializable) identifierType.resolve(
				identifierHydratedForm,
				context.getSession(),
				null
		);
		if ( resolvedId != null ) {
			processingState.registerEntityKey(
					context.getSession().generateEntityKey( resolvedId, entityReference.getEntityPersister() )
			);
		}
	}

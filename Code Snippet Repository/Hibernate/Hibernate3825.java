	@Override
	public void hydrateIdentifier(ResultSet resultSet, ResultSetProcessingContextImpl context) throws SQLException {

		final EntityReferenceProcessingState processingState = context.getProcessingState( entityReference );

		// get any previously registered identifier hydrated-state
		Object identifierHydratedForm = processingState.getIdentifierHydratedForm();
		if ( identifierHydratedForm == null ) {
			// if there is none, read it from the result set
			identifierHydratedForm = readIdentifierHydratedState( resultSet, context );

			// broadcast the fact that a hydrated identifier value just became associated with
			// this entity reference
			processingState.registerIdentifierHydratedForm( identifierHydratedForm );
		}
	}

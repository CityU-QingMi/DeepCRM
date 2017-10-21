		@Override
		public Object readRow(ResultSet resultSet, ResultSetProcessingContextImpl context) throws SQLException {
			final ResultSetProcessingContext.EntityReferenceProcessingState processingState =
					rootReturnReader.getIdentifierResolutionContext( context );
			// if the entity reference we are hydrating is a Return, it is possible that its EntityKey is
			// supplied by the QueryParameter optional entity information
			if ( context.shouldUseOptionalEntityInformation() && context.getQueryParameters().getOptionalId() != null ) {
				final EntityKey entityKey = context.getSession().generateEntityKey(
						context.getQueryParameters().getOptionalId(),
						processingState.getEntityReference().getEntityPersister()
				);
				processingState.registerIdentifierHydratedForm( entityKey.getIdentifier() );
				processingState.registerEntityKey( entityKey );
			}
			return super.readRow( resultSet, context );
		}

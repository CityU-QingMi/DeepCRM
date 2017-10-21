	private String getConcreteEntityTypeName(
			ResultSet resultSet,
			ResultSetProcessingContext context,
			EntityKey entityKey) {
		final Loadable loadable = (Loadable) entityReference.getEntityPersister();
		if ( ! loadable.hasSubclasses() ) {
			return entityReference.getEntityPersister().getEntityName();
		}

		final Object discriminatorValue;
		try {
			discriminatorValue = loadable.getDiscriminatorType().nullSafeGet(
					resultSet,
					entityReferenceAliases.getColumnAliases().getSuffixedDiscriminatorAlias(),
					context.getSession(),
					null
			);
		}
		catch (SQLException e) {
			throw context.getSession().getFactory().getServiceRegistry().getService( JdbcServices.class ).getSqlExceptionHelper().convert(
					e,
					"Could not read discriminator value from ResultSet"
			);
		}

		final String result = loadable.getSubclassForDiscriminatorValue( discriminatorValue );

		if ( result == null ) {
			// whoops! we got an instance of another class hierarchy branch
			throw new WrongClassException(
					"Discriminator: " + discriminatorValue,
					entityKey.getIdentifier(),
					entityReference.getEntityPersister().getEntityName()
			);
		}

		return result;
	}

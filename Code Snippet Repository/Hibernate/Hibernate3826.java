	private Object readIdentifierHydratedState(ResultSet resultSet, ResultSetProcessingContext context)
			throws SQLException {
		try {
			return entityReference.getEntityPersister().getIdentifierType().hydrate(
					resultSet,
					entityReferenceAliases.getColumnAliases().getSuffixedKeyAliases(),
					context.getSession(),
					null
			);
		}
		catch (Exception e) {
			throw new HibernateException(
					"Encountered problem trying to hydrate identifier for entity ["
							+ entityReference.getEntityPersister() + "]",
					e
			);
		}
	}

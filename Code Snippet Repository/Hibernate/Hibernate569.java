	public void checkNoUnresolvedActionsAfterOperation() throws PropertyValueException {
		if ( isEmpty() ) {
			LOG.trace( "No entity insert actions have non-nullable, transient entity dependencies." );
		}
		else {
			final AbstractEntityInsertAction firstDependentAction =
					dependenciesByAction.keySet().iterator().next();

			logCannotResolveNonNullableTransientDependencies( firstDependentAction.getSession() );

			final NonNullableTransientDependencies nonNullableTransientDependencies =
					dependenciesByAction.get( firstDependentAction );
			final Object firstTransientDependency =
					nonNullableTransientDependencies.getNonNullableTransientEntities().iterator().next();
			final String firstPropertyPath =
					nonNullableTransientDependencies.getNonNullableTransientPropertyPaths( firstTransientDependency ).iterator().next();

			throw new TransientPropertyValueException(
					"Not-null property references a transient value - transient instance must be saved before current operation",
					firstDependentAction.getSession().guessEntityName( firstTransientDependency ),
					firstDependentAction.getEntityName(),
					firstPropertyPath
			);
		}
	}

	private static List<ProcedureCallMementoImpl.ParameterMemento> toParameterMementos(List<ParameterRegistrationImplementor<?>> registeredParameters) {
		if ( registeredParameters == null ) {
			return null;
		}

		final List<ProcedureCallMementoImpl.ParameterMemento> copy = CollectionHelper.arrayList( registeredParameters.size() );
		for ( ParameterRegistrationImplementor registration : registeredParameters ) {
			copy.add( ProcedureCallMementoImpl.ParameterMemento.fromRegistration( registration ) );
		}
		return copy;
	}

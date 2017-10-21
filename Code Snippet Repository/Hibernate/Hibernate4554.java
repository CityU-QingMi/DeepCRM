	public <P> void validate(Type paramType, Object bind, TemporalType temporalType) {
		if ( bind == null || paramType == null ) {
			// nothing we can check
			return;
		}
		final Class parameterType = paramType.getReturnedClass();
		if ( parameterType == null ) {
			// nothing we can check
			return;
		}

		if ( Collection.class.isInstance( bind ) && !Collection.class.isAssignableFrom( parameterType ) ) {
			// we have a collection passed in where we are expecting a non-collection.
			// 		NOTE : this can happen in Hibernate's notion of "parameter list" binding
			// 		NOTE2 : the case of a collection value and an expected collection (if that can even happen)
			//			will fall through to the main check.
			validateCollectionValuedParameterBinding( parameterType, (Collection) bind, temporalType );
		}
		else if ( bind.getClass().isArray() ) {
			validateArrayValuedParameterBinding( parameterType, bind, temporalType );
		}
		else {
			if ( !isValidBindValue( parameterType, bind, temporalType ) ) {
				throw new IllegalArgumentException(
						String.format(
								"Parameter value [%s] did not match expected type [%s (%s)]",
								bind,
								parameterType.getName(),
								extractName( temporalType )
						)
				);
			}
		}
	}

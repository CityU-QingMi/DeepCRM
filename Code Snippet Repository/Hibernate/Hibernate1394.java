		static ParameterDefinition from(
				ParameterStrategy parameterStrategy,
				StoredProcedureParameter parameterAnnotation,
				int adjustedPosition,
				Map<String, Object> queryHintMap) {
			// see if there was an explicit hint for this parameter in regards to NULL passing
			final Object explicitNullPassingHint;
			if ( parameterStrategy == ParameterStrategy.NAMED ) {
				explicitNullPassingHint = queryHintMap.get( AvailableSettings.PROCEDURE_NULL_PARAM_PASSING + '.' + parameterAnnotation.name() );
			}
			else {
				explicitNullPassingHint = queryHintMap.get( AvailableSettings.PROCEDURE_NULL_PARAM_PASSING + '.' + adjustedPosition );
			}

			return new ParameterDefinition(
					adjustedPosition,
					parameterAnnotation,
					interpretBoolean( explicitNullPassingHint )
			);
		}

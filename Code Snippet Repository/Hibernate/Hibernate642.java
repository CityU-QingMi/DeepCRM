	@Override
	public String determineGeneratorName(GenerationType generationType, GeneratorNameDeterminationContext context) {
		if ( delegates != null ) {
			for ( IdGeneratorStrategyInterpreter delegate : delegates ) {
				final String result = delegate.determineGeneratorName( generationType, context );
				if ( result != null ) {
					return result;
				}
			}
		}
		return fallbackInterpreter.determineGeneratorName( generationType, context );
	}

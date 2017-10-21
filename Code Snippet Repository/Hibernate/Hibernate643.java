	@Override
	public void interpretTableGenerator(
			TableGenerator tableGeneratorAnnotation,
			IdentifierGeneratorDefinition.Builder definitionBuilder) {
		fallbackInterpreter.interpretTableGenerator( tableGeneratorAnnotation, definitionBuilder );

		if ( delegates != null ) {
			for ( IdGeneratorStrategyInterpreter delegate : delegates ) {
				delegate.interpretTableGenerator( tableGeneratorAnnotation, definitionBuilder );
			}
		}
	}

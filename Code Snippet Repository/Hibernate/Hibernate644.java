	@Override
	public void interpretSequenceGenerator(
			SequenceGenerator sequenceGeneratorAnnotation,
			IdentifierGeneratorDefinition.Builder definitionBuilder) {
		fallbackInterpreter.interpretSequenceGenerator( sequenceGeneratorAnnotation, definitionBuilder );

		if ( delegates != null ) {
			for ( IdGeneratorStrategyInterpreter delegate : delegates ) {
				delegate.interpretSequenceGenerator( sequenceGeneratorAnnotation, definitionBuilder );
			}
		}
	}

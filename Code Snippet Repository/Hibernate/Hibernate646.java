		@Override
		@SuppressWarnings("")
		public void interpretSequenceGenerator(
				SequenceGenerator sequenceGeneratorAnnotation,
				IdentifierGeneratorDefinition.Builder definitionBuilder) {
			definitionBuilder.setName( sequenceGeneratorAnnotation.name() );

			definitionBuilder.setStrategy( "seqhilo" );

			if ( !BinderHelper.isEmptyAnnotationValue( sequenceGeneratorAnnotation.sequenceName() ) ) {
				definitionBuilder.addParam( org.hibernate.id.SequenceGenerator.SEQUENCE, sequenceGeneratorAnnotation.sequenceName() );
			}
			//FIXME: work on initialValue() through SequenceGenerator.PARAMETERS
			//		steve : or just use o.h.id.enhanced.SequenceStyleGenerator
			if ( sequenceGeneratorAnnotation.initialValue() != 1 ) {
				log.unsupportedInitialValue( AvailableSettings.USE_NEW_ID_GENERATOR_MAPPINGS );
			}
			definitionBuilder.addParam( SequenceHiLoGenerator.MAX_LO, String.valueOf( sequenceGeneratorAnnotation.allocationSize() - 1 ) );
		}

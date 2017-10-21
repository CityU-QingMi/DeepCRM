		@Override
		public void interpretSequenceGenerator(
				SequenceGenerator sequenceGeneratorAnnotation,
				IdentifierGeneratorDefinition.Builder definitionBuilder) {
			definitionBuilder.setName( sequenceGeneratorAnnotation.name() );
			definitionBuilder.setStrategy( SequenceStyleGenerator.class.getName() );

			if ( !BinderHelper.isEmptyAnnotationValue( sequenceGeneratorAnnotation.catalog() ) ) {
				definitionBuilder.addParam(
						PersistentIdentifierGenerator.CATALOG,
						sequenceGeneratorAnnotation.catalog()
				);
			}
			if ( !BinderHelper.isEmptyAnnotationValue( sequenceGeneratorAnnotation.schema() ) ) {
				definitionBuilder.addParam(
						PersistentIdentifierGenerator.SCHEMA,
						sequenceGeneratorAnnotation.schema()
				);
			}
			if ( !BinderHelper.isEmptyAnnotationValue( sequenceGeneratorAnnotation.sequenceName() ) ) {
				definitionBuilder.addParam(
						SequenceStyleGenerator.SEQUENCE_PARAM,
						sequenceGeneratorAnnotation.sequenceName()
				);
			}

			definitionBuilder.addParam(
					SequenceStyleGenerator.INCREMENT_PARAM,
					String.valueOf( sequenceGeneratorAnnotation.allocationSize() )
			);
			definitionBuilder.addParam(
					SequenceStyleGenerator.INITIAL_PARAM,
					String.valueOf( sequenceGeneratorAnnotation.initialValue() )
			);
		}

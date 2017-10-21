	@Override
	public boolean process(final Set<? extends TypeElement> annotations, final RoundEnvironment roundEnvironment) {
		// see also METAGEN-45
		if ( roundEnvironment.processingOver() || annotations.size() == 0 ) {
			return ALLOW_OTHER_PROCESSORS_TO_CLAIM_ANNOTATIONS;
		}

		if ( context.isFullyXmlConfigured() ) {
			context.logMessage(
					Diagnostic.Kind.OTHER,
					"Skipping the processing of annotations since persistence unit is purely xml configured."
			);
			return ALLOW_OTHER_PROCESSORS_TO_CLAIM_ANNOTATIONS;
		}

		Set<? extends Element> elements = roundEnvironment.getRootElements();
		for ( Element element : elements ) {
			if ( isJPAEntity( element ) ) {
				context.logMessage( Diagnostic.Kind.OTHER, "Processing annotated class " + element.toString() );
				handleRootElementAnnotationMirrors( element );
			}
		}

		createMetaModelClasses();
		return ALLOW_OTHER_PROCESSORS_TO_CLAIM_ANNOTATIONS;
	}

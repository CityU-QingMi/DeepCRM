	@Override
	public void init(ProcessingEnvironment env) {
		super.init( env );
		context = new Context( env );
		context.logMessage(
				Diagnostic.Kind.NOTE, "Hibernate JPA 2 Static-Metamodel Generator " + Version.getVersionString()
		);

		String tmp = env.getOptions().get( JPAMetaModelEntityProcessor.ADD_GENERATED_ANNOTATION );
		if ( tmp != null ) {
			boolean addGeneratedAnnotation = Boolean.parseBoolean( tmp );
			context.setAddGeneratedAnnotation( addGeneratedAnnotation );
		}

		tmp = env.getOptions().get( JPAMetaModelEntityProcessor.ADD_GENERATION_DATE );
		boolean addGenerationDate = Boolean.parseBoolean( tmp );
		context.setAddGenerationDate( addGenerationDate );

		tmp = env.getOptions().get( JPAMetaModelEntityProcessor.ADD_SUPPRESS_WARNINGS_ANNOTATION );
		boolean addSuppressWarningsAnnotation = Boolean.parseBoolean( tmp );
		context.setAddSuppressWarningsAnnotation( addSuppressWarningsAnnotation );

		tmp = env.getOptions().get( JPAMetaModelEntityProcessor.FULLY_ANNOTATION_CONFIGURED_OPTION );
		boolean fullyAnnotationConfigured = Boolean.parseBoolean( tmp );

		if ( !fullyAnnotationConfigured ) {
			JpaDescriptorParser parser = new JpaDescriptorParser( context );
			parser.parseXml();
			if ( context.isFullyXmlConfigured() ) {
				createMetaModelClasses();
			}
		}
	}

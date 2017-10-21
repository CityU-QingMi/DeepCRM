	public static void bindPackage(String packageName, MetadataBuildingContext context) {
		XPackage pckg;
		try {
			pckg = context.getBuildingOptions().getReflectionManager().packageForName( packageName );
		}
		catch (ClassLoadingException e) {
			LOG.packageNotFound( packageName );
			return;
		}
		catch ( ClassNotFoundException cnf ) {
			LOG.packageNotFound( packageName );
			return;
		}

		if ( pckg.isAnnotationPresent( SequenceGenerator.class ) ) {
			SequenceGenerator ann = pckg.getAnnotation( SequenceGenerator.class );
			IdentifierGeneratorDefinition idGen = buildIdGenerator( ann, context );
			context.getMetadataCollector().addIdentifierGenerator( idGen );
			if ( LOG.isTraceEnabled() ) {
				LOG.tracev( "Add sequence generator with name: {0}", idGen.getName() );
			}
		}

		if ( pckg.isAnnotationPresent( TableGenerator.class ) ) {
			TableGenerator ann = pckg.getAnnotation( TableGenerator.class );
			IdentifierGeneratorDefinition idGen = buildIdGenerator( ann, context );
			context.getMetadataCollector().addIdentifierGenerator( idGen );
		}

		bindGenericGenerators( pckg, context );
		bindQueries( pckg, context );
		bindFilterDefs( pckg, context );
		bindTypeDefs( pckg, context );
		bindFetchProfiles( pckg, context );
		BinderHelper.bindAnyMetaDefs( pckg, context );

	}

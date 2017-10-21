	private List<String> createJavaOptions() {
		List<String> options = new ArrayList<String>();
		options.add( "-d" );
		options.add( TestUtil.getOutBaseDir().getAbsolutePath() );

		// pass orm files if specified
		if ( !xmlMappingFiles.isEmpty() ) {
			StringBuilder builder = new StringBuilder();
			builder.append( ANNOTATION_PROCESSOR_OPTION_PREFIX );
			builder.append( JPAMetaModelEntityProcessor.ORM_XML_OPTION );
			builder.append( "=" );
			for ( String ormFile : xmlMappingFiles ) {
				builder.append( ormFile );
				builder.append( "," );
			}
			builder.deleteCharAt( builder.length() - 1 );
			options.add( builder.toString() );
		}

		// add any additional options specified by the test
		for ( Map.Entry<String, String> entry : processorOptions.entrySet() ) {
			StringBuilder builder = new StringBuilder();
			builder.append( ANNOTATION_PROCESSOR_OPTION_PREFIX );
			builder.append( entry.getKey() );
			builder.append( "=" );
			builder.append( entry.getValue() );
			options.add( builder.toString() );
		}
		return options;
	}

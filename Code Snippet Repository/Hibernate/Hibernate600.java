	@SuppressWarnings("")
	private boolean acceptAsMappingFile(MappingFileDescriptor mappingFileDescriptor, boolean rootUrl) {
		if ( mappingFileDescriptor.getName().endsWith( "hbm.xml" ) ) {
			return options.canDetectHibernateMappingFiles();
		}

		if ( mappingFileDescriptor.getName().endsWith( "META-INF/orm.xml" ) ) {
			if ( environment.getExplicitlyListedMappingFiles().contains( "META-INF/orm.xml" ) ) {
				// if the user explicitly listed META-INF/orm.xml, only except the root one
				//
				// not sure why exactly, but this is what the old code does
				return rootUrl;
			}
			return true;
		}

		return environment.getExplicitlyListedMappingFiles().contains( mappingFileDescriptor.getName() );
	}

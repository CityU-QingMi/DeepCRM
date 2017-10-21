	public Context(ProcessingEnvironment pe) {
		this.pe = pe;

		if ( pe.getOptions().get( JPAMetaModelEntityProcessor.PERSISTENCE_XML_OPTION ) != null ) {
			String tmp = pe.getOptions().get( JPAMetaModelEntityProcessor.PERSISTENCE_XML_OPTION );
			if ( !tmp.startsWith( Constants.PATH_SEPARATOR ) ) {
				tmp = Constants.PATH_SEPARATOR + tmp;
			}
			persistenceXmlLocation = tmp;
		}
		else {
			persistenceXmlLocation = DEFAULT_PERSISTENCE_XML_LOCATION;
		}

		if ( pe.getOptions().get( JPAMetaModelEntityProcessor.ORM_XML_OPTION ) != null ) {
			String tmp = pe.getOptions().get( JPAMetaModelEntityProcessor.ORM_XML_OPTION );
			ormXmlFiles = new ArrayList<String>();
			for ( String ormFile : tmp.split( "," ) ) {
				if ( !ormFile.startsWith( Constants.PATH_SEPARATOR ) ) {
					ormFile = Constants.PATH_SEPARATOR + ormFile;
				}
				ormXmlFiles.add( ormFile );
			}
		}
		else {
			ormXmlFiles = Collections.emptyList();
		}

		lazyXmlParsing = Boolean.parseBoolean( pe.getOptions().get( JPAMetaModelEntityProcessor.LAZY_XML_PARSING ) );
		logDebug = Boolean.parseBoolean( pe.getOptions().get( JPAMetaModelEntityProcessor.DEBUG_OPTION ) );
	}

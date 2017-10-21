	private Collection<String> determineMappingFileNames() {
		Collection<String> mappingFileNames = new ArrayList<String>();

		Persistence persistence = getPersistence();
		if ( persistence != null ) {
			// get mapping file names from persistence.xml
			List<Persistence.PersistenceUnit> persistenceUnits = persistence.getPersistenceUnit();
			for ( Persistence.PersistenceUnit unit : persistenceUnits ) {
				mappingFileNames.addAll( unit.getMappingFile() );
			}
		}

		// /META-INF/orm.xml is implicit
		mappingFileNames.add( DEFAULT_ORM_XML_LOCATION );

		// not really part of the official spec, but the processor allows to specify mapping files directly as
		// command line options
		mappingFileNames.addAll( context.getOrmXmlFiles() );
		return mappingFileNames;
	}

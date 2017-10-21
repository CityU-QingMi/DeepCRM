	private static void writeSerFile(Serializable binding, File xmlFile, File serFile) {
		try {
			log.debugf( "Writing cache file for: %s to: %s", xmlFile.getAbsolutePath(), serFile.getAbsolutePath() );
			SerializationHelper.serialize( binding, new FileOutputStream( serFile ) );
			boolean success = serFile.setLastModified( System.currentTimeMillis() );
			if ( !success ) {
				log.warn( "Could not update cacheable hbm.xml bin file timestamp" );
			}
		}
		catch ( Exception e ) {
			log.unableToWriteCachedFile( serFile.getAbsolutePath(), e.getMessage() );
		}
	}

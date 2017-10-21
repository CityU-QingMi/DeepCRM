	private InputSource resolveOnClassPath(String publicId, String systemId, String namespace) {
		InputSource source = null;
		String path = "org/hibernate/" + systemId.substring( namespace.length() );
		InputStream dtdStream = resolveInHibernateNamespace( path );
		if ( dtdStream == null ) {
			LOG.debugf( "Unable to locate [%s] on classpath", systemId );
			if ( systemId.substring( namespace.length() ).indexOf( "2.0" ) > -1 ) {
				LOG.usingOldDtd();
			}
		}
		else {
			LOG.debugf( "Located [%s] in classpath", systemId );
			source = new InputSource( dtdStream );
			source.setPublicId( publicId );
			source.setSystemId( systemId );
		}
		return source;
	}

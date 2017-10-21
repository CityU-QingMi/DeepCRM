	private InputSource buildInputSource(String publicId, String systemId, InputStream dtdStream, boolean resolved) {
		if ( dtdStream == null ) {
			LOG.tracev( "Unable to locate [{0}] on classpath", systemId );
			return null;
		}
		LOG.tracev( "Located [{0}] in classpath", systemId );
		InputSource source = new InputSource( dtdStream );
		source.setPublicId( publicId );
		source.setSystemId( systemId );
		this.resolved = resolved;
		return source;
	}

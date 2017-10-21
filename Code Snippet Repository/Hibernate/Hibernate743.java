	@Override
	public Binding doBind(Binder binder) {
		try {
			InputStream stream = url.openStream();
			return InputStreamXmlSource.doBind( binder, stream, getOrigin(), true );
		}
		catch (UnknownHostException e) {
			throw new MappingNotFoundException( "Invalid URL", e, getOrigin() );
		}
		catch (IOException e) {
			throw new MappingException( "Unable to open URL InputStream", e, getOrigin() );
		}
	}

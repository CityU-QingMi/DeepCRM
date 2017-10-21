	@Override
	public void prepare() {
		super.prepare();
		try {
			this.reader = charsetName != null ?
				new InputStreamReader( url.openStream(), charsetName ) :
				new InputStreamReader( url.openStream() );
		}
		catch (IOException e) {
			throw new SchemaManagementException(
					"Unable to open specified script source url [" + url + "] for reading"
			);
		}
	}

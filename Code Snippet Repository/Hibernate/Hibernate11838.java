	@Override
	public Geometry fromString(String string) {
		final WKTReader reader = new WKTReader();
		try {
			return reader.read( string );
		}
		catch ( ParseException e ) {
			throw new RuntimeException( String.format( "Can't parse string %s as WKT", string ) );
		}
	}

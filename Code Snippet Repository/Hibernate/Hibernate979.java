	public static AttributePath parse(String path) {
		if ( path == null ) {
			return null;
		}

		AttributePath attributePath = new AttributePath();
		for ( String part : path.split( "\\." ) ) {
			attributePath = attributePath.append( part );
		}
		return attributePath;
	}

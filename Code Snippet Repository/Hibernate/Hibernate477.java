	private void setOverwriteProperty(
			String hibernateStyleKey,
			String c3p0StyleKey,
			Map hibp,
			Properties c3p,
			Integer value) {
		if ( value != null ) {
			final String peeledC3p0Key = c3p0StyleKey.substring( 5 );
			c3p.put( peeledC3p0Key, String.valueOf( value ).trim() );
			if ( hibp.containsKey( c3p0StyleKey ) ) {
				warnPropertyConflict( hibernateStyleKey, c3p0StyleKey );
			}
			final String longC3p0StyleKey = "hibernate." + c3p0StyleKey;
			if ( hibp.containsKey( longC3p0StyleKey ) ) {
				warnPropertyConflict( hibernateStyleKey, longC3p0StyleKey );
			}
		}
	}

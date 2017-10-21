	@Override
	@SuppressWarnings("")
	public boolean endRead() {
		if ( loadingEntries != null ) {
			for ( Object[] entry : loadingEntries ) {
				map.put( entry[0], entry[1] );
			}
			loadingEntries = null;
		}
		return super.endRead();
	}

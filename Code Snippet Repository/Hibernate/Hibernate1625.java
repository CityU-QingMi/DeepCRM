	@Override
	@SuppressWarnings("")
	public void putAll(Map puts) {
		if ( puts.size() > 0 ) {
			initialize( true );
			for ( Object o : puts.entrySet() ) {
				final Entry entry = (Entry) o;
				put( entry.getKey(), entry.getValue() );
			}
		}
	}

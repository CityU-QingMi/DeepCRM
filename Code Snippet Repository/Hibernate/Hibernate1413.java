	public QueryHintDefinition(final QueryHint[] hints) {
		if ( hints == null || hints.length == 0 ) {
			hintsMap = Collections.emptyMap();
		}
		else {
			final Map<String, Object> hintsMap = new HashMap<String, Object>();
			for ( QueryHint hint : hints ) {
				hintsMap.put( hint.name(), hint.value() );
			}
			this.hintsMap = hintsMap;
		}
	}

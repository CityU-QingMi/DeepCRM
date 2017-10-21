	public String get(int typeCode, long size, int precision, int scale) throws MappingException {
		final Map<Long, String> map = weighted.get( typeCode );
		if ( map != null && map.size() > 0 ) {
			// iterate entries ordered by capacity to find first fit
			for ( Map.Entry<Long, String> entry: map.entrySet() ) {
				if ( size <= entry.getKey() ) {
					return replace( entry.getValue(), size, precision, scale );
				}
			}
		}

		// if we get here one of 2 things happened:
		//		1) There was no weighted registration for that typeCode
		//		2) There was no weighting whose max capacity was big enough to contain size
		return replace( get( typeCode ), size, precision, scale );
	}

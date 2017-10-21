	@Override
	public boolean[] includeInTransform(String[] aliases, int tupleLength) {
		boolean[] includeInTransform;
		if ( tupleLength == 1 ) {
			includeInTransform = ArrayHelper.TRUE;
		}
		else {
			includeInTransform = new boolean[tupleLength];
			includeInTransform[ tupleLength - 1 ] = true;
		}
		return includeInTransform;
	}

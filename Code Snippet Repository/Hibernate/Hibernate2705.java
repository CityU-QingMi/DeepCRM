	private boolean areSqlTypesCompatible(int target, int source) {
		switch ( target ) {
			case Types.TIMESTAMP:
				return source == Types.DATE || source == Types.TIME || source == Types.TIMESTAMP;
			case Types.DATE:
				return source == Types.DATE || source == Types.TIMESTAMP;
			case Types.TIME:
				return source == Types.TIME || source == Types.TIMESTAMP;
			default:
				return target == source;
		}
	}

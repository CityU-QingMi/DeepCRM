	public void addProjection(String function, String alias, String propertyName, boolean distinct) {
		final String effectivePropertyName = propertyName == null ? "" : ".".concat( propertyName );
		if ( function == null ) {
			projections.add( (distinct ? "distinct " : "") + alias + effectivePropertyName );
		}
		else {
			projections.add(
					function + "(" + (distinct ? "distinct " : "") + alias +
					effectivePropertyName + ")"
			);
		}
	}

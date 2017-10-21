	@Override
	public ParameterTranslations getParameterTranslations() {
		return new ParameterTranslations() {
			@Override
			public boolean supportsOrdinalParameterMetadata() {
				// classic translator does not support collection of ordinal
				// param metadata
				return false;
			}

			@Override
			public int getOrdinalParameterCount() {
				return 0; // not known!
			}

			@Override
			public int getOrdinalParameterSqlLocation(int ordinalPosition) {
				return 0; // not known!
			}

			@Override
			public Type getOrdinalParameterExpectedType(int ordinalPosition) {
				return null; // not known!
			}

			@Override
			public Set getNamedParameterNames() {
				return namedParameters.keySet();
			}

			@Override
			public int[] getNamedParameterSqlLocations(String name) {
				return getNamedParameterLocs( name );
			}

			@Override
			public Type getNamedParameterExpectedType(String name) {
				return null; // not known!
			}
		};
	}

	@Override
	public String[][] getSuffixedPropertyAliases(Loadable persister) {
		final int size = persister.getPropertyNames().length;
		final String[][] suffixedPropertyAliases;
		if (size > 0) {
			suffixedPropertyAliases = new String[size][];
			for ( int j = 0; j < size; j++ ) {
				suffixedPropertyAliases[j] = getUserProvidedAliases(
						persister.getPropertyNames()[j],
						getPropertyAliases( persister, j )
				);
				suffixedPropertyAliases[j] = StringHelper.unquote( suffixedPropertyAliases[j], persister.getFactory().getDialect() );
				intern( suffixedPropertyAliases[j] );
			}
		}
		else {
			suffixedPropertyAliases = EMPTY_ARRAY_OF_ARRAY_OF_STRINGS;
		}
		return suffixedPropertyAliases;
	}

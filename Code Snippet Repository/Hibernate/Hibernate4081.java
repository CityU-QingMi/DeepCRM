	private String manyToManySelectFragment(
			Joinable rhs,
			String rhsAlias,
			String lhsAlias,
			String collectionSuffix) {
		SelectFragment frag = generateSelectFragment( lhsAlias, collectionSuffix );

		String[] elementColumnNames = rhs.getKeyColumnNames();
		frag.addColumns( rhsAlias, elementColumnNames, elementColumnAliases );
		appendIndexColumns( frag, lhsAlias );
		appendIdentifierColumns( frag, lhsAlias );

		return frag.toFragmentString()
				.substring( 2 ); //strip leading ','
	}

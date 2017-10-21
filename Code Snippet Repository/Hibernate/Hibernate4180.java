	private CaseFragment discriminatorFragment(String alias) {
		CaseFragment cases = getFactory().getDialect().createCaseFragment();

		for ( int i = 0; i < discriminatorValues.length; i++ ) {
			cases.addWhenColumnNotNull(
					generateTableAlias( alias, notNullColumnTableNumbers[i] ),
					notNullColumnNames[i],
					discriminatorValues[i]
			);
		}

		return cases;
	}

	public String generateHashedConstraintName(String prefix, Identifier tableName, Identifier... columnNames ) {
		// Use a concatenation that guarantees uniqueness, even if identical names
		// exist between all table and column identifiers.

		StringBuilder sb = new StringBuilder( "table`" + tableName + "`" );

		// Ensure a consistent ordering of columns, regardless of the order
		// they were bound.
		// Clone the list, as sometimes a set of order-dependent Column
		// bindings are given.
		Identifier[] alphabeticalColumns = columnNames.clone();
		Arrays.sort(
				alphabeticalColumns,
				new Comparator<Identifier>() {
					@Override
					public int compare(Identifier o1, Identifier o2) {
						return o1.getCanonicalName().compareTo( o2.getCanonicalName() );
					}
				}
		);
		for ( Identifier columnName : alphabeticalColumns ) {
			sb.append( "column`" ).append( columnName ).append( "`" );
		}
		return prefix + hashedName( sb.toString() );
	}

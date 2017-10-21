	private void associateSubclassNamesToSubclassTableIndex(
			String tableName,
			Set<String> classNames,
			String[][] mapping) {
		// find the table's entry in the subclassTableNameClosure array
		boolean found = false;
		for ( int i = 0; i < subclassTableNameClosure.length; i++ ) {
			if ( subclassTableNameClosure[i].equals( tableName ) ) {
				found = true;
				final int index = i - coreTableSpan;
				if ( index < 0 || index >= mapping.length ) {
					throw new IllegalStateException(
							String.format(
									"Encountered 'subclass table index' [%s] was outside expected range ( [%s] < i < [%s] )",
									index,
									0,
									mapping.length
							)
					);
				}
				mapping[index] = classNames.toArray( new String[ classNames.size() ] );
				break;
			}
		}
		if ( !found ) {
			throw new IllegalStateException(
					String.format(
							"Was unable to locate subclass table [%s] in 'subclassTableNameClosure'",
							tableName
					)
			);
		}
	}

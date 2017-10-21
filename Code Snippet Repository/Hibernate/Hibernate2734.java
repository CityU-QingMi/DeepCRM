	private void initializeColumnNames() {
		// Generate an 2d array of column names, the first dimension is parallel with the
		// return types array.  The second dimension is the list of column names for each
		// type.

		// todo: we should really just collect these from the various SelectExpressions, rather than regenerating here
		columnNames = getSessionFactoryHelper().generateColumnNames( queryReturnTypes );
		columnNamesStartPositions = new int[columnNames.length];
		int startPosition = 1;
		for ( int i = 0; i < columnNames.length; i++ ) {
			columnNamesStartPositions[i] = startPosition;
			startPosition += columnNames[i].length;
		}
	}
